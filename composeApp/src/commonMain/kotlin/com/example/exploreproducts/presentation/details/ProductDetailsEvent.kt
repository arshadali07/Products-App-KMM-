package com.example.exploreproducts.presentation.details

sealed interface ProductDetailsEvent {
    data class ShowToastEvent(val message: String) : ProductDetailsEvent
}