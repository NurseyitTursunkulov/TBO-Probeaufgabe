package com.example.tbo_probeaufgabe

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbo_probeaufgabe.data.local.Dao
import com.example.tbo_probeaufgabe.data.remote.Api
import com.example.tbo_probeaufgabe.data.remote.fetchResponse
import com.example.tbo_probeaufgabe.data.remote.model.CoinApiModel
import com.example.tbo_probeaufgabe.util.networkUtil.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException


/**
 * Created by nurseiit.tursunkulov on 03.01.2024
 * MainViewModel
 */
class MainViewModel(
    val api: Api,
    val dao: Dao
) : ViewModel() {
    init {
        Log.d("nurs", "init")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dao.getCoins().collect {
                    it.forEach {
                        Log.d("hadi", "it ${it}")
                    }
                }
            }
        }
    }

fun getFromApi() {
    viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val res = fetchResponse(api::getCoins)
            when (res) {
                is NetworkResponse.Success -> {
                    dao.insertCoins(res.body)
                    res.body.forEach {
                        Log.d("nurs", "${it.name}")
                        getHistory(it)
                    }
                }

                else -> {
                    Log.d("nurs", "${res.toString()}")
                }
            }
        }
    }
}

    private suspend fun getHistory(it: CoinApiModel) {
        val res2 = fetchResponse({ api.getCoinHistory(it.id) })
        when (res2) {
            is NetworkResponse.Success -> {
                Log.d("nurs", "resBody ${(res2.body)}")
            }

            is NetworkResponse.UnknownException -> {
                Log.d("nurs", "${res2.exception}")
                (res2.exception as? HttpException)?.let {
                    if (it.code() == 429) {
                        delay(10000)
                        Log.d("nurs", "after delay 300")
                    }
                }
            }
        }
    }

    override fun onCleared() {
    super.onCleared()
    Log.d("nurs", "onCleared")
    viewModelScope.coroutineContext.cancel()
}

}