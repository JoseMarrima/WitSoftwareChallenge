package com.example.witsoftwarechallenge.ui.city

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.witsoftwarechallenge.data.IWeatherRepository

@Suppress("UNCHECKED_CAST")
class CityViewModelFactory(private val repository: IWeatherRepository):
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityViewModel::class.java)) {
            return CityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
