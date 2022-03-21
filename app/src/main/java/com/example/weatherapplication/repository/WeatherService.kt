package com.example.weatherapplication.repository

import com.example.weatherapplication.constants.Constants
import com.example.weatherapplication.interfaces.APIService
import com.example.weatherapplication.model.WeatherModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class WeatherService{
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.urlAPI)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val callRetro = retrofit.create(APIService::class.java)

    suspend fun getWeather(city: String?, unit: String, id: String): WeatherModel? {
        val response = callRetro.getWeatherJson(city,unit,id)
        return response.body()
    }
}
