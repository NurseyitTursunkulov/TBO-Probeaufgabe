package com.example.tbo_probeaufgabe.domain.model


/**
 * Created by nurseiit.tursunkulov on 06.01.2024
 * CoinHistoryPrice
 */
data class CoinHistoryPrice(
    val id: String ,
    val prices: List<List<String>>,
) {
}