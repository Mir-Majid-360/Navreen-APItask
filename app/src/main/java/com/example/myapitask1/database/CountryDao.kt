package com.example.myapitask1.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapitask1.models.DataList
import com.example.myapitask1.models.DataListModelClass


@Dao
interface CountryDao {

    @Insert
    suspend fun addCountries(countries: List<DataEntity>?)

    @Query("SELECT * FROM countries")
    suspend fun getCountries() : List<DataEntity>
}