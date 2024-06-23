package com.example.domain.usecase

import com.example.domain.base.BaseUseCase
import com.example.domain.entities.City
import com.example.domain.entities.Weather
import com.example.domain.repository.WeatherRepository
import javax.inject.Inject

class GetCurrentTemperatureByCityUseCase  @Inject constructor(private val repository: WeatherRepository):
    BaseUseCase<City, Weather> {

    override suspend fun call(input: City): Weather {
        return repository.getCurrentWeatherByCity(input)
    }
}