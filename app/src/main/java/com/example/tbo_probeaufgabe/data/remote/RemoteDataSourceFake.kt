package com.example.tbo_probeaufgabe.data.remote

import android.util.Log
import com.example.tbo_probeaufgabe.data.local.test.coinHistoryList
import com.example.tbo_probeaufgabe.data.local.test.coinList
import com.example.tbo_probeaufgabe.data.remote.model.CoinApiModel
import com.example.tbo_probeaufgabe.data.remote.model.CoinHistoryApiModel
import com.example.tbo_probeaufgabe.data.remote.model.CoinHistoryLocalModel.Companion.toApiModel
import com.example.tbo_probeaufgabe.util.networkUtil.NetworkResponse
import kotlinx.coroutines.delay


/**
 * Created by nurseiit.tursunkulov on 06.01.2024
 * RemoteDataSourceTest
 */
class RemoteDataSourceFake: RemoteDataSource {
    override suspend fun getCoins(): NetworkResponse<List<CoinApiModel>> {
        Log.i("nurs", "RemoteDataSource getCoins from remote ${coinList.first().name}")
        delay(2000)
        return NetworkResponse.Success(coinList)
    }

    override suspend fun getCoinHistory(id: String): NetworkResponse<CoinHistoryApiModel> {
        Log.i("nurs", "RemoteDataSource getCoinHistory from remote ${id}")
        delay(2000)
        return NetworkResponse.Success(coinHistoryList.find { it.id == id }?.let { toApiModel(it) }!!)
    }
}