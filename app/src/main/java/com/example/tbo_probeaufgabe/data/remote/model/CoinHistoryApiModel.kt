package com.example.tbo_probeaufgabe.data.remote.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.io.IOException


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
){
    fun toLocalModel(id: String): CoinHistoryLocalModel {
        return CoinHistoryLocalModel(id, prices, marketCaps, totalVolumes)
    }
}