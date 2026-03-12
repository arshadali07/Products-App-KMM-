package com.example.exploreproducts.presentation.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.SubcomposeAsyncImage
import com.example.exploreproducts.presentation.theme.ProductsTheme

@Composable
fun ProductDetailsRoot(
    productId: String?,
    viewModel: ProductDetailsViewModel = viewModel { ProductDetailsViewModel(productId = productId) }
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    if (uiState.product != null) {
        ProductDetailsScreen(
            modifier = Modifier,
            uiState = uiState,
            onAction = viewModel::onAction
        )
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier
            )
        }
    }
}

@Composable
private fun ProductDetailsScreen(
    modifier: Modifier = Modifier,
    uiState: ProductDetailsUiState,
    onAction: (ProductDetailsAction) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        SubcomposeAsyncImage(
            model = uiState.product?.images?.firstOrNull(),
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
                .aspectRatio(1f)
                .clip(shape = RoundedCornerShape(12.dp))
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = uiState.product?.title ?: "",
                style = ProductsTheme.typography.headlineOne18Medium,
                color = ProductsTheme.colors.textPrimary
            )
            Text(
                text = uiState.product?.description ?: "",
                style = ProductsTheme.typography.bodyOne12Regular,
                color = ProductsTheme.colors.textSecondary
            )
            Text(
                text = "Brand: ${uiState.product?.brand}",
                style = ProductsTheme.typography.bodyOne12Regular,
                color = ProductsTheme.colors.textSecondary
            )
            Text(
                text = "Price: ₹ ${uiState.product?.price ?: ""}",
                style = ProductsTheme.typography.bodyOne12Regular,
                color = ProductsTheme.colors.textSecondary
            )
            Text(
                text = "Rating: ${uiState.product?.rating}",
                style = ProductsTheme.typography.headlineOne18Medium,
                color = ProductsTheme.colors.textPrimary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductDetailsScreenPreview() {
    ProductDetailsScreen(
        uiState = testUiState,
        onAction = {}
    )
}

private val testUiState = ProductDetailsUiState(
    product = ProductDetailsPreviewData.product1
)