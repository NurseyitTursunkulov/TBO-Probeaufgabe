package com.example.tbo_probeaufgabe.data.remote

import com.example.tbo_probeaufgabe.data.remote.model.CoinApiModel
import com.example.tbo_probeaufgabe.data.remote.model.CoinHistoryApiModel


/**
 * Created by nurseiit.tursunkulov on 06.01.2024
 * RemoteDataSourceImpl
 */
class RemoteDataSourceImpl(val api: Api) :RemoteDataSource{
    override suspend fun getCoins(): List<CoinApiModel> {
       return api.getCoins()
    }

    override suspend fun getCoinHistory(id: String): CoinHistoryApiModel {
        return api.getCoinHistory(id)
    }
}