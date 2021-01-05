package com.hemanth.weatherApp.presentation.home.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hemanth.weatherApp.data.model.WeatherResponse
import com.hemanth.weatherApp.databinding.WeatherItemBinding

class WeatherAdapter(private val list: ArrayList<WeatherResponse.Data>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    class WeatherViewHolder(val binding: WeatherItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder =
        WeatherViewHolder(
            WeatherItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.binding.data = list[position]
    }
}