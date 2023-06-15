package com.example.myapitask1.api

import com.example.myapitask1.models.DataList
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("all")
    suspend fun getCountriesData() : Response<DataList>
}