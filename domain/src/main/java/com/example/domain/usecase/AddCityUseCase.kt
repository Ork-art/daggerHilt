package com.example.domain.usecase

import com.example.domain.repository.WeatherRepository
import javax.inject.Inject

class AddCityUseCase  @Inject constructor(private val repository: WeatherRepository):BaseUseCase<String, Unit> {
    override  suspend fun invoke(input: String): Unit {
       return repository.addCity(input)
    }
}