package com.example.tbo_probeaufgabe.data

import android.util.Log
import com.example.tbo_probeaufgabe.data.local.LocalDataSource
import com.example.tbo_probeaufgabe.data.remote.RemoteDataSource
import com.example.tbo_probeaufgabe.data.remote.fetchResponse
import com.example.tbo_probeaufgabe.data.remote.model.CoinHistoryLocalModel.Companion.toDomainModel
import com.example.tbo_probeaufgabe.domain.model.Coin
import com.example.tbo_probeaufgabe.util.networkUtil.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


/**
 * Created by nurseiit.tursunkulov on 06.01.2024
 * RepoImpl
 */
class RepoImpl(val localDataSource: LocalDataSource, val remoteDataSource: RemoteDataSource) :
    Repository {
    override suspend fun getCoins(): Flow<List<Coin>> = flow {
        val coinsFromCahs = localDataSource.getCoins()
            .map { coinApiModelList ->
                coinApiModelList.map { coinApiModelList ->
                    coinApiModelList.toDomainModel().apply {
                        historyPrice = localDataSource.getCoinHistory(coinApiModelList.id)?.let {
                            toDomainModel(it)
                        }
                    }
                }
            }
        Log.d("nurs", "34 RepoImpl getCoins from repo ${coinsFromCahs.isEmpty()}")
        if (coinsFromCahs.isEmpty()) {
            val response = fetchResponse(remoteDataSource::getCoins)
            when (response) {
                is NetworkResponse.Success -> {
                    val coinList = response.body
                    localDataSource.insertCoins(coinList)
                    emit(coinList.map { it.toDomainModel() })
                    coinList.map { coinApiModel ->
                        coinApiModel.apply {
                            val coinHistoryResponse = fetchResponse { remoteDataSource.getCoinHistory(coinApiModel.id) }
                            when(coinHistoryResponse) {
                                is NetworkResponse.Success -> {
                                    localDataSource.insertCoinHistory(
                                        coinHistoryResponse.body.toLocalModel(coinApiModel.id)
                                    )
                                }
                                else -> {
                                    Log.d("nurs", "${coinHistoryResponse.toString()}")//todo handle error case
                                }
                            }
                        }
                    }
                }

                else -> {
                    Log.d("nurs", "${response.toString()}")
                }
            }
        }
        coinsFromCahs.collect {
            emit(it)
        }
        Log.d("nurs", "RepoImpl 53 getCoins from repo ${coinsFromCahs.firstOrNull()}")
    }
}

suspend fun <T> Flow<T>.isEmpty(): Boolean {
    this.firstOrNull()?.let {
        if (it is List<*>)
            return it.isEmpty()
        return false
    } ?: return true
}