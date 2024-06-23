package com.example.core

import android.app.usage.UsageEvents.Event
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.base.BaseUseCase
import com.example.domain.base.RemoteResponse
import com.example.domain.entities.ErrorBody
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.coroutineContext

open class BaseViewModel<State, Event>:ViewModel() {

    val state = MutableLiveData<State>()
    val event = SingleLiveEvent<Event>()

    val errorHandler = SingleLiveEvent<ErrorBody>()

    val loadingHandler = SingleLiveEvent<Boolean>()

    val globalCoroutineExceptionHandler:CoroutineExceptionHandler by lazy {
       CoroutineExceptionHandler{coroutineContext, throwable->
           errorHandler.postValue(ErrorBody("", 0))
       }
    }
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

    fun <T,R>invokeRequest(usecase:BaseUseCase<T,R>,
                           input:T,
                           onError:((error:ErrorBody)->Unit)= ::onErrorHappened,
                           onLoading:(Boolean)->Unit = ::handlerLoader,
                           onSuccess:(R)->Unit){
        viewModelScope.launch {
           try {
               onLoading(true)
               when(val result = usecase.invoke(input)){
                   is RemoteResponse.Success ->  onSuccess(result.result)
                   is RemoteResponse.NetworkError -> {
                       onError(result.errorBody)

                   }
               }

           }catch (e:Exception) {
               onError(ErrorBody("", 0))
           }finally {
               onLoading(false)
           }
        }
    }

    private fun onErrorHappened(error: ErrorBody){
        errorHandler.postValue(error)

    }

    private fun handlerLoader(isLoading:Boolean){
        loadingHandler.postValue(isLoading)
    }

}