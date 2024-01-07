package com.example.tbo_probeaufgabe

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbo_probeaufgabe.data.local.db.Dao
import com.example.tbo_probeaufgabe.data.remote.Api
import com.example.tbo_probeaufgabe.domain.GetCoinsUseCase
import com.example.tbo_probeaufgabe.domain.model.Coin
import com.example.tbo_probeaufgabe.domain.model.CoinHistoryPrice
import com.example.tbo_probeaufgabe.ui.screens.CoinListState
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
    val coinsUseCase: GetCoinsUseCase
) : ViewModel() {

    val state = MutableStateFlow<CoinListState>(CoinListState())//todo make private set, public get
    var selectedCoin =MutableStateFlow<CoinHistoryPrice>(CoinHistoryPrice("", listOf()))

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                coinsUseCase.invoke().collect {
                    when (it) {
                        is Result.Error -> { //todo show error
                        }

                        Result.Loading -> { //todo show loading,
                        }

                        is Result.Success -> {
                            state.value = CoinListState(
                                isLoading = false,
                                coins = it.data.sortedBy { it.name }//todo make customizble sort
                            )
                            coinsUseCase.startTorefresh()
                        }
                    }

                }
            }
        }
    }




    override fun onCleared() {
        super.onCleared()
        viewModelScope.coroutineContext.cancel()
    }

    fun setSelCoin(coin: Coin) {
        coin.historyPrice?.let {
            selectedCoin.value = it
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

}