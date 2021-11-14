package br.com.levezcode.demoapp

import android.app.Application
import br.com.levezcode.demoapp.data.di.dataModule
import br.com.levezcode.demoapp.domain.di.domainModule
import br.com.levezcode.demoapp.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class DemoApp : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@DemoApp)
            modules(
                dataModule,
                domainModule,
                presentationModule
            )
        }
    }
}
