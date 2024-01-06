package com.example.tbo_probeaufgabe.data

import com.example.tbo_probeaufgabe.data.local.LocalDataSource
import com.example.tbo_probeaufgabe.data.remote.RemoteDataSource
import com.example.tbo_probeaufgabe.data.remote.model.CoinHistoryLocalModel.Companion.toDomainModel
import com.example.tbo_probeaufgabe.domain.model.Coin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * Created by nurseiit.tursunkulov on 06.01.2024
 * RepoImpl
 */
class RepoImpl(val localDataSource: LocalDataSource, val remoteDataSource: RemoteDataSource) :
    Repository {
    override suspend fun getCoins(): Flow<List<Coin>> {
        return localDataSource.getCoins().map {

            it.map {
                it.toDomainModel().apply {
                    historyPrice = localDataSource.getCoinHistory(it.id)?.let {
                        toDomainModel(it)
                    }
                }
            }
        }
    }
}