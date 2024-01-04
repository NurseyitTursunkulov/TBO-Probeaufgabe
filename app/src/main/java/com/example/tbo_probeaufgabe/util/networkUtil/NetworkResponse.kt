package com.example.tbo_probeaufgabe.util.networkUtil

sealed class NetworkResponse<out T > {
    /**
     * Success response with body
     */
    data class Success<T >(val body: T) : NetworkResponse<T>()



    /**
     * For example, json parsing error
     */
    data class UnknownException(val exception: Exception) : NetworkResponse<Nothing>()
}