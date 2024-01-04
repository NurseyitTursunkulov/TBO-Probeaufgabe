package com.example.tbo_probeaufgabe.util


/**
 * Created by nurseiit.tursunkulov on 04.01.2024
 * Result
 */
sealed class Result<out T>{
    data class Success<out T>(val data:T):Result<T>()
    data class Error(val errorType: ErrorType):Result<Nothing>()
    object Loading:Result<Nothing>()
}

val Result<*>.succeeded
    get() = this is Result.Success<*> && data != null