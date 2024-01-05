package com.example.tbo_probeaufgabe.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


/**
 * Created by nurseiit.tursunkulov on 03.01.2024
 * CoinHistoryApiModel
 */
@JsonClass(generateAdapter = true)
data class CoinHistoryApiModel(
    val prices: List<List<String>>,
    @Json(name = "market_caps")
    val marketCaps: List<List<String>>,
    @Json(name = "total_volumes")
    val totalVolumes: List<List<String>>
)
