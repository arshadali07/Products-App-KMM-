package com.example.exploreproducts.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.exploreproducts.domain.ProductsRepository
import com.example.exploreproducts.domain.ProductsService
import com.example.exploreproducts.domain.model.response.Product
import com.example.exploreproducts.domain.model.response.ProductsMain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class RemoteProductsRepository(
    private val productsService: ProductsService,
) : ProductsRepository {

    override fun getPaginatedProducts(): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 1
            ),
            initialKey = 0,
            pagingSourceFactory = { ProductsPagingSource(productsService) }
        ).flow.flowOn(Dispatchers.IO)
    }

    override suspend fun getProductDetail(productId: String?): Result<Product> {
        return productsService.getProductDetail(productId)
    }

    override suspend fun getSearchedProducts(query: String?): Result<ProductsMain> {
        return productsService.getSearchedProducts(query)
    }

    override suspend fun getProductsCategory(): Result<List<String>> {
        return productsService.getProductsCategory()
    }

    override suspend fun getProductsByCategory(productCategory: String?): Result<ProductsMain> {
        return productsService.getProductsByCategory(productCategory)
    }
}