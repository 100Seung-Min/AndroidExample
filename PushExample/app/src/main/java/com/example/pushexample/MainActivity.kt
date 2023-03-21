package com.example.pushexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { token ->
                if (token.isSuccessful) {
                    println("안녕 ${token.result}")
                }
            }
    }
}