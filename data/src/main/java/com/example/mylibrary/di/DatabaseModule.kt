package com.example.mylibrary.di

import android.content.Context
import androidx.room.Room
import com.example.mylibrary.database.AppDatabase
import com.example.mylibrary.database.dao.CitiesDao
import com.example.mylibrary.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, Constants.WEATHER_DB)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCitiesDao(appDatabase: AppDatabase): CitiesDao = appDatabase.citiesDao()


}