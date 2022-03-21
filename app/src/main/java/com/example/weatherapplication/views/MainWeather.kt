package com.example.weatherapplication.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
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
        activity?.title = "Weather App"

        binding.lifecycleOwner = this
        binding.myviewmodel = viewmodel

        val button = binding.root.findViewById<Button>(R.id.searchB)

        button.setOnClickListener{
            viewmodel.callAPI()
            viewmodel.myResponse.value

            viewmodel.isBusy.observe(viewLifecycleOwner) {
                if(it==false) {
                    if(viewmodel.code.value?.toInt() == 200){
                        activity?.supportFragmentManager?.beginTransaction()
                            ?.replace(R.id.weatherFragment, ListWeather())
                            ?.addToBackStack(null)?.commit()
                    }else{
                        Toast.makeText(activity, "City Not Found", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        return binding.root
    }
}