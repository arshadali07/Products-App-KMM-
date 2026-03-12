package com.example.exploreproducts.domain

import androidx.paging.PagingData
import com.example.exploreproducts.domain.model.response.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun getPaginatedProducts(): Flow<PagingData<Product>>
    suspend fun getProductDetail(productId: String?): Result<Product>
}