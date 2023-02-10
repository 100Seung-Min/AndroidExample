package com.example.socketexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.socketexample.ui.theme.SocketExampleTheme
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @Stable
    private val counselorChat =
        RoundedCornerShape(topStart = 0.dp, topEnd = 5.dp, bottomEnd = 5.dp, bottomStart = 5.dp)
    private val clientChat =
        RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp, bottomEnd = 0.dp, bottomStart = 5.dp)


    private lateinit var socketClient: SocketClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var chatList = remember { mutableStateListOf<ChatData>() }
            val scrollState = rememberLazyListState()
            socketClient = SocketClient {
                chatList.add(ChatData(text = it, isMe = false))
                MainScope().launch {
                    scrollState.scrollToItem(chatList.size - 1)
                }
            }
            SocketExampleTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        ChatList(chatList, scrollState)
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    ChatInput {
                        socketClient.send(text = it)
                        chatList.add(ChatData(text = it, isMe = true))
                        MainScope().launch {
                            scrollState.scrollToItem(chatList.size - 1)
                        }
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
    fun ChatList(chatList: List<ChatData>, chatListState: LazyListState) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            state = chatListState
        ) {
            itemsIndexed(chatList) { _, item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 20.dp, end = 10.dp),
                    horizontalArrangement = if (item.isMe) Arrangement.End else Arrangement.Start
                ) {
                    Text(
                        text = item.text,
                        modifier = Modifier
                            .background(
                                color = if (item.isMe) Color.White else Color.LightGray,
                                shape = if (item.isMe) clientChat else counselorChat
                            )
                            .padding(horizontal = 7.dp, vertical = 5.dp)
                    )
                }
            }
        }
    }

    @Composable
    fun ChatInput(
        sendAction: (String) -> Unit
    ) {
        var isExpand by remember { mutableStateOf(false) }
        var text by remember { mutableStateOf("") }
        Row(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
        ) {
            if (isExpand) {
                IconButton(icon = Icons.Default.Close) {
                    isExpand = false
                }
                Spacer(modifier = Modifier.width(5.dp))
                IconButton(icon = Icons.Default.Home) {
                }
                Spacer(modifier = Modifier.width(5.dp))
                IconButton(icon = Icons.Default.Call) {
                }
                Spacer(modifier = Modifier.width(5.dp))
                IconButton(icon = Icons.Default.Notifications) {
                }
            } else {
                IconButton(icon = Icons.Default.Add) {
                    isExpand = true
                }
            }
            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(Color.White),
                textStyle = TextStyle(fontSize = 15.sp)
            )
            IconButton(icon = Icons.Default.Send) {
                if (!text.isNullOrBlank()) {
                    sendAction(text)
                }
                text = ""
            }
        }
    }

    @Composable
    fun IconButton(
        icon: ImageVector,
        onClick: () -> Unit
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .width(30.dp)
                .fillMaxHeight()
                .clickable {
                    onClick()
                }
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        SocketExampleTheme {
            ChatList(listOf(), rememberLazyListState())
            ChatInput {

            }
        }
    }
}