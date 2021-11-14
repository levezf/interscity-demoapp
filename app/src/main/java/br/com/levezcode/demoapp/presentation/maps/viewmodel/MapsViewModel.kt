package br.com.levezcode.demoapp.presentation.maps.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.levezcode.demoapp.commom.usecase.NoParams
import br.com.levezcode.demoapp.domain.entities.ResourceDetails
import br.com.levezcode.demoapp.domain.usecases.GetResourceDetailsUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MapsViewModel(
    private val getResourceDetailsUseCase: GetResourceDetailsUseCase
) : ViewModel() {

    private val _resources = MutableLiveData(emptyList<ResourceDetails>())
    val resources: LiveData<List<ResourceDetails>>
        get() = _resources

    init {
        updateResources()
    }

    private fun updateResources() {
        viewModelScope.launch {
            getResourceDetailsUseCase.invoke(NoParams())
                .collect {
                    _resources.postValue(it)
                }
        }
    }
}
