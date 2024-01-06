package com.example.tbo_probeaufgabe.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.tbo_probeaufgabe.data.remote.model.CoinApiModel
import com.example.tbo_probeaufgabe.data.remote.model.CoinHistoryLocalModel
import kotlinx.coroutines.flow.Flow


/**
 * Created by nurseiit.tursunkulov on 05.01.2024
 * Dao
 */
@Dao
abstract class Dao {
    @Transaction
    @Query("SELECT * FROM coin_data")
    abstract fun getCoins(): Flow<List<CoinApiModel>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCoins(coins: List<CoinApiModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertCoinHistory(coinHistory: CoinHistoryLocalModel)

    @Query("SELECT * FROM coin_history WHERE id = :id")
    abstract fun getCoinHistory(id: String): Flow<CoinHistoryLocalModel>

}