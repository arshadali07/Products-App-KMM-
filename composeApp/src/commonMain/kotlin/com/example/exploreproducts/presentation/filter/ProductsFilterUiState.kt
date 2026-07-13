package com.example.exploreproducts.presentation.filter

import androidx.compose.foundation.text.input.TextFieldState
import com.example.exploreproducts.domain.model.response.Product

data class ProductsFilterUiState(
    val isApiLoading: Boolean = false,
    val filteredProducts: List<Product>? = null
)