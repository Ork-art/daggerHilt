package com.example.core

import android.app.usage.UsageEvents
import android.app.usage.UsageEvents.Event
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VM : BaseViewModel<State, Event>, VB : ViewBinding, State, Event> : Fragment() {

    protected abstract val onViewBinding:(LayoutInflater, ViewGroup?, Boolean) ->VB
    protected abstract fun getViewModelClass():Class<VM>
    lateinit var binding:VB
    lateinit var viewModel:VM
    protected abstract fun onStateUpdate(state: State)
    protected abstract fun onEventUpdate(event: Event)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iniViewModel()
    }

    private fun iniViewModel(){
        viewModel = ViewModelProvider(requireActivity())[getViewModelClass()]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = onViewBinding(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        starObserve()
    }

    private fun starObserve(){
        viewModel.state.observe(viewLifecycleOwner){
            onStateUpdate(it)
        }
        viewModel.event.observe(viewLifecycleOwner){
            onEventUpdate(it)
        }
    }



}