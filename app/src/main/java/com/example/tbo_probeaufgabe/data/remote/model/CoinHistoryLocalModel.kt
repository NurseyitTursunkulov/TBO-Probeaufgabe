package com.example.tbo_probeaufgabe.data.remote.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.tbo_probeaufgabe.domain.model.CoinHistoryPrice
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


/**
 * Created by nurseiit.tursunkulov on 06.01.2024
 * CoinHistoryLocalModel
 */

@JsonClass(generateAdapter = true)
@Entity(
    tableName = "coin_history",//todo put in constants
    foreignKeys = [ForeignKey(
        entity = CoinApiModel::class,
        parentColumns = ["id"],
        childColumns = ["id"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("id")]
)
data class CoinHistoryLocalModel(
    @PrimaryKey
    val id: String,

    val prices: List<List<String>>,
    @Json(name = "market_caps")
    val marketCaps: List<List<String>>,
    @Json(name = "total_volumes")
    val totalVolumes: List<List<String>>
) {
    companion object {
        fun toDomainModel(coinHistoryLocalModel: CoinHistoryLocalModel): CoinHistoryPrice {
            return CoinHistoryPrice(
                id = coinHistoryLocalModel.id,
                prices = coinHistoryLocalModel.prices
            )
        }

        fun toApiModel(coinHistoryLocalModel: CoinHistoryLocalModel): CoinHistoryApiModel {
            return CoinHistoryApiModel(
                prices = coinHistoryLocalModel.prices,
                marketCaps = coinHistoryLocalModel.marketCaps,
                totalVolumes = coinHistoryLocalModel.totalVolumes
            )
        }
    }
}