package com.example.myapitask1

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.myapitask1.api.ApiInterface
import com.example.myapitask1.api.RetrofitHelper
import com.example.myapitask1.common.CountryApplication
import com.example.myapitask1.databinding.ActivityMainBinding
import com.example.myapitask1.models.DataListModelClass
import com.example.myapitask1.repositories.MainVMRepository
import com.example.myapitask1.viewmodels.MainViewModel
import com.example.myapitask1.viewmodels.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

     lateinit var mainBinding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)


        val controller = supportFragmentManager.findFragmentById(R.id.main_controller) as NavHostFragment
        navController = controller.navController


        val mainRepository = (application as CountryApplication).mainVMRepository
        val mainViewModel = ViewModelProvider(this, ViewModelFactory(mainRepository)).get(MainViewModel::class.java)
           val countriesList = ArrayList<DataListModelClass>()


//           try {
//               mainViewModel.liveList
//                   .observe(this@MainActivity, Observer {
//                       Log.d(TAG, "getCountryData: //// main list  = "+ it)
//
//                       for(i in 0 until (mainViewModel.liveList.value?.size!!)){
//                           val listValue = mainViewModel.liveList.value
//                           countriesList.add(DataListModelClass(
//                               listValue?.get(i)?.flags?.svg.toString(),
//                               listValue?.get(i)?.name.toString(),
//                               listValue?.get(i)?.population.toString(),
//                               listValue?.get(i)?.currencies.toString(),
//                               listValue?.get(i)?.currencies?.JOD?.symbol.toString(),
//                           ))
//                       }
//
//                       Log.d(TAG, "getCountryData: /// live list size "+mainViewModel.liveList.value?.size)
//                        Log.d(TAG, "getCountryData: /// countries size "+countriesList.size)
//                       val adapter = DataAdapter(countriesList)
//                       Log.d(TAG, "onCreate: //// list chnged"+ (it.get(0)?.name.toString() ))
//                   })
//           }catch (e:Exception){
//
//               Log.w("CRASH",e.message.toString())
//           }

       }
    }
