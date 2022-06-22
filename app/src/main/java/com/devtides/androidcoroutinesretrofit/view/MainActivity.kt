package com.devtides.androidcoroutinesretrofit.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devtides.androidcoroutinesretrofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}
