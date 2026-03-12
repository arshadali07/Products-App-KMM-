package com.example.exploreproducts.domain

import com.example.exploreproducts.domain.model.response.Product
import com.example.exploreproducts.domain.model.response.ProductsMain

interface ProductsService {
    suspend fun getPaginatedProducts(queryParams: Map<String, Any> = mapOf()): Result<ProductsMain>
    suspend fun getProductDetail(productId: String?): Result<Product>
}