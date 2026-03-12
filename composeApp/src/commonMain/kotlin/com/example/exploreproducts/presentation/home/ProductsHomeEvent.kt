package com.example.exploreproducts.presentation.home

sealed interface ProductsHomeEvent {
    data class ShowToastEvent(val message: String) : ProductsHomeEvent
}