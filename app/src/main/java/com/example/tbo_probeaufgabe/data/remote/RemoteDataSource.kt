package com.example.tbo_probeaufgabe.data.remote

import com.example.tbo_probeaufgabe.data.remote.model.CoinApiModel
import com.example.tbo_probeaufgabe.data.remote.model.CoinHistoryApiModel
import com.example.tbo_probeaufgabe.util.networkUtil.NetworkResponse


/**
 * Created by nurseiit.tursunkulov on 06.01.2024
 * RemoteDataSource
 */
interface RemoteDataSource {
    suspend fun getCoins(): NetworkResponse<List<CoinApiModel>>

    suspend fun getCoinHistory(id: String): NetworkResponse<CoinHistoryApiModel>
}