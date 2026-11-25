package com.nattv.app.ui.theme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val NATTVColors = darkColorScheme(
    primary = Gold, onPrimary = Obsidian, background = Obsidian,
    onBackground = SoftWhite, surface = Panel, onSurface = SoftWhite,
    surfaceVariant = Panel, onSurfaceVariant = Muted
)
@Composable fun NATTVTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = NATTVColors, content = content)
}