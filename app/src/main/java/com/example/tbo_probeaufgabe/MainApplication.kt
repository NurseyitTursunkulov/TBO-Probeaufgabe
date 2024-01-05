package com.example.tbo_probeaufgabe

import android.app.Application
import androidx.room.Room
import com.example.tbo_probeaufgabe.data.local.Dao
import com.example.tbo_probeaufgabe.data.local.Database
import com.example.tbo_probeaufgabe.data.remote.Api
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
    viewModel { MainViewModel(api = get(), dao = get()) }
    single { createApiService() }
    single {
      Room.databaseBuilder(
                androidContext(),
                Database::class.java,
                Database.DATABASE_NAME
            ).build()
    }
    single <Dao>{ get<Database>().getDao() }
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



