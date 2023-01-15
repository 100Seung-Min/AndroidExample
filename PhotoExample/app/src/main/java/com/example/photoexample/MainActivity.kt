package com.example.photoexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.photoexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onClick(view: View) {
        when (view) {
            binding.outerPhotoBtn -> {
            }
        }
    }
}