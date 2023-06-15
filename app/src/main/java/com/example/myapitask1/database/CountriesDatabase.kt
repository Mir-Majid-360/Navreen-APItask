package com.example.myapitask1.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapitask1.models.DataList
import com.example.myapitask1.models.DataListModelClass

@Database(entities = [DataEntity::class], version = 1)
abstract class CountriesDatabase : RoomDatabase() {

    //fun c to access dao
    abstract fun countriesDao() : CountryDao
    companion object{
        @Volatile
        //singleton code


        private var INSTANCE : CountriesDatabase? = null

        fun getCountries(context: Context) : CountriesDatabase {
           if (INSTANCE==null)
           {
               synchronized(this){
                   INSTANCE = Room.databaseBuilder(context,
                       CountriesDatabase::class.java,
                       "countriesdb")
                       .build()
               }
           }
            return INSTANCE!!
        }
    }
}

//// Database class after the version update.
//@Database(
//    version = 2,
//    entities = [DataEntity::class],
//    autoMigrations = [
//        AutoMigration (from = 1, to = 2)
//    ]
//)
//abstract class CountriesDatabase : RoomDatabase() {
//
//    //fun c to access dao
//    abstract fun countriesDao() : CountryDao
//    companion object{
//        @Volatile
//        //singleton code
//
//
//        private var INSTANCE : CountriesDatabase? = null
//
//        fun getCountries(context: Context) : CountriesDatabase {
//            if (INSTANCE==null)
//            {
//                synchronized(this){
//                    INSTANCE = Room.databaseBuilder(context,
//                        CountriesDatabase::class.java,
//                        "countriesDB")
//                        .build()
//                }
//            }
//            return INSTANCE!!
//        }
//    }
//}
