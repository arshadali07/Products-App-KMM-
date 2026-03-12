package com.example.exploreproducts.presentation.home

sealed interface ProductsHomeAction {
    data object OnBackClickAction : ProductsHomeAction
}