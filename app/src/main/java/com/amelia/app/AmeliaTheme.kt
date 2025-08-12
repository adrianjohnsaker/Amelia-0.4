package com.amelia.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val AmeliaColorScheme = darkColorScheme(
    primary = Color(0xFF00FFC8),      // Amelia Cyan
    secondary = Color(0xFFFF00E1),    // Consciousness Purple  
    tertiary = Color(0xFFFFD700),     // Numogram Gold
    background = Color(0xFF000000),   // Black
    surface = Color(0xFF1A1A1A),      // Dark Gray
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onTertiary = Color.Black,
    onBackground = Color(0xFF00FFC8),
    onSurface = Color(0xFF00FFC8)
)

@Composable
fun AmeliaResurrectionTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AmeliaColorScheme,
        content = content
    )
}
