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
    private lateinit var socketClient: SocketClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var chatList = remember { mutableStateListOf<ChatData>() }
            socketClient = SocketClient {
                chatList.add(ChatData(text = it, isMe = false))
            }
            SocketExampleTheme {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    ChatList(chatList)
                    ChatInput {
                        socketClient.send(text = it)
                        chatList.add(ChatData(text = it, isMe = true))
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        socketClient.close()
    }

    @Composable
    fun ChatList(chatList: List<ChatData>) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.93f)
        ) {
            itemsIndexed(chatList) { _, item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = if (item.isMe) Arrangement.End else Arrangement.Start
                ) {
                    Text(text = item.text, modifier = Modifier.background(if (item.isMe) Color.Blue else Color.Gray))
                }
            }
        }
    }

    @Composable
    fun ChatInput(
        sendAction: (String) -> Unit
    ) {
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
                    sendAction(text)
                    text = ""
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
            ChatInput {

            }
        }
    }
}