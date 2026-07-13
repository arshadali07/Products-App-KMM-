package com.example.exploreproducts.presentation.home

import androidx.compose.foundation.text.input.TextFieldState

data class ProductsHomeUiState(
    val isApiLoading: Boolean = false,
    val categories: List<String>? = null,
)