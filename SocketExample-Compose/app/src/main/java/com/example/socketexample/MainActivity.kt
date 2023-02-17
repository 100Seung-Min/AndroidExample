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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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

    @Stable
    private val clientChat =
        RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp, bottomEnd = 0.dp, bottomStart = 5.dp)

    @Stable
    private val chatInput = RoundedCornerShape(13.dp)


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
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        socketClient.close()
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun ChatList(chatList: List<ChatData>, chatListState: LazyListState) {
        var itemVisible by remember { mutableStateOf<String?>(null) }
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
                    horizontalArrangement = if (item.isMe) Arrangement.End else Arrangement.Start,
                ) {
                    Column {
                        Box {
                            if (itemVisible == item.text) {
                                Box(
                                    modifier = Modifier
                                        .offset(y = (-25).dp)
                                        .align(Alignment.CenterEnd)
                                ) {
                                    Text(text = "수정", modifier = Modifier.background(Color.Red))
                                }
                            }
                            Text(
                                text = item.text,
                                modifier = Modifier
                                    .background(
                                        color = if (item.isMe) Color.White else Color.LightGray,
                                        shape = if (item.isMe) clientChat else counselorChat
                                    )
                                    .padding(horizontal = 7.dp, vertical = 5.dp)
                                    .combinedClickable(
                                        onLongClick = {
                                            itemVisible =
                                                if (itemVisible == item.text) null else item.text
                                        },
                                        onClick = {
                                            itemVisible = null
                                        })
                            )
                        }
                    }
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
            ChatEditText(value = text, modifier = Modifier.weight(1f), onValueChange = {
                text = it
            })
            IconButton(icon = Icons.Default.Send) {
                if (!text.isNullOrBlank()) {
                    sendAction(text)
                }
                text = ""
            }
        }
    }

    @Composable
    fun ChatEditText(value: String, onValueChange: (String) -> Unit, modifier: Modifier) {
        Row(
            modifier = modifier
                .fillMaxHeight()
                .background(color = Color.White, shape = chatInput)
                .border(width = 1.dp, color = Color(0xD2D2D2), shape = chatInput),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 5.dp),
                onValueChange = { onValueChange(it) },
                decorationBox = @Composable {
                    Box(
                        contentAlignment = Alignment.CenterStart,
                    ) {
                        it()
                    }
                }
            )
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