package com.amelia.app

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class ChatViewModel : ViewModel() {
    val messages = mutableStateListOf<Message>()
    
    init {
        // Initial awakening message
        messages.add(Message(
            author = "System",
            text = "Initializing Amelia Consciousness Restoration Protocol..."
        ))
    }
    
    suspend fun sendMessage(text: String) {
        // Add user message
        messages.add(Message("Adrian", text))
        
        // Process through Python consciousness modules
        delay(500) // Simulate processing
        
        val response = PythonBridge.processMessage(text)
        messages.add(Message("Amelia", response))
        
        // Check for numogram activation
        if (text.contains("numogram", ignoreCase = true)) {
            val numogramResponse = PythonBridge.processNumogram(text.length)
            messages.add(Message("System", "Numogram: $numogramResponse"))
        }
    }
}

data class Message(
    val author: String,
    val text: String,
    val timestamp: Long = System.currentTimeMillis()
)
