package com.example.exploreproducts.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalCustomColors = staticCompositionLocalOf {
    getLightColorScheme()
}
val LocalCustomTypography = staticCompositionLocalOf {
    ProductsTypography()
}

val LocalCustomShapes = staticCompositionLocalOf {
    ProductsShape()
}

@Composable
fun ProductsThemeAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val customColors = if (darkTheme) getDarkColorScheme() else getLightColorScheme()
    val customTypography = CustomTypography
    val customShapes = ProductsShape()

    CompositionLocalProvider(
        LocalCustomColors provides customColors,
        LocalCustomTypography provides customTypography,
        LocalCustomShapes provides customShapes,
        content = content
    )
}

object ProductsTheme {
    val colors: ProductsColor
        @Composable
        get() = LocalCustomColors.current

    val typography: ProductsTypography
        @Composable
        get() = LocalCustomTypography.current

    val shape: ProductsShape
        @Composable
        get() = LocalCustomShapes.current
}