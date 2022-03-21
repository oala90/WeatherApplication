package com.example.weatherapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.R
import com.example.weatherapplication.model.WeatherList

class ListWeatherAdapter (private var items: List<WeatherList>, private var listener1: OnItemClickListener) : RecyclerView.Adapter<ListWeatherAdapter.ViewHolder>() {

    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val weather: TextView = view.findViewById(R.id.vWeather)
        val temperature: TextView = view.findViewById(R.id.vTemperature)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            if ( layoutPosition != RecyclerView.NO_POSITION) {
                listener1.onItemClick(items[adapterPosition])
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: WeatherList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.weather.text = item.weather?.firstOrNull()?.main
        holder.temperature.text = item.main?.temp.toString()
    }

    override fun getItemCount() = items.size
}