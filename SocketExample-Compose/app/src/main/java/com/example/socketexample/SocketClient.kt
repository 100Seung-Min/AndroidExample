package com.example.socketexample

import okhttp3.*

class SocketClient(receiveAction: (String) -> Unit) {
    private var webSocket: WebSocket
    private val client by lazy { OkHttpClient() }

    init {
        val request = Request.Builder()
            .url("ws://port-0-mitalk-backend-108dypx2aldtri5ke.sel3.cloudtype.app/ws/messages")
            .build()
        val listener = object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                receiveAction(text)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
            }
        }
        webSocket = client.newWebSocket(request, listener)
    }

    fun close() {
        webSocket.close(1000, "Close")
        client.dispatcher().executorService().shutdown()
    }

    fun send(text: String) {
        webSocket.send(text)
    }
}