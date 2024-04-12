package com.example.domain.di

import com.example.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//class DomainModule {
//
//    @Provides
//    @Singleton
//    fun provideGetWeatherUseCase(repository: WeatherRepository) = GetWeatherUseCase(repository)
//}