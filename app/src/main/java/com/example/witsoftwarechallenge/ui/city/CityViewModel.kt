package com.example.witsoftwarechallenge.ui.city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.example.witsoftwarechallenge.data.IWeatherRepository
import com.example.witsoftwarechallenge.util.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart

class CityViewModel(private val repository: IWeatherRepository) : ViewModel() {

    private val _cityName = MutableLiveData<String>()

    fun setCityName(cityName: String) {
        _cityName.value = cityName
    }

    val currentWeather = _cityName.switchMap {
        repository.getWeatherByCityName(it)
            .onStart { Resource.loading(null) }
            .catch { exception -> Resource.error(exception.message!!, null) }
            .asLiveData()
    }

}