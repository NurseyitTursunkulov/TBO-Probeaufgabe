package com.example.tbo_probeaufgabe.data.local

import com.example.tbo_probeaufgabe.data.local.db.Dao
import com.example.tbo_probeaufgabe.data.remote.model.CoinApiModel
import com.example.tbo_probeaufgabe.data.remote.model.CoinHistoryLocalModel
import kotlinx.coroutines.flow.Flow


/**
 * Created by nurseiit.tursunkulov on 06.01.2024
 * LocalDataSourceImpl
 */
class LocalDataSourceImpl(val dao: Dao) :LocalDataSource{
    override suspend fun getCoins(): Flow<List<CoinApiModel>> {
        return dao.getCoins()
    }

    override suspend fun insertCoins(coins: List<CoinApiModel>) {
        return dao.insertCoins(coins)
    }

    override suspend fun insertCoinHistory(coinHistory: CoinHistoryLocalModel) {
        dao.insertCoinHistory(coinHistory)
    }

    override suspend fun getCoinHistoryFlow(id: String): Flow<CoinHistoryLocalModel> {
        return dao.getCoinHistoryFlow(id)
    }

    override suspend fun getCoinHistory(id: String): CoinHistoryLocalModel? {
        return dao.getCoinHistory(id)
    }
}