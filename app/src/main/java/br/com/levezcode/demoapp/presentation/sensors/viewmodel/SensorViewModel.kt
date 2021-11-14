package br.com.levezcode.demoapp.presentation.sensors.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.levezcode.demoapp.commom.usecase.NoParams
import br.com.levezcode.demoapp.domain.entities.ResourceData
import br.com.levezcode.demoapp.domain.usecases.GetAnemometerUseCase
import br.com.levezcode.demoapp.domain.usecases.GetThermometerUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SensorViewModel(
    private val getAnemometerUseCase: GetAnemometerUseCase,
    private val getThermometerUseCase: GetThermometerUseCase
) : ViewModel() {

    private val _anemometroData = MutableLiveData(ResourceData())
    val anemometro: LiveData<ResourceData>
        get() = _anemometroData

    private val _isLoadingAnemometro = MutableLiveData(true)
    val isLoadingAnemometro: LiveData<Boolean>
        get() = _isLoadingAnemometro

    private val _termometroData = MutableLiveData(ResourceData())
    val termometro: LiveData<ResourceData>
        get() = _termometroData

    private val _isLoadingTermometro = MutableLiveData(true)
    val isLoadingTermometro: LiveData<Boolean>
        get() = _isLoadingTermometro

    init {
        updateAnemometroData()
        updateTermometroData()
    }

    fun updateAnemometroData() {
        viewModelScope.launch {
            _isLoadingAnemometro.postValue(true)
            getAnemometerUseCase.invoke(NoParams())
                .flowOn(Dispatchers.IO)
                .collect {
                    _anemometroData.postValue(it.single)
                    _isLoadingAnemometro.postValue(false)
                }
        }
    }

    fun updateTermometroData() {
        viewModelScope.launch {
            _isLoadingTermometro.postValue(true)
            getThermometerUseCase.invoke(NoParams())
                .collect {
                    _termometroData.postValue(it.single)
                    _isLoadingTermometro.postValue(false)
                }
        }
    }
}
