package com.example.weatherapplication.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.FragmentMainWeatherBinding
import com.example.weatherapplication.viewmodel.ViewModelWeather


class MainWeather : Fragment() {

    private lateinit var binding: FragmentMainWeatherBinding
    private lateinit var viewmodel: ViewModelWeather

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_weather, container, false)
        viewmodel = ViewModelProvider(requireActivity())[ViewModelWeather::class.java]

        binding.lifecycleOwner = this
        binding.myviewmodel = viewmodel

        val button = binding.root.findViewById<Button>(R.id.searchB)


        button.setOnClickListener{
            viewmodel.callAPI()
            viewmodel.isBusy.observe(viewLifecycleOwner) {
                if(it==false) {
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.weatherFragment, ListWeather())
                        ?.addToBackStack(null)?.commit()
                }
            }
        }
        return binding.root
    }
}