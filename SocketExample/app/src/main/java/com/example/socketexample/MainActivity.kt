package com.example.socketexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.rsocket.kotlin.ktor.client.RSocketSupport
import io.rsocket.kotlin.ktor.client.rSocket
import io.rsocket.kotlin.payload.buildPayload
import io.rsocket.kotlin.payload.data
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Thread {
            kotlin.run {
                MainScope().launch {
                    val client = HttpClient {
                        install(WebSockets)
                        install(RSocketSupport)
                        hostIsIp("192.168.0.6")
                    }
                    val rSocket = client.rSocket(path = "rs", port = 6565)
                    val stream = rSocket.requestStream(buildPayload { data("dsds") })
                    stream.collect {
                        println("안녕 $it")
                    }
                }
            }
        }
    }
}