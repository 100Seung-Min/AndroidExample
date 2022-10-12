package com.example.mapexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.mapexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        if(!checkPermissions(this)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, 1)
        }
        supportFragmentManager.beginTransaction().replace(R.id.mainContainer, MapFragment()).commit()
    }
}