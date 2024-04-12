package com.example.home

import androidx.lifecycle.viewModelScope
import com.example.core.BaseViewModel
import com.example.domain.entities.Weather
import com.example.domain.usecase.AddCityUseCase
import com.example.domain.usecase.GetCityUseCase
import com.example.domain.usecase.GetCurrentTemperatureByCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val addCityWeatherUseCase: AddCityUseCase,
    private val getCityUseCase: GetCityUseCase,
    private val getCurrentTemperatureByCityUseCase: GetCurrentTemperatureByCityUseCase
) : BaseViewModel<HomeState, HomeEvent>() {

//    private var weatherRepository= WeatherRepositoryImpl()
//    private var weatherUseCase = GetWeatherUseCase(weatherRepository)


    fun addCityName(cityName: String) {
        viewModelScope.launch {
            addCityWeatherUseCase.invoke(cityName)
        }
    }

    fun getCities(){
        viewModelScope.launch {
            val cities = getCityUseCase.invoke(Unit)
            val weathers= cities.map{
                getCurrentTemperatureByCityUseCase.invoke(it)
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