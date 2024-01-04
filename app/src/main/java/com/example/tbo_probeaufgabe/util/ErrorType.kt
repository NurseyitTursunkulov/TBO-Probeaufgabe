package com.example.tbo_probeaufgabe.util


/**
 * Created by nurseiit.tursunkulov on 04.01.2024
 * ErrorType
 */
sealed class ErrorType(message: String): Exception(message){
    data class NetworkUnavailableException(val code: Int, val netMessage: String):ErrorType(netMessage)
    data class ServerError(val code: Int, val apiMessage: String):ErrorType(apiMessage)

    data class TooManyRequestExceptions(val code: Int, val apiMessage: String):ErrorType(apiMessage)
    object UnknownError:ErrorType("Unknown error")
}
