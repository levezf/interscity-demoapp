package br.com.levezcode.demoapp.presentation.di

import br.com.levezcode.demoapp.presentation.maps.viewmodel.MapsViewModel
import br.com.levezcode.demoapp.presentation.sensors.viewmodel.SensorViewModel
import br.com.levezcode.demoapp.presentation.station.viewmodel.StationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { StationViewModel(get()) }
    viewModel { SensorViewModel(get(), get()) }
    viewModel { MapsViewModel(get()) }
}
