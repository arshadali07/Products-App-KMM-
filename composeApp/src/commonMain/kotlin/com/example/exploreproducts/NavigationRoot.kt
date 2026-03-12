package com.example.exploreproducts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.example.exploreproducts.presentation.details.ProductDetailsRoot
import com.example.exploreproducts.presentation.home.ProductsHomeRoot
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun NavigationRoot() {
    val backStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(NavigationRoute.ProductsHome::class, NavigationRoute.ProductsHome.serializer())
                    subclass(NavigationRoute.ProductDetails::class, NavigationRoute.ProductDetails.serializer())
                }
            }
        },
        NavigationRoute.ProductsHome
    )
    NavDisplay(
        modifier = Modifier.fillMaxSize(),
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<NavigationRoute.ProductsHome> {
                ProductsHomeRoot(
                    onItemClick = { product ->
                        backStack.add(NavigationRoute.ProductDetails(product.id))
                    }
                )
            }
            entry<NavigationRoute.ProductDetails> {
                ProductDetailsRoot(
                    productId = it.productId?.toString()
                )
            }
        }
    )
}