package com.example.myapitask1.repositories

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapitask1.api.ApiInterface
import com.example.myapitask1.database.CountriesDatabase
import com.example.myapitask1.database.DataEntity
import com.example.myapitask1.models.DataList
import java.lang.Exception

class MainVMRepository(private val apiInterface: ApiInterface
,private val countriesDatabase: CountriesDatabase?
) {

    private val mutabledata = MutableLiveData<DataList>()
    val liveData : LiveData<DataList>
        get() = mutabledata


    suspend fun getData(){
        val result= apiInterface.getCountriesData()
        val countriesEntityList = ArrayList<DataEntity>()
        if (result!=null)
        {
            //add response to database
            for (i in 0 until result.body()?.size!!)
            {
                val response = result.body()
                countriesEntityList.add(DataEntity(
                    0,
                    response?.get(i)?.currencies.toString()!!,
                    response?.get(i)?.flags?.svg.toString()!!,
                    response?.get(i)?.name?.common.toString()!!,
                    response?.get(i)?.population!!
                ))


            }
            try {
                countriesDatabase?.countriesDao()?.addCountries(countriesEntityList)
            }
            catch (e : Exception)
            {
                Log.w(TAG," exception occurred = "+e.message.toString())
            }

            //post to live data
            mutabledata.postValue(result.body())
            Log.d(ContentValues.TAG, "getCountryData: //// repo = "+ (result.body()?.size ))

        }
    }

    suspend fun getDataFromDatabase(){
       val countries =  countriesDatabase?.countriesDao()?.getCountries()
        for (i in 0 until (countries?.size!!))
        {
            Log.d(TAG, "exception occurred  data got = : "+countries.get(i).name.toString())
        }

    }
}