package com.example.tbo_probeaufgabe

import android.app.Application
import com.example.tbo_probeaufgabe.data.Api
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

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
        .client(OkHttpClient.Builder()
            .build())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(Api::class.java)
}

class RateLimitInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (response.code() == 429) {
            // Handle rate-limiting logic here
            // You might want to wait for a certain amount of time and retry the request
            // Example: Retry after waiting for 5 seconds
            Thread.sleep(5000)
            return chain.proceed(request)
        }

        return response
    }
}