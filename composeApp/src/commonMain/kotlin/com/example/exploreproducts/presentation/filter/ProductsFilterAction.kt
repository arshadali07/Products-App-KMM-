package com.example.exploreproducts.presentation.filter

sealed interface ProductsFilterAction {
    data object OnBackClickAction : ProductsFilterAction
}