package com.example.myapitask1.database

import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import com.example.myapitask1.models.Currencies
import com.example.myapitask1.models.Flags
import com.example.myapitask1.models.Name


@Entity (tableName = "countries" )
data class DataEntity (
    @PrimaryKey(autoGenerate = true)
    val countryId : Int,
    val currencies: String,
    val flags: String,
    val name: String,
    val population: Int,
)
