package com.example.shareprefer.data.io

import com.example.domain.helper.Constants
import retrofit2.HttpException

suspend fun <T> apiCall(call: suspend () -> T): RemoteResponse<T> {
    return try {
        RemoteResponse.Success(call.invoke())
    } catch (httpException: HttpException) {
        RemoteResponse.NetworkError(httpException.code())
    } catch (e: Exception) {
        RemoteResponse.NetworkError(Constants.NETWORK_RESPONSE_EXCEPTION_CODE)
    }
}