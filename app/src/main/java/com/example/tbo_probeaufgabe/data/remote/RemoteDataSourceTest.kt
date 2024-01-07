package com.example.tbo_probeaufgabe.data.remote

import android.util.Log
import com.example.tbo_probeaufgabe.data.local.test.coinHistoryList
import com.example.tbo_probeaufgabe.data.local.test.coinList
import com.example.tbo_probeaufgabe.data.remote.model.CoinApiModel
import com.example.tbo_probeaufgabe.data.remote.model.CoinHistoryApiModel
import com.example.tbo_probeaufgabe.data.remote.model.CoinHistoryLocalModel.Companion.toApiModel
import kotlinx.coroutines.delay


/**
 * Created by nurseiit.tursunkulov on 06.01.2024
 * RemoteDataSourceTest
 */
class RemoteDataSourceTest: RemoteDataSource {
    override suspend fun getCoins(): List<CoinApiModel> {
        Log.i("nurs", "RemoteDataSource getCoins from remote ${coinList.first().name}")
        delay(2000)
        return coinList
    }

    override suspend fun getCoinHistory(id: String): CoinHistoryApiModel {
        Log.i("nurs", "RemoteDataSource getCoinHistory from remote ${id}")
        delay(2000)
        return coinHistoryList.find { it.id == id }?.let { toApiModel(it) }!!
    }
}