package com.example.tbo_probeaufgabe

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbo_probeaufgabe.data.Repository
import com.example.tbo_probeaufgabe.data.local.db.Dao
import com.example.tbo_probeaufgabe.data.remote.Api
import com.example.tbo_probeaufgabe.data.remote.fetchResponse
import com.example.tbo_probeaufgabe.data.remote.model.CoinApiModel
import com.example.tbo_probeaufgabe.data.remote.model.CoinHistoryApiModel
import com.example.tbo_probeaufgabe.util.networkUtil.NetworkResponse
import com.example.tbo_probeaufgabe.util.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException


/**
 * Created by nurseiit.tursunkulov on 03.01.2024
 * MainViewModel
 */
class MainViewModel(
    val api: Api,
    val dao: Dao,
    val repository: Repository
) : ViewModel() {
    init {
        Log.d("nurs", "init")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getCoins().collect {
                        Log.e("nurs", "MainViewmodel it ${it}")

                }
            }
        }
    }

    fun clearDb() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dao.clearCoin()
                dao.clearHistory()
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        Log.d("nurs", "onCleared")
        viewModelScope.coroutineContext.cancel()
    }

}