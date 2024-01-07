package com.example.tbo_probeaufgabe

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tbo_probeaufgabe.data.RepoImpl
import com.example.tbo_probeaufgabe.data.local.LocalDataSource
import com.example.tbo_probeaufgabe.data.local.test.coinHistoryList
import com.example.tbo_probeaufgabe.data.local.test.coinList
import com.example.tbo_probeaufgabe.data.local.test.coinsWithHistory
import com.example.tbo_probeaufgabe.data.remote.RemoteDataSource
import com.example.tbo_probeaufgabe.data.remote.model.CoinApiModel
import com.example.tbo_probeaufgabe.data.remote.model.CoinHistoryLocalModel
import com.example.tbo_probeaufgabe.domain.model.Coin
import com.example.tbo_probeaufgabe.util.Result
import com.example.tbo_probeaufgabe.util.networkUtil.NetworkResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by nurseiit.tursunkulov on 07.01.2024
 * RepoImplTest
 */
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class RepoImplTest {

    @Test
    fun first_lauch_case_test_repository() = runTest {
        /**given
         * ---------------------------------------------------*/
        val coinFlowInternal = MutableStateFlow<List<CoinApiModel>>(listOf())
        val coinFlow: Flow<List<CoinApiModel>> = coinFlowInternal
        val coinHistory: MutableList<CoinHistoryLocalModel> = mutableListOf()

        val remoteDataSourceFake = mockk<RemoteDataSource>()
        val localDataSourceFake = mockk<LocalDataSource>()
        coEvery { remoteDataSourceFake.getCoins() } returns  NetworkResponse.Success(coinList)
        coEvery { localDataSourceFake.getCoins() } returns coinFlow
        coEvery { localDataSourceFake.insertCoins(coinList) } coAnswers {coinFlowInternal.emit(coinList) }
        coEvery { localDataSourceFake.insertCoinHistory(any()) } coAnswers { coinHistory.add(firstArg()) }
        coEvery { localDataSourceFake.getCoinHistory(any()) } coAnswers { coinHistory.find { it.id == firstArg() } }
        coEvery { remoteDataSourceFake.getCoinHistory(any()) } coAnswers {
            NetworkResponse.Success(coinHistoryList.find { it.id == firstArg() }
                ?.let { CoinHistoryLocalModel.toApiModel(it) }!!)
        }
        val repository = RepoImpl(localDataSourceFake, remoteDataSourceFake)

        val values = mutableListOf<Result<List<Coin>>>()
        /** when
         * ---------------------------------------------------*/

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            repository.getCoins().toList(values)
        }

        /** then
         * ---------------------------------------------------*/

        coVerify { localDataSourceFake.getCoins() }
        coVerify { remoteDataSourceFake.getCoins() }
        coVerify { localDataSourceFake.insertCoins(coinList) }
        assertEquals(Result.Success(coinList.map { it.toDomainModel() }), values[0]) // Assert on the list contents
        coinList.forEach {
            coVerify { remoteDataSourceFake.getCoinHistory(it.id) }
            coinHistoryList.find { coinHistoryLocalModel -> coinHistoryLocalModel.id == it.id }?.let { coinHistoryLocalModel ->
                coVerify { localDataSourceFake.insertCoinHistory(coinHistoryLocalModel) }
            }
        }

        assertEquals(Result.Success(coinsWithHistory), values[1])
    }

    @Test
    fun first_lauch_case_test_repository_and_getCoins_returns_error() = runTest {
        /** given
         * ---------------------------------------------------*/
        val coinFlowInternal = MutableStateFlow<List<CoinApiModel>>(listOf())
        val coinFlow: Flow<List<CoinApiModel>> = coinFlowInternal
        val coinHistory: MutableList<CoinHistoryLocalModel> = mutableListOf()

        val remoteDataSourceFake = mockk<RemoteDataSource>()
        val localDataSourceFake = mockk<LocalDataSource>()
        val exception = Exception("test exception")
        coEvery { remoteDataSourceFake.getCoins() } returns NetworkResponse.UnknownException(exception)
        coEvery { localDataSourceFake.getCoins() } returns coinFlow
        coEvery { localDataSourceFake.insertCoins(coinList) } coAnswers {coinFlowInternal.emit(coinList) }
        coEvery { localDataSourceFake.insertCoinHistory(any()) } coAnswers { coinHistory.add(firstArg()) }
        coEvery { localDataSourceFake.getCoinHistory(any()) } coAnswers { coinHistory.find { it.id == firstArg() } }
        coEvery { remoteDataSourceFake.getCoinHistory(any()) } coAnswers {
            NetworkResponse.Success(coinHistoryList.find { it.id == firstArg() }
                ?.let { CoinHistoryLocalModel.toApiModel(it) }!!)
        }
        val repository = RepoImpl(localDataSourceFake, remoteDataSourceFake)

        val values = mutableListOf<Result<List<Coin>>>()
        /** when
         * ---------------------------------------------------*/

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            repository.getCoins().toList(values)
        }

        /** then
         * ---------------------------------------------------*/

        coVerify { localDataSourceFake.getCoins() }
        coVerify { remoteDataSourceFake.getCoins() }
        coVerify(exactly = 0) { localDataSourceFake.insertCoins(coinList) }
        assertEquals(listOf<List<Coin>>(), values[0]) // Assert on the list contents
    }
}