package com.example.domain.entities.remote

import com.example.domain.entities.City
import com.example.domain.entities.Weather
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("main")
    val mainTemperature: Main
) {
    fun toDomain(city: City): Weather {
        return Weather(city.cityName, mainTemperature.temp, mainTemperature.feelsLike, mainTemperature.humidity)
    }
}

data class Main(
    @SerializedName("temp")
    val temp: Float,
    @SerializedName("feels_like")
    val feelsLike: Float,
    @SerializedName("temp_min")
    val tempMin: Float,
    @SerializedName("temp_max")
    val tempMax: Float,
    @SerializedName("pressure")
    val pressure: Float,
    @SerializedName("humidity")
    val humidity: Float

)
