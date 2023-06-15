package com.example.myapitask1

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapitask1.api.ApiInterface
import com.example.myapitask1.api.RetrofitHelper
import com.example.myapitask1.common.CountryApplication
import com.example.myapitask1.database.CountriesDatabase
import com.example.myapitask1.databinding.ScreenOneFragmentBinding
import com.example.myapitask1.models.DataListModelClass
import com.example.myapitask1.repositories.MainVMRepository
import com.example.myapitask1.viewmodels.MainViewModel
import com.example.myapitask1.viewmodels.ViewModelFactory

class ScreenOneFragment : Fragment() {
    lateinit var screenOneFragmentBinding: ScreenOneFragmentBinding
    val countriesList = ArrayList<DataListModelClass>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        screenOneFragmentBinding = ScreenOneFragmentBinding.inflate(inflater, container, false)
        return screenOneFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiInterface = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        val database = CountriesDatabase.getCountries(activity?.applicationContext!!)

        val mainVMRepository = MainVMRepository(apiInterface, database)
        val mainViewModel = ViewModelProvider(this, ViewModelFactory(mainVMRepository)).get(
            MainViewModel::class.java)

//        val mainRepository = (activity?.application as CountryApplication).mainVMRepository
//        val mainViewModel = ViewModelProvider(this, ViewModelFactory(mainRepository)).get(MainViewModel::class.java)
//        val countriesList = ArrayList<DataListModelClass>()

        try {
            mainViewModel.liveList
                .observe(viewLifecycleOwner, Observer {
                    Log.d(TAG, "getCountryData: //// main list  = "+ it)

                    for(i in 0 until (mainViewModel.liveList.value?.size!!)){
//                        val jsonResponse = mainViewModel.liveList.value!!.get(i) as JsonObject
                        val listValue = mainViewModel.liveList.value

//                        var symbolValue : String ? = null
//                        var currencyValue : String ? = null
//
//                        if (jsonResponse["currencies"].asJsonObject != null) {
//                            val currencyObj = jsonResponse["currencies"].asJsonObject
//                            var key = currencyObj.keySet()
//                            var currencykey = ""
//                            for (i in key) {
//                                currencykey = i
//                                currencyValue = i
//                            }
//                            if (currencyObj[currencykey] != null) {
//                                var obj = currencyObj[currencykey].asJsonObject
//                                symbolValue = obj["symbol"].asString
//                            } else {
//                                symbolValue = ""
//                            }
//                        }
                        countriesList.add(DataListModelClass(
                            listValue?.get(i)?.flags?.svg.toString(),
                            listValue?.get(i)?.name?.common.toString(),
                            listValue?.get(i)?.population.toString(),
                            listValue?.get(i)?.currencies?.JOD.toString(),
                            listValue?.get(i)?.currencies?.JOD?.symbol.toString(),
                        ))
                    }

                    Log.d(TAG, "getCountryData: /// live list size "+mainViewModel.liveList.value?.size)
                    Log.d(TAG, "getCountryData: /// countries size "+countriesList.size)
                    setDataRv()
                })
        }catch (e:Exception){

            Log.w("CRASH",e.message.toString())
        }
        Log.d(TAG, "onViewCreated: //")

        screenOneFragmentBinding.nextBT.setOnClickListener {
            findNavController().navigate(R.id.action_screenOneFragment_to_screenTwoFragment)
        }
    }

    private fun setDataRv() {
        if (countriesList.size!=0)
        {
            val adapter = DataAdapter(countriesList)
            screenOneFragmentBinding.dataRV.adapter =adapter
        }
    }
}