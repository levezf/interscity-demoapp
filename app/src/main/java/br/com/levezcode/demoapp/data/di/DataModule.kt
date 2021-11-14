package br.com.levezcode.demoapp.data.di

import android.content.Context
import android.content.SharedPreferences
import br.com.levezcode.demoapp.BuildConfig
import br.com.levezcode.demoapp.data.datasources.local.ILocalDataSource
import br.com.levezcode.demoapp.data.datasources.local.SharedPreferencesDataSource
import br.com.levezcode.demoapp.data.datasources.remote.IRemoteDataSource
import br.com.levezcode.demoapp.data.datasources.remote.InterscityRemoteDataSource
import br.com.levezcode.demoapp.data.repositories.InterscityRepository
import br.com.levezcode.demoapp.domain.repositories.IResourceRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val API_BASE_URL = "http://api.playground.interscity.org"

val dataModule = module {
    single { provideSharedPreferences(get()) }
    single { provideRetrofit() }
    single<IRemoteDataSource> { provideIntercityRemoteDataSource(get()) }
    single<ILocalDataSource> { SharedPreferencesDataSource(get()) }
    single<IResourceRepository> { InterscityRepository(get(), get()) }
}

private fun provideSharedPreferences(context: Context): SharedPreferences =
    context.getSharedPreferences(
        context.packageName,
        Context.MODE_PRIVATE
    )

private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .client(provideOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().let {
    if (BuildConfig.DEBUG) {
        it.addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
    }
    it.build()
}

private fun provideIntercityRemoteDataSource(retrofit: Retrofit): InterscityRemoteDataSource =
    retrofit.create(InterscityRemoteDataSource::class.java)
