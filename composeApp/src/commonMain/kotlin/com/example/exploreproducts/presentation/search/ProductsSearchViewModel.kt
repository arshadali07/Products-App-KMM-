package com.example.exploreproducts.presentation.search

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.exploreproducts.data.KtorProductsService
import com.example.exploreproducts.data.RemoteProductsRepository
import com.example.exploreproducts.domain.ProductsRepository
import com.example.exploreproducts.domain.model.response.Product
import com.example.exploreproducts.presentation.theme.darkTextSecondary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class ProductsSearchViewModel(
    private val productsRepository: ProductsRepository = RemoteProductsRepository(productsService = KtorProductsService())
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductsSearchUiState())
    val uiState = _uiState.asStateFlow()

    init {
        snapshotFlow { uiState.value.searchFieldState.text }
            .debounce(timeoutMillis = 1000)
            .distinctUntilChanged()
            .onEach { text ->
                getSearchedProducts(text.toString())
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: ProductsSearchAction) {
        when (action) {
            is ProductsSearchAction.OnBackClickAction -> navigateBack()
        }
    }

    private fun navigateBack() {

    }

    private fun getSearchedProducts(query: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            productsRepository.getSearchedProducts(query)
                .onSuccess { response ->
                    _uiState.update { it.copy(searchedProducts = response.products) }
                }
                .onFailure {

                }
        }
    }
}