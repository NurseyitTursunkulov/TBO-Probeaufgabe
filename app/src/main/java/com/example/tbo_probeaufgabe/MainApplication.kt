package com.example.tbo_probeaufgabe

import android.app.Application
import androidx.room.Room
import com.example.tbo_probeaufgabe.data.RepoImpl
import com.example.tbo_probeaufgabe.data.Repository
import com.example.tbo_probeaufgabe.data.local.LocalDataSource
import com.example.tbo_probeaufgabe.data.local.LocalDataSourceImpl
import com.example.tbo_probeaufgabe.data.local.db.Dao
import com.example.tbo_probeaufgabe.data.local.db.Database
import com.example.tbo_probeaufgabe.data.local.test.LocalDataSourceFake
import com.example.tbo_probeaufgabe.data.remote.Api
import com.example.tbo_probeaufgabe.data.remote.RemoteDataSourceFake
import okhttp3.OkHttpClient
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
val appModule = module {//todo remove to modules class
    viewModel { MainViewModel(api = get(), dao = get(), repository = get()) }
    single { createApiService() }
    single {
      Room.databaseBuilder(
                androidContext(),
                Database::class.java,
                Database.DATABASE_NAME
            )
          .fallbackToDestructiveMigration().build()
    }
    single <Dao>{ get<Database>().getDao() }
//    single <LocalDataSource>{ LocalDataSourceTest() }
//    single <RemoteDataSource>{ RemoteDataSourceTest() }
    single<Repository>{RepoImpl(localDataSource = LocalDataSourceImpl(get()), remoteDataSource = RemoteDataSourceFake())}
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



