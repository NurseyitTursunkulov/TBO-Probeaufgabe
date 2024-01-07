package com.example.tbo_probeaufgabe.domain

import android.util.Log
import com.example.tbo_probeaufgabe.data.Repository
import kotlinx.coroutines.delay


/**
 * Created by nurseiit.tursunkulov on 07.01.2024
 * GetCoinsUseCase
 */
class GetCoinsUseCase(private val repository: Repository) {
    suspend operator fun invoke() = repository.getCoins()

    suspend fun startTorefresh() {
        while (true) {
            delay(1000 )//todo make it configurable
            repository.refresh()
        }
    }
}