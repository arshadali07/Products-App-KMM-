package com.example.exploreproducts.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.exploreproducts.domain.ProductsService
import com.example.exploreproducts.domain.model.response.Product
import kotlinx.io.IOException

class ProductsPagingSource(
    private val productsService: ProductsService
) : PagingSource<Int, Product>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val page = params.key ?: 0
        val loadSize = params.loadSize
        var loadResult: LoadResult<Int, Product> = LoadResult.Page(
            data = emptyList(),
            prevKey = null,
            nextKey = null
        )
        try {
            val response = productsService.getPaginatedProducts(
                queryParams = mapOf(
                    "limit" to loadSize,
                    "skip" to page * loadSize
                )
            )
            response
                .onSuccess { result ->
                    val movies = result.products.orEmpty()
                    loadResult = LoadResult.Page(
                        data = movies,
                        prevKey = if (page == 0) null else page - 1,
                        nextKey = if (movies.isEmpty()) null else page + 1
                    )
                }
                .onFailure { result ->
                    loadResult = LoadResult.Error(IOException(result.message))
                }
        } catch (e: Exception) {
            loadResult = LoadResult.Error(e)
        }
        return loadResult
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}