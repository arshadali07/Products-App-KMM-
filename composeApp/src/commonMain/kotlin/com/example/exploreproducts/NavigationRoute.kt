package com.example.exploreproducts

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface NavigationRoute : NavKey {

    @Serializable
    data object ProductsHome : NavigationRoute

    @Serializable
    data class ProductDetails(val productId: Int?) : NavigationRoute

    @Serializable
    data object ProductsSearch : NavigationRoute

    @Serializable
    data class ProductsByCategory(val category: String?) : NavigationRoute
}
