package com.example.witsoftwarechallenge.data.remote

import com.example.witsoftwarechallenge.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/weather")
    suspend fun getAsyncWeatherResponse(@Query("q") city: String,
                                        @Query("units") temperatureUnit: String,
                                        @Query("appid") apiKey: String): WeatherResponse
}