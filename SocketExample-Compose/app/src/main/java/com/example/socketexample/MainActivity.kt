package com.example.socketexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.socketexample.ui.theme.SocketExampleTheme
import okhttp3.*

class MainActivity : ComponentActivity() {
    private lateinit var webSocket: WebSocket
    private val client by lazy { OkHttpClient() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var chatList = remember { mutableStateListOf<ChatData>() }
            val request = Request.Builder()
                .url("ws://192.168.0.6:8080/ws/messages")
                .build()
            val listener = object : WebSocketListener() {
                override fun onOpen(webSocket: WebSocket, response: Response) {
                    super.onOpen(webSocket, response)
                    println("안녕 소켓 연결 성공")
                }

                override fun onMessage(webSocket: WebSocket, text: String) {
                    super.onMessage(webSocket, text)
                    runOnUiThread {
                        chatList.add(ChatData(text = text, isMe = false))
                    }
                }

                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                    super.onFailure(webSocket, t, response)
                    println("안녕 소켓 연결 실패 ${t}")
                }
            }
            webSocket = client.newWebSocket(request, listener)
            SocketExampleTheme {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    ChatList(chatList)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        webSocket.close(1000, "Close")
        client.dispatcher().executorService().shutdown()
    }

    @Composable
    fun ChatList(chatList: List<ChatData>) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.93f)
        ) {
            itemsIndexed(chatList) { index, item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = if (item.isMe) Arrangement.Start else Arrangement.End
                ) {
                    Text(text = item.text)
                }
            }
        }
    }

    @Composable
    fun ChatInput() {
        var text by remember { mutableStateOf("") }
        Row(
            modifier = Modifier
                .fillMaxHeight(1f)
                .fillMaxWidth()
        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .fillMaxHeight()
                    .background(Color.White),
                textStyle = TextStyle(fontSize = 15.sp)
            )
            Button(
                onClick = {
                    webSocket.send(text)
                }, modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(1f)
            ) {
                Text(text = "전송")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        SocketExampleTheme {
            ChatList(listOf())
            ChatInput()
        }
    }
}