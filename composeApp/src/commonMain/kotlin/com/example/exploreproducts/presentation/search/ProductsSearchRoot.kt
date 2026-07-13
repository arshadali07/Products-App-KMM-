package com.example.exploreproducts.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.SubcomposeAsyncImage
import com.example.exploreproducts.domain.model.response.Product
import com.example.exploreproducts.presentation.home.ProductsPreviewData
import com.example.exploreproducts.presentation.theme.ProductsTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun ProductsSearchRoot(
    viewModel: ProductsSearchViewModel = viewModel { ProductsSearchViewModel() },
    onItemClick: (Product) -> Unit,
    onBackClick: () -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ProductsSearchScreen(
        modifier = Modifier,
        uiState = uiState,
        onAction = viewModel::onAction,
        onItemClick = onItemClick,
        onBackClick = onBackClick
    )
}

@Composable
private fun ProductsSearchScreen(
    modifier: Modifier = Modifier,
    uiState: ProductsSearchUiState,
    onItemClick: (Product) -> Unit,
    onAction: (ProductsSearchAction) -> Unit,
    onBackClick: () -> Unit = {},
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(24.dp)
    ) {
        item {
            IconButton(
                onClick = onBackClick,
                content = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back Icon"
                    )
                }
            )
            TextField(
                state = uiState.searchFieldState,
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Search...") }
            )
        }
        val products = uiState.searchedProducts.orEmpty()
        if (products.isEmpty()) {
            item {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillParentMaxSize()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(24.dp),
                        strokeWidth = 4.dp
                    )
                }
            }
        } else {
            items(items = products) { product ->
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
                        error = {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .matchParentSize()
                                    .padding(6.dp)
                            ) {
                                Text(
                                    text = "Error loading image",
                                    style = MaterialTheme.typography.bodySmall,
                                    textAlign = TextAlign.Center
                                )
                            }
                        },
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
private fun ProductsSearchScreenPreview() {
    ProductsSearchScreen(
        uiState = testUiState,
        onAction = {},
        onItemClick = {},
        onBackClick = {}
    )
}

private val testUiState = ProductsSearchUiState()