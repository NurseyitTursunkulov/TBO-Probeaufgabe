package com.example.tbo_probeaufgabe

import com.example.tbo_probeaufgabe.domain.model.Coin


data class  CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
