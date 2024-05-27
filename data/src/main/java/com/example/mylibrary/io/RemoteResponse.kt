package com.example.shareprefer.data.io

sealed class RemoteResponse<out T> {

    data class Success<out T>(val result: T) : RemoteResponse<T>()
    data class NetworkError(val errorCode: Int) : RemoteResponse<Nothing>()

}
