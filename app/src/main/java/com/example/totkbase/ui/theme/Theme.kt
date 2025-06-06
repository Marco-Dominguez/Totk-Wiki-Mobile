package com.example.totkbase.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = ColorPalette5,
    onPrimary = ColorPalette1,
    primaryContainer = ColorPalette7,
    onPrimaryContainer = ColorPalette2,
    secondary = ColorPalette4,
    onSecondary = ColorPalette11,
    secondaryContainer = ColorPalette6,
    onSecondaryContainer = ColorPalette2,
    tertiary = ColorPalette8,
    onTertiary = ColorPalette1,
    tertiaryContainer = ColorPalette9,
    onTertiaryContainer = ColorPalette2,
    background = DarkBackground,
    onBackground = ColorPalette2,
    surface = DarkBackground.copy(alpha = 0.9f),
    onSurface = ColorPalette2,
    surfaceVariant = ColorPalette10,
    onSurfaceVariant = ColorPalette3,
    error = Color(0xFFE57373),
    outline = ColorPalette6
)

private val LightColorScheme = lightColorScheme(
    primary = ColorPalette7,
    onPrimary = ColorPalette1,
    primaryContainer = ColorPalette5,
    onPrimaryContainer = ColorPalette9,
    secondary = ColorPalette6,
    onSecondary = ColorPalette1,
    secondaryContainer = ColorPalette4,
    onSecondaryContainer = ColorPalette9,
    tertiary = ColorPalette8,
    onTertiary = ColorPalette1,
    tertiaryContainer = ColorPalette3,
    onTertiaryContainer = ColorPalette9,
    background = LightBackground,
    onBackground = ColorPalette11,
    surface = LightBackground.copy(alpha = 0.9f),
    onSurface = ColorPalette9,
    surfaceVariant = ColorPalette2,
    onSurfaceVariant = ColorPalette8,
    error = Color(0xFFB00020),
    outline = ColorPalette6
)

@Composable
fun TotKBaseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

