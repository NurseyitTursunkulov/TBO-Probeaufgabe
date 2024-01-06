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
interface RemoteDataSource {
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
suspend fun <A> fetchResponse(
    networkFunction: suspend () -> A
) :NetworkResponse<A> {
    try {
        val res = networkFunction()
        return NetworkResponse.Success(res)
    } catch (e: retrofit2.HttpException) {
        if (e.code() == 429) {
            delay(1000)
            Log.d("nurs", "after delay ")
            return fetchResponse(networkFunction)
        }else {
            return NetworkResponse.UnknownException(e)
        }
    }
    catch (e: Exception) {
        return NetworkResponse.UnknownException(e)
    }
}