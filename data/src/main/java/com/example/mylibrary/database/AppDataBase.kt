package com.example.mylibrary.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mylibrary.database.dao.CitiesDao
import com.example.mylibrary.database.entities.CityEntity


@Database(
    entities = [CityEntity::class], version = 3
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun citiesDao():CitiesDao
}