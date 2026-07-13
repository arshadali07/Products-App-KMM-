package com.example.exploreproducts.presentation.search

import androidx.compose.foundation.text.input.TextFieldState
import com.example.exploreproducts.domain.model.response.Product

data class ProductsSearchUiState(
    val isApiLoading: Boolean = false,
    val searchFieldState: TextFieldState = TextFieldState(),
    val searchedProducts: List<Product>? = null
)