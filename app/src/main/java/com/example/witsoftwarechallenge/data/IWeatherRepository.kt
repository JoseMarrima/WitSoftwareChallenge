package com.example.witsoftwarechallenge.data

import com.example.witsoftwarechallenge.util.Resource
import com.example.witsoftwarechallenge.model.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface IWeatherRepository {
    fun getWeatherByCityName(cityName: String): Flow<Resource<WeatherResponse>>
}