package com.example.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.home.databinding.WeatherItemBinding
import com.example.home.model.WeatherModel

class WeatherListAdapter: ListAdapter<WeatherModel, WeatherListAdapter.WeatherViewHolder>(DiffChecker()) {

    inner class WeatherViewHolder(private val mainItem: WeatherItemBinding)
        : RecyclerView.ViewHolder(mainItem.root) {

        fun bind(itemName: WeatherModel) {
            mainItem.degree.text = itemName.degree
            mainItem.humidite.text = itemName.humidity
            mainItem.vent.text = itemName.vent
            mainItem.precipitation.text = itemName.precipitation
            mainItem.textView1.text = itemName.itemH
            mainItem.textView2.text = itemName.itemV
            mainItem.textView3.text = itemName.itemP
            mainItem.cityName.text = itemName.city
            //  mainItem.imageView. = itemName.itemImage

        }
    }

    class DiffChecker: DiffUtil.ItemCallback<WeatherModel>(){
        override fun areItemsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
            return oldItem.degree == newItem.degree
        }

        override fun areContentsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
            return oldItem.degree == newItem.degree
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListAdapter.WeatherViewHolder {
        return WeatherViewHolder(WeatherItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: WeatherListAdapter.WeatherViewHolder, position: Int) {

        holder.bind(getItem(position))
    }


}