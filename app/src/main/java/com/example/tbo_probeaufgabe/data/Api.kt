package com.example.tbo_probeaufgabe.data

import com.example.tbo_probeaufgabe.data.model.CoinApiModel
import retrofit2.http.GET
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
        @Query("vs_currency") currency: String = "eur"
    ): List<CoinApiModel>
}