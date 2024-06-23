package com.example.domain.usecase

import com.example.domain.base.BaseUseCase
import com.example.domain.repository.WeatherRepository
import javax.inject.Inject

class AddCityUseCase  @Inject constructor(private val repository: WeatherRepository):
    BaseUseCase<String, Unit> {
    override  suspend fun call(input: String): Unit {
       return repository.addCity(input)
    }
}