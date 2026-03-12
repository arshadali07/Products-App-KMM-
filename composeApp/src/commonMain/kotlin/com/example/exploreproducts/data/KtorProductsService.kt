package com.example.exploreproducts.data

import com.example.exploreproducts.data.mapper.toDomain
import com.example.exploreproducts.data.model.response.ProductDto
import com.example.exploreproducts.data.model.response.ProductsMainDto
import com.example.exploreproducts.data.networking.EmptyResultException
import com.example.exploreproducts.data.networking.HttpClientFactory
import com.example.exploreproducts.data.networking.constructRoute
import com.example.exploreproducts.data.networking.getHttpClientEngine
import com.example.exploreproducts.data.networking.responseToResult
import com.example.exploreproducts.data.networking.safeCall
import com.example.exploreproducts.domain.ProductsService
import com.example.exploreproducts.domain.model.response.Product
import com.example.exploreproducts.domain.model.response.ProductsMain
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import kotlin.collections.component1
import kotlin.collections.component2

class KtorProductsService(
    private val client: HttpClient = HttpClientFactory().create(getHttpClientEngine())
) : ProductsService {

    override suspend fun getPaginatedProducts(
        queryParams: Map<String, Any>,
    ): Result<ProductsMain> {
        return safeCall {
            val response = client.get {
                url(constructRoute("/products"))
                queryParams.forEach { (key, value) ->
                    parameter(key, value)
                }
            }
            val responseResult = responseToResult<ProductsMainDto>(response)
            if (responseResult.isSuccess) {
                val responseData = responseResult.getOrNull() ?: throw EmptyResultException("Empty Result")
                responseData.toDomain()
            } else {
                throw (responseResult.exceptionOrNull() ?: Exception("Different Exception"))
            }
        }
    }

    override suspend fun getProductDetail(productId: String?): Result<Product> {
        return safeCall {
            val response = client.get {
                url(constructRoute("/products/$productId"))
            }
            val responseResult = responseToResult<ProductDto>(response)
            if (responseResult.isSuccess) {
                val responseData = responseResult.getOrNull() ?: throw EmptyResultException("Empty Result")
                responseData.toDomain()
            } else {
                throw (responseResult.exceptionOrNull() ?: Exception("Different Exception"))
            }
        }
    }
}