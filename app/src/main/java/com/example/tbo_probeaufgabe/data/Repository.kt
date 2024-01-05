package com.example.tbo_probeaufgabe.data

import com.example.tbo_probeaufgabe.domain.model.Coin
import kotlinx.coroutines.flow.Flow


/**
 * Created by nurseiit.tursunkulov on 05.01.2024
 * Repository
 */
interface Repository {
    suspend fun getCoins(): Flow<List<Coin>>
}