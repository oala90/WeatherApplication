package com.example.weatherapplication.interfaces

import com.example.weatherapplication.model.ModelWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("find")
    suspend fun getWeatherJson(
        @Query("q") city: String?,
        @Query("units") unit: String,
        @Query("appid") apiId: String
    ): Response<ModelWeather>
}