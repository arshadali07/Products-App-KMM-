package com.example.exploreproducts.presentation.details

import com.example.exploreproducts.domain.model.response.Product

data class ProductDetailsUiState(
    val isApiLoading: Boolean = false,
    val product: Product? = null,
)