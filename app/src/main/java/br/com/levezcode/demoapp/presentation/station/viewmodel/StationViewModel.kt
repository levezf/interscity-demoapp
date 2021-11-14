package br.com.levezcode.demoapp.presentation.station.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.levezcode.demoapp.commom.usecase.NoParams
import br.com.levezcode.demoapp.domain.entities.ResourceData
import br.com.levezcode.demoapp.domain.usecases.GetStationUseCase
import java.util.Calendar
import java.util.Locale
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StationViewModel(
    private val getStationUseCase: GetStationUseCase
) : ViewModel() {

    companion object {
        const val TEMPLATE_LAST_UPDATE = "Última atualização em %02d/%02d/%4d às %02d:%02d"
        const val NO_UPDATES_YET = "Sem atualizações até o momento"
    }

    private val _anemometroData = MutableLiveData(ResourceData())
    val anemometro: LiveData<ResourceData>
        get() = _anemometroData

    private val _termometroData = MutableLiveData(ResourceData())
    val termometro: LiveData<ResourceData>
        get() = _termometroData

    private val _higrometroData = MutableLiveData(ResourceData())
    val higrometro: LiveData<ResourceData>
        get() = _higrometroData

    private val _luminosidadeData = MutableLiveData(ResourceData())
    val luminosidade: LiveData<ResourceData>
        get() = _luminosidadeData

    private val _altimetroData = MutableLiveData(ResourceData())
    val altimetro: LiveData<ResourceData>
        get() = _altimetroData

    private val _barometroData = MutableLiveData(ResourceData())
    val barometro: LiveData<ResourceData>
        get() = _barometroData

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _lastUpdate = MutableLiveData(NO_UPDATES_YET)
    val lastUpdate: LiveData<String>
        get() = _lastUpdate

    init {
        update()
    }

    fun update() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            getStationUseCase.invoke(NoParams())
                .collect {
                    _anemometroData.postValue(it.anemometerSensor)
                    _termometroData.postValue(it.thermometerSensor)
                    _higrometroData.postValue(it.hygrometerSensor)
                    _luminosidadeData.postValue(it.luminositySensor)
                    _altimetroData.postValue(it.altimeterSensor)
                    _barometroData.postValue(it.barometerSensor)
                    _lastUpdate.postValue(getLastUpdate())
                    _isLoading.postValue(false)
                }
        }
    }

    private fun getLastUpdate(): String =
        Calendar.getInstance(Locale.getDefault()).let {
            TEMPLATE_LAST_UPDATE.format(
                it.get(Calendar.DAY_OF_MONTH),
                it.get(Calendar.MONTH) + 1,
                it.get(Calendar.YEAR),
                it.get(Calendar.HOUR_OF_DAY),
                it.get(Calendar.MINUTE)
            )
        }
}
