package com.example.home

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.BaseFragment
import com.example.home.databinding.FragmentHomeBinding
import com.example.home.model.WeatherModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, HomeState, HomeEvent>() {

    private val weatherList: ArrayList<WeatherModel> by lazy {
       arrayListOf(
            WeatherModel(
                "11.11°", "Humidite:", "Vent: ",
                "Precipitation: ", "drawable/rainandsun.jpg",
                "51%", "12km", "8.005", "Baku", "drawable/ic_recycle_bin.xml"
            ),
            WeatherModel(
                "13°", "Humidite:", "Vent: ",
                "Precipitation: ", "drawable/rainandsun.jpg",
                "51%", "12km", "8.005", "Ganca", "drawable/ic_recycle_bin.xml"
            ),
            WeatherModel(
                "14°", "Humidite:", "Vent: ",
                "Precipitation: ", "drawable/rainandsun.jpg",
                "51%", "12km", "8.005", "Nakhchivan", "drawable/ic_recycle_bin.xml"
            )

        )
    }
    override val onViewBinding: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = WeatherListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.submitList(weatherList)
       // adapter.notifyDataSetChanged()

        binding.addCity.setOnClickListener {
            showAddCity()
        }

        viewModel.getCities()

    }

    private fun showAddCity(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Add City")

        val input = EditText(requireContext())
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
        builder.setPositiveButton("OK") { dialog, which ->
            viewModel.addCityName(input.text.toString())
        }
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }

        builder.show()
    }
    override fun onStateUpdate(state: HomeState) {
        when(state){
            is HomeState.WeathersResult ->{
               // state.weather
            }
            is HomeState.Weathers ->{
                state.weather
            }

            else -> {}
        }
    }

    override fun onEventUpdate(event: HomeEvent) {
        when(event){
            is HomeEvent.ValidateResult ->{

            }

            else -> {}
        }
    }
}