package com.example.domain.repository

import com.example.domain.entities.City
import com.example.domain.entities.Weather
import com.example.domain.entities.remote.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
   suspend fun addCity(cityName:String)
   suspend fun getCities():List<City>
   suspend fun getCurrentWeatherByCity(city: City): Weather

   fun getAllCitiesFlow(): Flow<List<City>>
}