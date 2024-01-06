package com.example.tbo_probeaufgabe.data.remote.model


/**
 * Created by nurseiit.tursunkulov on 03.01.2024
 * Coin
 */
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tbo_probeaufgabe.domain.model.Coin
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "coin_data")
data class CoinApiModel(
    @PrimaryKey
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    @Json(name = "current_price")
    val currentPrice: Double,
    @Json(name = "market_cap")
    val marketCap: Long,
    @Json(name = "market_cap_rank")
    val marketCapRank: Int,
    @Json(name = "fully_diluted_valuation")
    val fullyDilutedValuation: Long,
    @Json(name = "total_volume")
    val totalVolume: Long,
    @Json(name = "high_24h")
    val high24h: Double,
    @Json(name = "low_24h")
    val low24h: Double,
    @Json(name = "price_change_24h")
    val priceChange24h: Double,
    @Json(name = "price_change_percentage_24h")
    val priceChangePercentage24h: Double,
    @Json(name = "market_cap_change_24h")
    val marketCapChange24h: Double,
    @Json(name = "market_cap_change_percentage_24h")
    val marketCapChangePercentage24h: Double,
    @Json(name = "circulating_supply")
    val circulatingSupply: Double,
    @Json(name = "total_supply")
    val totalSupply: Double,
    @Json(name = "max_supply")
    val maxSupply: Double?,
    val ath: Double,
    @Json(name = "ath_change_percentage")
    val athChangePercentage: Double,
    @Json(name = "ath_date")
    val athDate: String,
    val atl: Double,
    @Json(name = "atl_change_percentage")
    val atlChangePercentage: Double,
    @Json(name = "atl_date")
    val atlDate: String,
    @Json(name = "last_updated")
    val lastUpdated: String
){
    fun toDomainModel():Coin{
        return Coin(
            id = this.id,
                    symbol = this.symbol,
                    name = this.name,
                    image = this.image,
                    currentPrice = this.currentPrice,
                    priceChange24h = this.priceChange24h,
                    priceChangePercentage24h = this.priceChangePercentage24h,
                    lastUpdated = this.lastUpdated,
        )
    }
}
