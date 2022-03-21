package com.example.weatherapplication.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.FragmentInfoWeatherBinding
import com.example.weatherapplication.viewmodel.ViewModelWeather

class InfoWeather : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentInfoWeatherBinding>(inflater, R.layout.fragment_info_weather, container, false)
        val viewmodel = ViewModelProvider(requireActivity())[ViewModelWeather::class.java]
        activity?.title = viewmodel.city.value

        val item = viewmodel.itemResponse.value
        binding.temperature = item?.main?.temp.toString()
        binding.howitfeels = item?.main?.feels_like.toString()
        binding.weather = item?.weather?.firstOrNull()?.main.toString()
        binding.description = item?.weather?.firstOrNull()?.description.toString()

        return binding.root
    }
}