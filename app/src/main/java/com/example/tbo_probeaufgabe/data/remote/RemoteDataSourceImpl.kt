package com.example.tbo_probeaufgabe.data.remote

import com.example.tbo_probeaufgabe.data.remote.model.CoinApiModel
import com.example.tbo_probeaufgabe.data.remote.model.CoinHistoryApiModel
import com.example.tbo_probeaufgabe.util.fetchResponse
import com.example.tbo_probeaufgabe.util.networkUtil.NetworkResponse


/**
 * Created by nurseiit.tursunkulov on 06.01.2024
 * RemoteDataSourceImpl
 */
class RemoteDataSourceImpl(val api: Api) :RemoteDataSource{
    override suspend fun getCoins(): NetworkResponse<List<CoinApiModel>> {
       return fetchResponse(api::getCoins)
    }

    override suspend fun getCoinHistory(id: String): NetworkResponse<CoinHistoryApiModel> {
        return fetchResponse{api.getCoinHistory(id)}
    }
}