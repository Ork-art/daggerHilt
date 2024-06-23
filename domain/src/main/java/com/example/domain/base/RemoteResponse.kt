package com.example.domain.base

import com.example.domain.entities.ErrorBody

sealed class RemoteResponse <out T>{
    data class Success<out T>(val result:T):RemoteResponse<T>()
    data class NetworkError(val errorBody: ErrorBody):RemoteResponse<Nothing>()
}