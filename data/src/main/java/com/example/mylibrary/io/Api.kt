package com.example.shareprefer.util

import com.example.domain.entities.remote.WeatherResponse
import com.example.domain.repository.WeatherRepository
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {
    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") cityName: String,
        @Query("appid") appId: String,
        @Query("units") unitsType: String
    ): WeatherResponse
}
