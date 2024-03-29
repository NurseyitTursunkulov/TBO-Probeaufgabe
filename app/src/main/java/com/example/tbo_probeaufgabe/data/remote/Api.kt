package com.example.tbo_probeaufgabe.data.remote

import android.util.Log
import com.example.tbo_probeaufgabe.data.remote.model.CoinApiModel
import com.example.tbo_probeaufgabe.data.remote.model.CoinHistoryApiModel
import com.example.tbo_probeaufgabe.util.networkUtil.NetworkResponse
import kotlinx.coroutines.delay
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by nurseiit.tursunkulov on 03.01.2024
 * Api
 */
interface Api {
    companion object {
        const val BASE_URL = "https://api.coingecko.com/api/v3/coins/"
    }

    @GET("markets")
    suspend fun getCoins(
        @Query("vs_currency") currency: String = "eur",
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int = 1,
    ): List<CoinApiModel>

    @GET("{id}/market_chart")
    suspend fun getCoinHistory(
        @Path("id") id: String,
        @Query("vs_currency") currency: String = "eur",
        @Query("days") days: Int = 14,
        @Query("interval") interval: String = "daily"
    ): CoinHistoryApiModel

}