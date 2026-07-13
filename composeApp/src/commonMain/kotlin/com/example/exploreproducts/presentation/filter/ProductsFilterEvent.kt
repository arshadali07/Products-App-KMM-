package com.example.exploreproducts.presentation.filter

sealed interface ProductsFilterEvent {
    data class ShowToastEvent(val message: String) : ProductsFilterEvent
}