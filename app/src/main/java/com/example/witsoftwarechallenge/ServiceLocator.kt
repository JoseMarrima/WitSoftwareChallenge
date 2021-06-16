package com.example.witsoftwarechallenge

import com.example.witsoftwarechallenge.data.DefaultWeatherRepository
import com.example.witsoftwarechallenge.data.remote.WeatherService
import com.example.witsoftwarechallenge.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceLocator {
    @Volatile
    private var defaultRepository: DefaultWeatherRepository? = null


    fun provideRepository(): DefaultWeatherRepository {
        synchronized(this) {
            return defaultRepository ?: createRepository()
        }
    }

    private fun createRepository(): DefaultWeatherRepository {
        val newRepo = DefaultWeatherRepository(provideWeatherService())
        defaultRepository = newRepo
        return newRepo
    }

    private fun provideWeatherService(): WeatherService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(WeatherService::class.java)
    }
}