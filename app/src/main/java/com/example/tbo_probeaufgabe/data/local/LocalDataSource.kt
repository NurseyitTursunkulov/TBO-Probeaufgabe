package com.example.tbo_probeaufgabe.data.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.tbo_probeaufgabe.data.remote.model.CoinApiModel
import com.example.tbo_probeaufgabe.data.remote.model.CoinHistoryLocalModel
import kotlinx.coroutines.flow.Flow


/**
 * Created by nurseiit.tursunkulov on 06.01.2024
 * LocalDataSource
 */
interface LocalDataSource {

   suspend fun getCoins(): Flow<List<CoinApiModel>>

   suspend fun insertCoins(coins: List<CoinApiModel>)

   suspend fun insertCoinHistory(coinHistory: CoinHistoryLocalModel)

   suspend fun getCoinHistoryFlow(id: String): Flow<CoinHistoryLocalModel>

   suspend fun getCoinHistory(id: String): CoinHistoryLocalModel?
}