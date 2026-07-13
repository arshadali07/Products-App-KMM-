package com.example.exploreproducts.domain

import androidx.paging.PagingData
import com.example.exploreproducts.domain.model.response.Product
import com.example.exploreproducts.domain.model.response.ProductsMain
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun getPaginatedProducts(): Flow<PagingData<Product>>
    suspend fun getProductDetail(productId: String?): Result<Product>
    suspend fun getSearchedProducts(query: String?): Result<ProductsMain>
    suspend fun getProductsCategory(): Result<List<String>>
    suspend fun getProductsByCategory(productCategory: String?): Result<ProductsMain>
}