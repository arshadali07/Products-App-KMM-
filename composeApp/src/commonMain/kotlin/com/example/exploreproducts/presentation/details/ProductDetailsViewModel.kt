package com.example.exploreproducts.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exploreproducts.data.KtorProductsService
import com.example.exploreproducts.data.RemoteProductsRepository
import com.example.exploreproducts.domain.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val productId: String? = null,
    private val productsRepository: ProductsRepository = RemoteProductsRepository(productsService = KtorProductsService())
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductDetailsUiState())
    val uiState = _uiState
        .onStart { getProductDetail(productId = productId) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = _uiState.value
        )

    fun onAction(action: ProductDetailsAction) {
        when (action) {
            is ProductDetailsAction.OnBackClickAction -> navigateBack()
        }
    }

    private fun navigateBack() {

    }

    private fun getProductDetail(productId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            productsRepository.getProductDetail(productId = productId)
                .onSuccess { product ->
                    _uiState.update { it.copy(product = product) }
                }
                .onFailure {

                }
        }
    }
}