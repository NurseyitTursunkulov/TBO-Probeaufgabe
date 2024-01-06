package com.example.tbo_probeaufgabe.data.remote.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


/**
 * Created by nurseiit.tursunkulov on 06.01.2024
 * CoinHistoryLocalModel
 */

@JsonClass(generateAdapter = true)
@Entity(tableName = "coin_history", foreignKeys = [ForeignKey(entity = CoinApiModel::class, parentColumns = ["id"], childColumns = ["id"])])
data class CoinHistoryLocalModel(
    @PrimaryKey
    val id: String ,

    val prices: List<List<String>>,
    @Json(name = "market_caps")
    val marketCaps: List<List<String>>,
    @Json(name = "total_volumes")
    val totalVolumes: List<List<String>>
)