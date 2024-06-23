package com.example.core

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.core.extencion.CustomProgressDialog
import com.example.domain.entities.ErrorBody


abstract class BaseFragment<VM : BaseViewModel<State, Event>, VB : ViewBinding, State, Event> : Fragment() {

    protected abstract val onViewBinding:(LayoutInflater, ViewGroup?, Boolean) ->VB
    protected abstract fun getViewModelClass():Class<VM>
    lateinit var binding:VB
    lateinit var viewModel:VM
    protected abstract fun onStateUpdate(state: State)
    protected abstract fun onEventUpdate(event: Event)

    val loadingDialog:CustomProgressDialog by lazy {CustomProgressDialog(requireContext()) }
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

        viewModel.errorHandler.observe(viewLifecycleOwner){
            showGlobalError(it)
        }

        viewModel.loadingHandler.observe(viewLifecycleOwner){
            if (it){
                showLoader()
            }else{
                hideLoader()
            }
        }
    }

    private fun hideLoader() {
        loadingDialog.dismiss() 
    }

    private fun showLoader() {
        loadingDialog.show()
    }

    fun showGlobalError(errorBody:ErrorBody?){
        errorBody?.let {
            when(errorBody.errorCode){
                0 ->showErrorDialog(errorBody.also {
                    it.errorMessage = getString(R.string.global_unexpected_error_message)
                })
            }
        }

    }

    private fun showErrorDialog(errorBody: ErrorBody) {
        AlertDialog.Builder(context)
            .setTitle(R.string.global_error_handler_dialog_title)
            .setMessage(errorBody.errorMessage)
            .setPositiveButton(android.R.string.yes) { dialog, which ->
            }
            .setNegativeButton(android.R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }


}