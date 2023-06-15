package com.example.myapitask1.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapitask1.models.DataList
import com.example.myapitask1.repositories.MainVMRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val mainVMRepository: MainVMRepository) : ViewModel() {

   init{
       viewModelScope.launch(Dispatchers.IO) {
           //getting data and saving to backend
           mainVMRepository.getData()
           mainVMRepository.getDataFromDatabase()
           Log.d(TAG, "getCountryData: //// size = "+ (mainVMRepository.liveData.value?.size ))
       }
    }

    val liveList : LiveData<DataList>
        get() = mainVMRepository.liveData


}