package com.example.totkbase.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.totkbase.R

// Definimos la familia de fuentes Hylia Serif
val HyliaSerifFamily = FontFamily(
    Font(R.font.hyliaserif_regular, FontWeight.Normal)
)

// Definimos la familia de fuentes Funnel Sans
val FunnelSansFamily = FontFamily(
    Font(R.font.funnelsans_variablefont_wght, FontWeight.Normal)
)

// Fuente del sistema como respaldo
val DefaultFontFamily = FontFamily.Default

// Set of Material typography styles to start with
val Typography = Typography(
    // Titulos grandes (como título principal)
    displayLarge = TextStyle(
        fontFamily = HyliaSerifFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),
    // Títulos de sección
    displayMedium = TextStyle(
        fontFamily = HyliaSerifFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    // Subtítulos o cabeceras más pequeñas
    displaySmall = TextStyle(
        fontFamily = HyliaSerifFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    // Cabecera para tarjetas o secciones destacadas
    headlineLarge = TextStyle(
        fontFamily = HyliaSerifFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    // Cabeceras de tamaño medio
    headlineMedium = TextStyle(
        fontFamily = HyliaSerifFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.sp
    ),
    // Cabeceras pequeñas
    headlineSmall = TextStyle(
        fontFamily = HyliaSerifFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    // Títulos de elementos como botones grandes o modales
    titleLarge = TextStyle(
        fontFamily = HyliaSerifFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    // Texto principal para contenido importante
    bodyLarge = TextStyle(
        fontFamily = FunnelSansFamily, // Usando Funnel Sans para el cuerpo del texto
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    // Texto común para descripciones
    bodyMedium = TextStyle(
        fontFamily = FunnelSansFamily, // Usando Funnel Sans para el cuerpo del texto
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    // Texto pequeño para información secundaria
    bodySmall = TextStyle(
        fontFamily = FunnelSansFamily, // Usando Funnel Sans para el cuerpo del texto
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    // Etiquetas para elementos pequeños como chips o insignias
    labelSmall = TextStyle(
        fontFamily = FunnelSansFamily, // Usando Funnel Sans para etiquetas pequeñas
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)

