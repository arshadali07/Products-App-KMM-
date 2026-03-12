package com.example.exploreproducts

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import com.example.exploreproducts.presentation.theme.ProductsThemeAppTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview
fun App() {
    ProductsThemeAppTheme {
        NavigationRoot()
    }
}