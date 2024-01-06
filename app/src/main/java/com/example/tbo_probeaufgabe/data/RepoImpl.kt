package com.example.tbo_probeaufgabe.data

import com.example.tbo_probeaufgabe.data.local.LocalDataSource
import com.example.tbo_probeaufgabe.data.remote.RemoteDataSource
import com.example.tbo_probeaufgabe.data.remote.model.CoinHistoryLocalModel.Companion.toDomainModel
import com.example.tbo_probeaufgabe.domain.model.Coin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map


/**
 * Created by nurseiit.tursunkulov on 06.01.2024
 * RepoImpl
 */
class RepoImpl(val localDataSource: LocalDataSource, val remoteDataSource: RemoteDataSource) :
    Repository {
    override suspend fun getCoins(): Flow<List<Coin>> {
        val coinsFromCahs = localDataSource.getCoins().map {coinApiModelList->
            coinApiModelList.map {coinApiModelList->
                coinApiModelList.toDomainModel().apply {
                    historyPrice = localDataSource.getCoinHistory(coinApiModelList.id)?.let {
                        toDomainModel(it)
                    }
                }
            }
        }
        if (coinsFromCahs.isEmpty()) {
            val coinsFromNetwork = remoteDataSource.getCoins()
            localDataSource.insertCoins(coinsFromNetwork)
            coinsFromNetwork.map {coinApiModel->
                coinApiModel.apply {
                    remoteDataSource.getCoinHistory(coinApiModel.id).let {coinHistory->
                        localDataSource.insertCoinHistory(coinHistory.toLocalModel(coinApiModel.id))
                    }
                }
            }
        }
        return coinsFromCahs
    }
}

suspend fun <T> Flow<T>.isEmpty(): Boolean{
    this.firstOrNull()?.let {
        return false
    }?: return true
}