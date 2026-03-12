package com.example.exploreproducts.presentation.details

sealed interface ProductDetailsAction {
    data object OnBackClickAction : ProductDetailsAction
}