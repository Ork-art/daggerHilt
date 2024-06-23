package com.example.home

import androidx.lifecycle.viewModelScope
import com.example.core.BaseViewModel
import com.example.domain.base.RemoteResponse
import com.example.domain.entities.City
import com.example.domain.entities.Weather
import com.example.domain.usecase.AddCityUseCase
import com.example.domain.usecase.GetCityLiveUseCase
import com.example.domain.usecase.GetCityUseCase
import com.example.domain.usecase.GetCurrentTemperatureByCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val addCityWeatherUseCase: AddCityUseCase,
    private val getCurrentTemperatureByCityUseCase: GetCurrentTemperatureByCityUseCase,
    private val getCityLiveUseCase: GetCityLiveUseCase
) : BaseViewModel<HomeState, HomeEvent>() {

    fun addCityName(cityName: String) {
        viewModelScope.launch(globalCoroutineExceptionHandler) {
            addCityWeatherUseCase.call(cityName)
        }
    }

    fun getCities(){

            getCityLiveUseCase.invoke(Unit)
                .onEach {cities->
                    viewModelScope.launch {
                        val weathersAsyncList= cities.map{
                            async(globalCoroutineExceptionHandler){ getCurrentTemperatureByCityUseCase.invoke(it) }
                        }

                        val weathers = weathersAsyncList.map {
                            it.await()
                        }
                        postState(HomeState.Weathers(weathers))
                    }
                }.launchIn(viewModelScope)

    }

    fun getCityWeather(city: City){
        invokeRequest(getCurrentTemperatureByCityUseCase, city, onError = {
            it
        }){
            it
        }
    }

}

sealed class HomeState {
    class WeathersResult(val result: String) : HomeState()
    class Weathers(val weather:List<RemoteResponse<Weather>>) : HomeState()
}

sealed class HomeEvent {
    class ValidateResult(val result: String) : HomeEvent()
}