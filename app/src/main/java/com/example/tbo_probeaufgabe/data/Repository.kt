package com.example.tbo_probeaufgabe.data

import com.example.tbo_probeaufgabe.domain.model.Coin
import kotlinx.coroutines.flow.Flow
import com.example.tbo_probeaufgabe.util.Result


/**
 * Created by nurseiit.tursunkulov on 05.01.2024
 * Repository
 */
interface Repository {
    suspend fun getCoins(): Flow<Result<List<Coin>>>
    suspend fun refresh():Result<Unit>
}