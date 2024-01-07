package com.example.tbo_probeaufgabe.data.local.test

import android.util.Log
import com.example.tbo_probeaufgabe.data.local.LocalDataSource
import com.example.tbo_probeaufgabe.data.remote.model.CoinApiModel
import com.example.tbo_probeaufgabe.data.remote.model.CoinHistoryLocalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flowOf


/**
 * Created by nurseiit.tursunkulov on 06.01.2024
 * LocalDataSourceTest
 */
class LocalDataSourceTest: LocalDataSource {

    private val coinFlowInternal = MutableStateFlow<List<CoinApiModel>>(listOf())

    // Expose the flow as a shared flow to subscribers
    val coinFlow: Flow<List<CoinApiModel>> = coinFlowInternal
    val coinHistory:MutableList<CoinHistoryLocalModel> = mutableListOf()

    override suspend fun getCoins(): Flow<List<CoinApiModel>> {
        Log.v("nurs", "getCoins from local ")
        return coinFlow
    }

    override suspend fun insertCoins(coins: List<CoinApiModel>) {
        Log.v("nurs", "insertCoins from Local ${coins.first().name}")
        coinFlowInternal.emit(coins)
    }

    override suspend fun insertCoinHistory(coinHistory: CoinHistoryLocalModel) {
        Log.v("nurs", "insertCoinHistory from Local ${coinHistory}")
        this.coinHistory.add(coinHistory)
    }

    override suspend fun getCoinHistoryFlow(id: String): Flow<CoinHistoryLocalModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getCoinHistory(id: String): CoinHistoryLocalModel? {
        return coinHistory.find { it.id == id }?.apply {
            Log.v("nurs", "getCoinHistory from Local ${this}")
        }
    }
}