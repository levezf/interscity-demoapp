package br.com.levezcode.demoapp.commom.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import br.com.levezcode.demoapp.R

private const val CHANNEL_ID = "AdvanSE_Demo_App_of_InterSCity"
private const val NOTIFICATION_ICON = R.drawable.ic_hygrometer
private const val NOTIFICATION_ID = 1

fun Service.startForeground(notification: Notification) {
    this.startForeground(NOTIFICATION_ID, notification)
}

fun Service.removeAllNotifications() {
    getNotificationManager().cancelAll()
}

fun Service.sendNotification(
    title: String,
    text: String,
): Notification {
    val notification = createNotification(title, text, applicationContext).build()
    getNotificationManager().notify(
        NOTIFICATION_ID,
        notification
    )
    return notification
}

fun Service.sendNotificationWithProgress(
    title: String,
    text: String,
    max: Int,
    progress: Int,
): Notification {
    val notification = createNotification(title, text, applicationContext)
        .setProgress(max, progress, false)
        .build()

    getNotificationManager().notify(
        NOTIFICATION_ID,
        notification
    )
    return notification
}

private fun isNeededStartForegroundAndCreateChannel() =
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

private fun createNotification(
    title: String,
    text: String,
    applicationContext: Context
): NotificationCompat.Builder {
    val builder = if (isNeededStartForegroundAndCreateChannel()) {
        applicationContext.createNotificationChannel()
        NotificationCompat.Builder(
            applicationContext,
            CHANNEL_ID
        )
    } else {
        NotificationCompat.Builder(
            applicationContext
        )
    }

    return builder
        .setSmallIcon(NOTIFICATION_ICON)
        .setContentTitle(title)
        .setContentText(text)
        .setAutoCancel(false)
        .setChannelId(CHANNEL_ID)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
}

@RequiresApi(Build.VERSION_CODES.O)
private fun Context.createNotificationChannel() {
    val name = getString(R.string.channel_name)
    val descriptionText = getString(R.string.channel_description)
    val importance = NotificationManager.IMPORTANCE_DEFAULT
    val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
        description = descriptionText
    }
    getNotificationManager().createNotificationChannel(channel)
}

private fun Context.getNotificationManager(): NotificationManager = ContextCompat.getSystemService(
    this,
    NotificationManager::class.java
) as NotificationManager
