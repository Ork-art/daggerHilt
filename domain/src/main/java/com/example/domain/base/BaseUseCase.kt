package com.example.domain.base

import com.example.domain.entities.ErrorBody
import com.google.gson.Gson
import retrofit2.HttpException
import java.lang.Exception

interface BaseUseCase<T, R> {

   suspend fun call(input: T):R
   suspend fun invoke(input:T):RemoteResponse<R>{
      return try {
          RemoteResponse.Success(call(input))
      } catch (httpException:HttpException){
         val errorBodyString = httpException.response()?.errorBody()?.string()?:""
         val errorBody = Gson().fromJson(errorBodyString, ErrorBody::class.java)
         RemoteResponse.NetworkError(errorBody)
      }catch (e:Exception){
         RemoteResponse.NetworkError(ErrorBody("", 0))
      }
   }


}