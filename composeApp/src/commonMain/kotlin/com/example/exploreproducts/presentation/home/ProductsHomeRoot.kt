package com.example.exploreproducts.presentation.home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.example.exploreproducts.presentation.theme.ProductsTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun ProductsHomeRoot(
    viewModel: ProductsHomeViewModel = viewModel { ProductsHomeViewModel() },
    onItemClick: (Product) -> Unit,
    onSearchClick: () -> Unit,
    onFilterClick: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lazyPagingProducts = viewModel.paginatedProducts.collectAsLazyPagingItems()
    ProductsHomeScreen(
        modifier = Modifier,
        uiState = uiState,
        lazyPagingProducts = lazyPagingProducts,
        onAction = viewModel::onAction,
        onItemClick = onItemClick,
        onSearchClick = onSearchClick,
        onFilterClick = onFilterClick
    )
}

@Composable
private fun ProductsHomeScreen(
    modifier: Modifier = Modifier,
    uiState: ProductsHomeUiState,
    lazyPagingProducts: LazyPagingItems<Product>,
    onItemClick: (Product) -> Unit,
    onAction: (ProductsHomeAction) -> Unit,
    onSearchClick: () -> Unit,
    onFilterClick: (String) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(24.dp)
    ) {
        when (lazyPagingProducts.loadState.refresh) {
            is LoadState.Error -> {
                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillParentMaxSize()
                            .padding(24.dp)
                    ) {
                        Text(
                            text = "Some error occurred while fetching details",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            is LoadState.Loading -> {
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
            }
            is LoadState.NotLoading -> {
                productsHomeScreenMainContent(
                    lazyPagingProducts = lazyPagingProducts,
                    categories = uiState.categories,
                    onItemClick = onItemClick,
                    onSearchClick = onSearchClick,
                    onFilterClick = onFilterClick
                )
            }
        }
    }
}

private fun LazyListScope.productsHomeScreenMainContent(
    lazyPagingProducts: LazyPagingItems<Product>,
    categories: List<String>?,
    onItemClick: (Product) -> Unit,
    onSearchClick: () -> Unit,
    onFilterClick: (String) -> Unit,
) {
    item {
        var isExpanded by remember { mutableStateOf(false) }
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillParentMaxWidth()
                .animateContentSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = onSearchClick,
                    content = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search Icon"
                        )
                    }
                )
                IconButton(
                    onClick = { isExpanded = !isExpanded },
                    content = {
                        val icon = if (isExpanded) Icons.Filled.Close else Icons.Filled.FilterList
                        Icon(
                            imageVector = icon,
                            contentDescription = "Filter Icon"
                        )
                    }
                )
            }
            if (isExpanded) {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    var selectedText by remember { mutableStateOf("") }
                    categories?.forEach { category ->
                        val formattedCategory = category.replace(" ", "-").replaceFirstChar { it.uppercase() }
                        val color = if (selectedText == category) Color.Green else Color.Black
                        Text(
                            text = formattedCategory,
                            style = MaterialTheme.typography.bodySmall,
                            color = color,
                            modifier = Modifier
                                .clip(shape = CircleShape)
                                .border(width = 1.dp, color = color, shape = CircleShape)
                                .clickable(onClick = { onFilterClick(category) })
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
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
    item {
        when (lazyPagingProducts.loadState.append) {
            is LoadState.Error -> {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .padding(24.dp)
                ) {
                    Text(
                        text = "Some error occurred while fetching details",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                    Button(
                        onClick = { lazyPagingProducts.retry() },
                        content = { Text(text = "Retry") }
                    )
                }
            }
            is LoadState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillParentMaxWidth()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(24.dp),
                        strokeWidth = 4.dp
                    )
                }
            }
            is LoadState.NotLoading -> {}
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
        onItemClick = {},
        onSearchClick = {},
        onFilterClick = {}
    )
}

private val testUiState = ProductsHomeUiState()