package com.example.tbo_probeaufgabe

import android.app.Application
import com.example.tbo_probeaufgabe.data.Api
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by nurseiit.tursunkulov on 03.01.24.
 */


class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}
val appModule = module {
    viewModel { MainViewModel(get()) }
    single { createApiService() }
}
private fun createApiService(): Api {
    return Retrofit.Builder()
        .baseUrl(Api.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)
}