package com.example.weatherapplication.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapplication.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.weatherFragment, MainWeather()).commit()
    }
}