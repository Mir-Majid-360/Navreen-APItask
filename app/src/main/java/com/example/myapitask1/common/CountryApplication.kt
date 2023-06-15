package com.example.myapitask1.common

import android.app.Application
import com.example.myapitask1.api.ApiInterface
import com.example.myapitask1.api.RetrofitHelper
import com.example.myapitask1.database.CountriesDatabase
import com.example.myapitask1.repositories.MainVMRepository

class CountryApplication : Application() {

    lateinit var mainVMRepository: MainVMRepository
    override fun onCreate() {
        super.onCreate()
        intialize()
    }

    private fun intialize() {
        val apiInterface = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        val database = CountriesDatabase.getCountries(applicationContext)
        mainVMRepository = MainVMRepository(apiInterface, database)

    }
}