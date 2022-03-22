package com.example.weatherapplication.viewmodel

import com.example.weatherapplication.BuildConfig
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.repository.WeatherService
import com.example.weatherapplication.constants.Constants.Companion.units
import com.example.weatherapplication.model.WeatherList
import com.example.weatherapplication.model.WeatherModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModelWeather: ViewModel() {

    private var _city = MutableLiveData("")
    val city = _city
    private var _myResponse: MutableLiveData<WeatherModel?> = MutableLiveData()
    val myResponse = _myResponse
    private val objWeather = WeatherService()
    private var _itemResponse: MutableLiveData<WeatherList> = MutableLiveData()
    val itemResponse = _itemResponse
    private var _code = MutableLiveData(0)
    val code = _code
    private var _isBusy = MutableLiveData(false)
    val isBusy = _isBusy

    fun callAPI(){
        isBusy.value=true
        viewModelScope.launch(Dispatchers.IO) {
            val response = objWeather.getWeather(city.value,units, BuildConfig.API_KEY)
            withContext(Dispatchers.Main){
                myResponse.value = response
                code.value = myResponse.value?.cod?.toInt()
                isBusy.value=false
            }
        }
    }
}