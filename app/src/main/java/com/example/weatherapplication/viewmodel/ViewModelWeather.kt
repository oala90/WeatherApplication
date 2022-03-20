package com.example.weatherapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.constants.Constants.Companion.apiID
import com.example.weatherapplication.constants.Constants.Companion.units
import com.example.weatherapplication.constants.Constants.Companion.urlAPI
import com.example.weatherapplication.interfaces.APIService
import com.example.weatherapplication.model.ModelWeather
import com.example.weatherapplication.model.WeatherList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ViewModelWeather: ViewModel() {

    var city = MutableLiveData("")
    var id = apiID
    var unit = units
    var myResponse: MutableLiveData<ModelWeather> = MutableLiveData()
    var retrofit = Retrofit.Builder()
        .baseUrl(urlAPI)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    var callRetro = retrofit.create(APIService::class.java)
    var itemResponse: MutableLiveData<WeatherList> = MutableLiveData()
    var isBusy = MutableLiveData(false)

    fun callAPI(){
        isBusy.value=true

        viewModelScope.launch(Dispatchers.IO) {

            val response = callRetro.getWeatherJson(city.value,unit,id)

            withContext(Dispatchers.Main){
                myResponse.value = response.body()
                isBusy.value=false
            }
        }
    }
}