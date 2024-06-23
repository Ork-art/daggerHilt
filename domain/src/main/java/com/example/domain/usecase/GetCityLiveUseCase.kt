package com.example.domain.usecase

import com.example.domain.base.BaseFlowUseCase
import com.example.domain.base.BaseUseCase
import com.example.domain.entities.City
import com.example.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCityLiveUseCase  @Inject constructor(private val repository: WeatherRepository):
    BaseFlowUseCase<Unit, List<City>> {
    override fun invoke(input: Unit): Flow<List<City>> {
       return repository.getAllCitiesFlow()
    }

}