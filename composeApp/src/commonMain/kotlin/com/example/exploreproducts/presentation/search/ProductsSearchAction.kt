package com.example.exploreproducts.presentation.search

sealed interface ProductsSearchAction {
    data object OnBackClickAction : ProductsSearchAction
}