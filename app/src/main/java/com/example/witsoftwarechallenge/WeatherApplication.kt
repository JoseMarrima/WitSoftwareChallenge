package com.example.witsoftwarechallenge

import android.app.Application
import com.example.witsoftwarechallenge.data.IWeatherRepository

class WeatherApplication: Application() {
    val repository: IWeatherRepository
        get() = ServiceLocator.provideRepository()
}