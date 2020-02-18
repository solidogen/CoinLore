package com.spyrdonapps.coinlore.utils.state

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error<out T>(val error: String? = null, val fallbackData: T? = null) : Result<T>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[error=$error, fallbackData=$fallbackData]"
            Loading -> "Loading"
        }
    }
}