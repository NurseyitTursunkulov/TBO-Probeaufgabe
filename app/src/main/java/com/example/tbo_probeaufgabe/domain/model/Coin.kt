package com.example.tbo_probeaufgabe.domain.model

/**
 * Created by nurseiit.tursunkulov on 05.01.2024
 * Coin
 */
data class Coin(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val currentPrice: Double,
    val priceChange24h: Double,
    val priceChangePercentage24h: Double,
    val lastUpdated: String,//todo use local time, so that user will not confused
    var historyPrice: CoinHistoryPrice? = null
)