package com.example.exploreproducts.presentation.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.SubcomposeAsyncImage
import com.example.exploreproducts.presentation.theme.ProductsTheme

@Composable
fun ProductDetailsRoot(
    productId: String?,
    viewModel: ProductDetailsViewModel = viewModel { ProductDetailsViewModel(productId = productId) },
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    if (uiState.product != null) {
        ProductDetailsScreen(
            modifier = Modifier,
            uiState = uiState,
            onAction = viewModel::onAction,
            onBackClick = onBackClick
        )
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
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
    onAction: (ProductDetailsAction) -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        IconButton(
            onClick = onBackClick,
            content = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back Icon"
                )
            }
        )
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
            error = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .matchParentSize()
                        .padding(6.dp)
                ) {
                    Text(
                        text = "Error loading image",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                }
            },
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
        onAction = {},
        onBackClick = {}
    )
}

private val testUiState = ProductDetailsUiState(
    product = ProductDetailsPreviewData.product1
)