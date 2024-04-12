package com.example.mylibrary.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mylibrary.database.entities.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CitiesDao {

    @Query("select * from cities")
    suspend fun getAllCities(): List<CityEntity>

    @Query("select * from cities where id == :itemId")
    fun getCityById(itemId: String): CityEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cityEntity: CityEntity)

    @Delete
    fun delete(cityEntity: CityEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(cityEntity: CityEntity)
}