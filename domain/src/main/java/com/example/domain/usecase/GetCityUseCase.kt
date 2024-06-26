package com.example.domain.usecase

import com.example.domain.entities.City
import com.example.domain.repository.WeatherRepository
import javax.inject.Inject

class GetCityUseCase  @Inject constructor(private val repository: WeatherRepository):BaseUseCase<Unit, List<City>> {

    override suspend fun invoke(input: Unit): List<City> {
        return repository.getCities()
    }
}