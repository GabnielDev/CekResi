package com.example.cekresi.base

import com.example.cekresi.helper.SingleEvent

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: SingleEvent<String>? = null
) {
    class Success<T>(data: T?) : NetworkResult<T>(data)

    class Error<T>(message: SingleEvent<String>?, data: T? = null) : NetworkResult<T>(data, message)

    class Loading<T> : NetworkResult<T>()
}
