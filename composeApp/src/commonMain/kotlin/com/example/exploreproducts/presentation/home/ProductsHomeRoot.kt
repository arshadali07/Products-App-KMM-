package com.example.exploreproducts.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.SubcomposeAsyncImage
import com.example.exploreproducts.domain.model.response.Product
import com.example.exploreproducts.presentation.theme.ProductsTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun ProductsHomeRoot(
    viewModel: ProductsHomeViewModel = viewModel { ProductsHomeViewModel() },
    onItemClick: (Product) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lazyPagingProducts = viewModel.paginatedProducts.collectAsLazyPagingItems()
    ProductsHomeScreen(
        modifier = Modifier,
        uiState = uiState,
        lazyPagingProducts = lazyPagingProducts,
        onAction = viewModel::onAction,
        onItemClick = onItemClick
    )
}

@Composable
private fun ProductsHomeScreen(
    modifier: Modifier = Modifier,
    uiState: ProductsHomeUiState,
    lazyPagingProducts: LazyPagingItems<Product>,
    onItemClick: (Product) -> Unit,
    onAction: (ProductsHomeAction) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(24.dp)
    ) {
        items(count = lazyPagingProducts.itemCount) { index ->
            lazyPagingProducts[index]?.let { product ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                        .background(
                            color = ProductsTheme.colors.backgroundPrimary,
                            shape = ProductsTheme.shape.twentyFourPixelRounded
                        )
                        .clip(shape = ProductsTheme.shape.twentyFourPixelRounded)
                        .clickable(onClick = { onItemClick(product) })
                        .padding(16.dp)
                ) {
                    SubcomposeAsyncImage(
                        model = product.images?.firstOrNull(),
                        contentDescription = "Poster Image",
                        contentScale = ContentScale.FillBounds,
                        loading = {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .matchParentSize()
                                    .padding(24.dp),
                                strokeWidth = 4.dp
                            )
                        },
                        error = { Text(text = "Error loading image") },
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .aspectRatio(1f)
                            .clip(shape = RoundedCornerShape(12.dp))
                    )
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = product.title ?: "",
                            style = ProductsTheme.typography.headlineOne18Medium,
                            color = ProductsTheme.colors.textPrimary
                        )
                        Text(
                            text = product.description ?: "",
                            style = ProductsTheme.typography.bodyOne12Regular,
                            color = ProductsTheme.colors.textSecondary,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductsHomeScreenPreview() {
    ProductsHomeScreen(
        uiState = testUiState,
        lazyPagingProducts = flowOf(
            PagingData.from(ProductsPreviewData.productsMain.products.orEmpty())
        ).collectAsLazyPagingItems(),
        onAction = {},
        onItemClick = {}
    )
}

private val testUiState = ProductsHomeUiState()