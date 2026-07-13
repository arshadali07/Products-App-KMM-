package com.example.exploreproducts.presentation.filter

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exploreproducts.data.KtorProductsService
import com.example.exploreproducts.data.RemoteProductsRepository
import com.example.exploreproducts.domain.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class ProductsFilterViewModel(
    private val category: String?,
    private val productsRepository: ProductsRepository = RemoteProductsRepository(productsService = KtorProductsService())
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductsFilterUiState())
    val uiState = _uiState
        .onStart { getProductsByCategory() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _uiState.value
        )

    fun onAction(action: ProductsFilterAction) {
        when (action) {
            is ProductsFilterAction.OnBackClickAction -> navigateBack()
        }
    }

    private fun navigateBack() {

    }

    private fun getProductsByCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            productsRepository.getProductsByCategory(category)
                .onSuccess { response ->
                    _uiState.update { it.copy(filteredProducts = response.products) }
                }
                .onFailure {

                }
        }
    }
}