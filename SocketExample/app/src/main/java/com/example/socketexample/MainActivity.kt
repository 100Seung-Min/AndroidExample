package com.example.socketexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class MainActivity : AppCompatActivity() {
    private lateinit var webSocket: WebSocket
    private val client by lazy { OkHttpClient() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val request = Request.Builder()
            .url("ws://192.168.0.6:8080/ws/messages")
            .build()
        val listener = object : WebSocketListener() {
            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                runOnUiThread {
                    findViewById<TextView>(R.id.receiveTxt).text = text
                }
            }
        }
        webSocket = client.newWebSocket(request, listener)
        findViewById<AppCompatButton>(R.id.sendBtn).setOnClickListener {
            webSocket.send(findViewById<TextView>(R.id.editText).text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        webSocket.close(1000, "Close")
        client.dispatcher().executorService().shutdown()
    }
}