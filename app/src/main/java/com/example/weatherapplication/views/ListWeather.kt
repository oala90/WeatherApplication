package com.example.weatherapplication.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
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
        val decorationLine = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        activity?.title = viewmodel.city.value

        viewInit()
        myView.findViewById<RecyclerView>(R.id.listWeatherRV).addItemDecoration(decorationLine)
        return myView
    }

    private fun viewInit() {
        val listRVWeather: RecyclerView = myView.findViewById(R.id.listWeatherRV)
        listRVWeather.layoutManager = LinearLayoutManager(activity)
        viewmodel.myResponse.value?.list?.let {
            arr.clear()
            arr.addAll(0, it) }

        val myAdapter =   ListWeatherAdapter(arr, this)
        listRVWeather.adapter = myAdapter
    }

    override fun onItemClick(item: WeatherList) {
       Toast.makeText(activity, "Loading values", Toast.LENGTH_SHORT).show()
        viewmodel.itemResponse.value = item
        val fragment = InfoWeather()

        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.weatherFragment, fragment)
            ?.addToBackStack(null)?.commit()
    }
}