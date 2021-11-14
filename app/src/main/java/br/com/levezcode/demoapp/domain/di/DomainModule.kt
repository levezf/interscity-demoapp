package br.com.levezcode.demoapp.domain.di

import br.com.levezcode.demoapp.domain.usecases.GetAnemometerUseCase
import br.com.levezcode.demoapp.domain.usecases.GetResourceDetailsUseCase
import br.com.levezcode.demoapp.domain.usecases.GetStationUseCase
import br.com.levezcode.demoapp.domain.usecases.GetThermometerUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetAnemometerUseCase(get()) }
    single { GetThermometerUseCase(get()) }
    single { GetResourceDetailsUseCase(get()) }
    single { GetStationUseCase(get()) }
}
