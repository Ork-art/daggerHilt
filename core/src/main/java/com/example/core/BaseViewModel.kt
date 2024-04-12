package com.example.core

import android.app.usage.UsageEvents.Event
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<State, Event>:ViewModel() {

    val state = MutableLiveData<State>()
    val event = SingleLiveEvent<Event>()

    fun postState(value: State){
        value?.let {
            state.postValue(it)
        }
    }

    fun postEvent(value: Event){
        value?.let {
            event.postValue(it)
        }
    }

}