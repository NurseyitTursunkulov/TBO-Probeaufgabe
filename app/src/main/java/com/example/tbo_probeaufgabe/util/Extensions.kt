package com.example.tbo_probeaufgabe.util

import com.example.tbo_probeaufgabe.util.networkUtil.NetworkResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

/**
 * Created by nurseiit.tursunkulov on 07.01.24.
 */

suspend fun <T> Flow<T>.isEmpty(): Boolean {
    this.firstOrNull()?.let {
        if (it is List<*>)
            return it.isEmpty()
        return false
    } ?: return true
}

suspend fun <A> fetchResponse(
    networkFunction: suspend () -> A
) : NetworkResponse<A> {
    try {
        val res = networkFunction()
        return NetworkResponse.Success(res)
    } catch (e: retrofit2.HttpException) {
        if (e.code() == 429) {// todo test, put 429 to constants
            delay(1000)
            return fetchResponse(networkFunction)//todo add max retry
        }else {
            return NetworkResponse.UnknownException(e)
        }
    }
    catch (e: Exception) {
        return NetworkResponse.UnknownException(e)
    }
}