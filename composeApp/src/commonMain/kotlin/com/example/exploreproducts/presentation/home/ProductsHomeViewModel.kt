package com.example.exploreproducts.presentation.home

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.exploreproducts.data.KtorProductsService
import com.example.exploreproducts.data.RemoteProductsRepository
import com.example.exploreproducts.domain.ProductsRepository
import com.example.exploreproducts.domain.model.response.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProductsHomeViewModel(
    private val productsRepository: ProductsRepository = RemoteProductsRepository(productsService = KtorProductsService())
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductsHomeUiState())
    val uiState = _uiState.asStateFlow()

    val paginatedProducts: Flow<PagingData<Product>> = productsRepository
        .getPaginatedProducts()
        .cachedIn(viewModelScope)

    init {
        snapshotFlow { uiState.value.searchFieldState.text }
            .onEach { text ->

            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: ProductsHomeAction) {
        when (action) {
            is ProductsHomeAction.OnBackClickAction -> navigateBack()
        }
    }

    private fun navigateBack() {

    }
}