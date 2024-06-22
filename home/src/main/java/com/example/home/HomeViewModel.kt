package com.example.home

import androidx.lifecycle.viewModelScope
import com.example.core.BaseViewModel
import com.example.domain.entities.Weather
import com.example.domain.usecase.AddCityUseCase
import com.example.domain.usecase.GetCityUseCase
import com.example.domain.usecase.GetCurrentTemperatureByCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val addCityWeatherUseCase: AddCityUseCase,
    private val getCityUseCase: GetCityUseCase,
    private val getCurrentTemperatureByCityUseCase: GetCurrentTemperatureByCityUseCase
) : BaseViewModel<HomeState, HomeEvent>() {

    fun addCityName(cityName: String) {
        viewModelScope.launch {
            addCityWeatherUseCase.invoke(cityName)
        }
    }

    fun getCities(){
        viewModelScope.launch {
            val cities = getCityUseCase.invoke(Unit)
            val weathersAsyncList= cities.map{
                async { getCurrentTemperatureByCityUseCase.invoke(it) }
            }

            val weathers = weathersAsyncList.map {
                it.await()
            }
            postState(HomeState.Weathers(weathers))
        }
    }


}

sealed class HomeState {
    class WeathersResult(val result: String) : HomeState()
    class Weathers(val weather:List<Weather>) : HomeState()
}

sealed class HomeEvent {
    class ValidateResult(val result: String) : HomeEvent()
}