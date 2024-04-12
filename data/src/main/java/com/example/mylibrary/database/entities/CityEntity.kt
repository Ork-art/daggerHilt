package com.example.mylibrary.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.entities.City

@Entity(tableName =  "cities")
data class CityEntity(
    @PrimaryKey()
    @ColumnInfo(name = "id")
    val cityId: String,
    @ColumnInfo(name = "city_name")
    val cityName: String
){
    fun toDomain():City{
        return City(cityId, cityName)
    }
}