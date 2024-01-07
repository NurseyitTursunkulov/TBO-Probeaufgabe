package com.example.tbo_probeaufgabe.util.networkUtil

sealed class NetworkResponse<out T > {

    data class Success<T >(val body: T) : NetworkResponse<T>()

    data class UnknownException(val exception: Exception) : NetworkResponse<Nothing>()//todo rename
}