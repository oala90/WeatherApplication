package com.example.weatherapplication.views

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.R
import com.example.weatherapplication.adapters.ListWeatherAdapter

import com.example.weatherapplication.model.WeatherList
import com.example.weatherapplication.viewmodel.ViewModelWeather


class ListWeather : Fragment(), ListWeatherAdapter.OnItemClickListener {

    var arr = mutableListOf<WeatherList>()
    private lateinit var viewmodel: ViewModelWeather
    private lateinit var myView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewmodel = ViewModelProvider(requireActivity()).get(ViewModelWeather::class.java)
        myView = inflater.inflate(R.layout.fragment_list_weather, container, false)

        viewInit()
        return myView
    }

    private fun viewInit() {
        val listRVWeather: RecyclerView = myView.findViewById(R.id.listWeatherRV)
        listRVWeather.layoutManager = LinearLayoutManager(activity)
        viewmodel.myResponse.value?.list?.let { arr.addAll(0, it) }
        val myadapter =   ListWeatherAdapter(arr, this)
        listRVWeather.adapter = myadapter
    }

    override fun onItemClick(item: WeatherList) {
        Toast.makeText(activity, "Loading ${item.sys?.country.toString()}", Toast.LENGTH_SHORT).show()
        viewmodel.itemResponse.value = item
        val fragment = InfoWeather()
//        val bundle= Bundle()
//        bundle.putParcelable("item", item)
//        fragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.weatherFragment, fragment)
            ?.addToBackStack(null)?.commit()
    }
}