package com.example.tbo_probeaufgabe

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbo_probeaufgabe.data.Repository
import com.example.tbo_probeaufgabe.data.local.db.Dao
import com.example.tbo_probeaufgabe.data.remote.Api
import com.example.tbo_probeaufgabe.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * Created by nurseiit.tursunkulov on 03.01.2024
 * MainViewModel
 */
class MainViewModel(
    val api: Api,
    val dao: Dao,
    val repository: Repository
) : ViewModel() {

    val state = MutableStateFlow<CoinListState>(CoinListState())

    init {
        Log.d("nurs", "init")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getCoins().collect {
                    when (it) {
                        is Result.Error -> {

                        }
                        Result.Loading -> {

                        }
                        is Result.Success -> {
                            state.value = CoinListState(
                                isLoading = false,
                                coins = it.data.sortedBy { it.name }//todo make customizble sort
                            )
                        }
                    }
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