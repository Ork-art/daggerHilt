package com.example.mylibrary.di

import com.example.domain.repository.WeatherRepository
import com.example.mylibrary.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface Binding {
        @Binds
        @Singleton
        fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
    }
}