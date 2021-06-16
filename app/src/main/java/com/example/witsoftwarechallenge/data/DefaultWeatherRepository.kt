package com.example.witsoftwarechallenge.data

import com.example.witsoftwarechallenge.data.remote.WeatherService
import com.example.witsoftwarechallenge.model.WeatherResponse
import com.example.witsoftwarechallenge.util.API_KEY
import com.example.witsoftwarechallenge.util.Resource
import com.example.witsoftwarechallenge.util.Unit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DefaultWeatherRepository(private val service: WeatherService): IWeatherRepository {

    override fun getWeatherByCityName(cityName: String): Flow<Resource<WeatherResponse>> = flow {
        try {
            val currentWeather = service.getAsyncWeatherResponse(cityName, Unit.METRIC.name.lowercase(), API_KEY)
            emit(Resource.success(currentWeather))
        } catch (e: Exception) {
            emit(Resource.error(e.message!!, null))
        }
    }.flowOn(Dispatchers.IO)
}