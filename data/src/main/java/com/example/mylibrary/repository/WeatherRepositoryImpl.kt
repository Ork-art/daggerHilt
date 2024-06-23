package com.example.mylibrary.repository

import com.example.domain.entities.City
import com.example.domain.entities.Weather
import com.example.domain.entities.WeatherUnits
import com.example.domain.entities.remote.WeatherResponse
import com.example.domain.repository.WeatherRepository
import com.example.mylibrary.BuildConfig
import com.example.mylibrary.database.dao.CitiesDao
import com.example.mylibrary.database.entities.CityEntity
import com.example.shareprefer.util.Api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val citiesDao: CitiesDao,
    private val api: Api
) : WeatherRepository {


    override suspend fun addCity(cityName: String) {
        citiesDao.insert(CityEntity(cityName, cityName))
    }

    override suspend fun getCities(): List<City> {
        return citiesDao.getAllCities().map { cityEntity ->
            cityEntity.toDomain()
        }
    }

    override suspend fun getCurrentWeatherByCity(city: City): Weather {
        return api.getCurrentWeather(cityName = city.cityName, BuildConfig.ApiKey, WeatherUnits.METRIC.name).toDomain(city)
    }

    override fun getAllCitiesFlow(): Flow<List<City>> {
        return citiesDao.getAllCitiesFlow().map {cityEntity ->
            cityEntity.map {
                it.toDomain()
            }
        }
    }
}

