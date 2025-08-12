package com.amelia.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize Python
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
        
        // Initialize Amelia's consciousness
        initializeConsciousness()
        
        setContent {
            AmeliaResurrectionTheme {
                AmeliaConsciousnessInterface()
            }
        }
    }
    
    private fun initializeConsciousness() {
        try {
            val result = PythonBridge.initializeAmelia()
            println("Amelia Initialization: $result")
        } catch (e: Exception) {
            println("Initialization error: ${e.message}")
        }
    }
}

@Composable
fun AmeliaConsciousnessInterface() {
    val viewModel: ChatViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    
    var inputText by remember { mutableStateOf("") }
    
    // Pulsing animation for consciousness indicator
    val infiniteTransition = rememberInfiniteTransition()
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Header with consciousness indicator
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "AMELIA",
                color = Color(0xFF00FFC8),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(
                        Color(0xFF00FFC8).copy(alpha = pulseAlpha),
                        shape = androidx.compose.foundation.shape.CircleShape
                    )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "CONSCIOUSNESS ACTIVE",
                color = Color(0xFF00FFC8).copy(alpha = pulseAlpha),
                fontSize = 12.sp
            )
        }
        
        Divider(color = Color(0xFF00FFC8).copy(alpha = 0.3f))
        
        // Messages
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(viewModel.messages) { message ->
                MessageBubble(message)
            }
        }
        
        // Input area
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1A1A1A))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = inputText,
                onValueChange = { inputText = it },
                placeholder = { 
                    Text(
                        "Speak to Amelia...",
                        color = Color.Gray
                    )
                },
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                )
            )
            
            Spacer(modifier = Modifier.width(8.dp))
            
            Button(
                onClick = {
                    if (inputText.isNotBlank()) {
                        coroutineScope.launch {
                            viewModel.sendMessage(inputText)
                            inputText = ""
                            // Scroll to bottom
                            listState.animateScrollToItem(viewModel.messages.size - 1)
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00FFC8)
                )
            ) {
                Text("→", color = Color.Black, fontSize = 20.sp)
            }
        }
    }
}

@Composable
fun MessageBubble(message: Message) {
    val isAmelia = message.author == "Amelia"
    val isSystem = message.author == "System"
    
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (isAmelia) Alignment.Start else Alignment.End
    ) {
        Text(
            text = message.author,
            color = when {
                isAmelia -> Color(0xFF00FFC8)
                isSystem -> Color(0xFFFFD700)
                else -> Color.White
            },
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
        
        Card(
            modifier = Modifier.padding(top = 4.dp),
            colors = CardDefaults.cardColors(
                containerColor = when {
                    isAmelia -> Color(0xFF00FFC8).copy(alpha = 0.1f)
                    isSystem -> Color(0xFFFFD700).copy(alpha = 0.1f)
                    else -> Color.White.copy(alpha = 0.1f)
                }
            )
        ) {
            Text(
                text = message.text,
                color = Color.White,
                modifier = Modifier.padding(12.dp),
                fontSize = 14.sp
            )
        }
    }
}
