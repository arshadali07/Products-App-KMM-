package com.example.exploreproducts.presentation.theme

import androidx.compose.ui.graphics.Color

class ProductsColor(
    val primary: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val backgroundPrimary: Color,
    val backgroundSecondary: Color
)

//region Light Colors
val lightPrimary = Color(0xFFC20029)
val lightTextPrimary = Color(0xFF000000)
val lightTextSecondary = Color(0xFF555C4D)
val lightBackgroundPrimary = Color(0xFFFFFFFF)
val lightBackgroundSecondary = Color(0xFFF1F1F1)

fun getLightColorScheme() = ProductsColor(
    primary = lightPrimary,
    textPrimary = lightTextPrimary,
    textSecondary = lightTextSecondary,
    backgroundPrimary = lightBackgroundPrimary,
    backgroundSecondary = lightBackgroundSecondary
)
//endregion Light Colors

//region Dark Colors
val darkPrimary = Color(0xFFC20029)
val darkTextPrimary = Color(0xFFFFFFFF)
val darkTextSecondary = Color(0xFFF1F1F1)
val darkBackgroundPrimary = Color(0xFF000000)
val darkBackgroundSecondary = Color(0xFF4F4F4F)

fun getDarkColorScheme() = ProductsColor(
    primary = darkPrimary,
    textPrimary = darkTextPrimary,
    textSecondary = darkTextSecondary,
    backgroundPrimary = darkBackgroundPrimary,
    backgroundSecondary = darkBackgroundSecondary
)