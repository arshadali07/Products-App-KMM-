package com.example.exploreproducts.presentation.search

sealed interface ProductsSearchEvent {
    data class ShowToastEvent(val message: String) : ProductsSearchEvent
}