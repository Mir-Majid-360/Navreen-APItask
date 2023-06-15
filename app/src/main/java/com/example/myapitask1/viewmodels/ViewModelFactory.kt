package com.example.myapitask1.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapitask1.repositories.MainVMRepository

class ViewModelFactory(private val mainVMRepository: MainVMRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(mainVMRepository) as T
    }
}