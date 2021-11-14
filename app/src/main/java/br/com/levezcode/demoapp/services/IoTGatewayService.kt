package br.com.levezcode.demoapp.services

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Messenger
import android.os.Process.THREAD_PRIORITY_BACKGROUND
import br.com.levezcode.demoapp.R
import br.com.levezcode.demoapp.commom.notification.removeAllNotifications
import br.com.levezcode.demoapp.commom.notification.sendNotification
import br.com.levezcode.demoapp.commom.notification.sendNotificationWithProgress
import br.com.levezcode.demoapp.commom.notification.startForeground
import br.com.levezcode.demoapp.domain.repositories.IResourceRepository
import java.util.Calendar
import kotlin.math.roundToLong
import kotlin.random.Random
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class IoTGatewayService : Service() {

    companion object {
        private const val TAG = "br.com.levezcode.IoTGateway"
        private const val INTERVAL_IN_MILLIS = 1_000L
        private const val TIME_TO_WAIT_IN_MILLIS = 30_000L
        const val FORCE_SEND = 1
    }

    enum class IoTSenderStatus {
        STARTING, GENERATING, WAITING, SENDING
    }

    private val repository: IResourceRepository by inject()
    private var serviceLooper: Looper? = null
    private var serviceHandler: ServiceHandler? = null
    private var handlerThread: HandlerThread? = null
    private var messenger: Messenger? = null
    private var latch: CountDownTimer? = null

    private inner class ServiceHandler(looper: Looper) : Handler(looper) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                FORCE_SEND -> {
                    latch?.onFinish()
                }
                else -> startProcess()
            }
        }
    }

    fun startProcess() {
        latch = object : CountDownTimer(TIME_TO_WAIT_IN_MILLIS, INTERVAL_IN_MILLIS) {
            override fun onTick(millisUntilFinished: Long) {
                updateNotification(
                    IoTSenderStatus.WAITING,
                    progress = (TIME_TO_WAIT_IN_MILLIS - millisUntilFinished).toInt()
                )
                // enviar via message
            }

            override fun onFinish() {
                sendSimulatedData()
                startProcess()
            }
        }
        latch?.start()
    }

    private fun sendSimulatedData() {
        updateNotification(IoTSenderStatus.GENERATING)
        val stationData = generateStationData()
        val thermometerData = generateThermometerData()
        val anemometerData = generateAnemometerData()

        updateNotification(IoTSenderStatus.SENDING)
        CoroutineScope(Job() + Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                repository.sendStationData(stationData)
                repository.sendThermometerData(thermometerData)
                repository.sendAnemometerData(anemometerData)
            }
        }
    }

    private fun generateAnemometerData() =
        DataGeneratorBuilder()
            .addCapability("Anemometro")
            .map

    private fun generateThermometerData() =
        DataGeneratorBuilder()
            .addCapability("Termometro")
            .map

    private fun generateStationData() =
        DataGeneratorBuilder()
            .addCapability("Higrômetro")
            .addCapability("Anemômetro")
            .addCapability("Luminosidade")
            .addCapability("Termômetro")
            .addCapability("Barômetro")
            .addCapability("Altímetro")
            .map

    override fun onCreate() {
        super.onCreate()
        startForeground(updateNotification(IoTSenderStatus.STARTING))

        handlerThread = HandlerThread(TAG, THREAD_PRIORITY_BACKGROUND).apply {
            start()
            serviceLooper = looper
            serviceHandler = ServiceHandler(looper)
            messenger = Messenger(serviceHandler)
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        messenger?.send(Message.obtain())

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = messenger?.binder

    private fun updateNotification(
        status: IoTSenderStatus,
        maxProgress: Int = TIME_TO_WAIT_IN_MILLIS.toInt(),
        progress: Int = 0
    ): Notification = when (status) {
        IoTSenderStatus.GENERATING -> {
            sendNotification(
                getString(R.string.iot_generating_values_title),
                getString(R.string.iot_generating_values_description)
            )
        }
        IoTSenderStatus.WAITING -> {
            sendNotificationWithProgress(
                getString(R.string.iot_waiting_to_send_title),
                getString(R.string.iot_waiting_to_send_description),
                maxProgress,
                progress
            )
        }
        IoTSenderStatus.SENDING -> {
            sendNotification(
                getString(R.string.iot_sending_title),
                getString(R.string.iot_sending_description)
            )
        }
        else -> sendNotification(
            getString(R.string.iot_starting_title),
            getString(R.string.iot_starting_description)
        )
    }

    override fun onDestroy() {
        removeAllNotifications()
        super.onDestroy()
    }
}

private class DataGeneratorBuilder {

    val map: Map<String, Any> = mutableMapOf(
        "data" to mutableMapOf<String, List<Map<String, Any>>>()
    )

    fun addCapability(name: String): DataGeneratorBuilder {
        (map["data"] as MutableMap<String, List<Map<String, Any>>>)[name] = listOf(
            mapOf<String, Any>(
                "value" to (Random.nextDouble(10.0, 100.0) * 100.0).roundToLong() / 100.0,
                "timestamp" to Calendar.getInstance().time
            )
        )
        return this
    }
}
