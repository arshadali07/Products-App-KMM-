package com.example.exploreproducts.data

import com.example.exploreproducts.domain.ProductsService
import com.example.exploreproducts.domain.model.response.Product
import com.example.exploreproducts.domain.model.response.ProductsMain
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class RemoteProductsRepositoryTest {

    // region helpers

    private fun buildProduct(id: Int = 1, title: String = "Test Product") = Product(
        id = id,
        title = title,
        brand = "TestBrand",
        category = "electronics",
        description = "A test product",
        price = 99.99,
        rating = 4.5,
        stock = 100,
        availabilityStatus = "In Stock",
        discountPercentage = 10.0,
        dimensions = null,
        images = listOf("https://example.com/img.jpg"),
        meta = null,
        minimumOrderQuantity = 1,
        returnPolicy = "30-day return",
        reviews = emptyList(),
        shippingInformation = "Ships in 2 days",
        sku = "SKU-001",
        tags = listOf("sale"),
        thumbnail = "https://example.com/thumb.jpg",
        warrantyInformation = "1-year warranty",
        weight = 200
    )

    private fun buildProductsMain(
        products: List<Product> = listOf(buildProduct()),
        total: Int = 1,
        skip: Int = 0,
        limit: Int = 10,
    ) = ProductsMain(products = products, total = total, skip = skip, limit = limit)

    // endregion

    // region fake service implementations

    private inner class FakeProductsService(
        private val productDetailResult: Result<Product> = Result.success(buildProduct()),
        private val searchedProductsResult: Result<ProductsMain> = Result.success(buildProductsMain()),
        private val categoriesResult: Result<List<String>> = Result.success(listOf("electronics", "clothing")),
        private val productsByCategoryResult: Result<ProductsMain> = Result.success(buildProductsMain()),
        private val paginatedProductsResult: Result<ProductsMain> = Result.success(buildProductsMain()),
    ) : ProductsService {
        var lastProductDetailId: String? = null
        var lastSearchQuery: String? = null
        var lastCategory: String? = null
        var lastPaginationParams: Map<String, Any> = emptyMap()

        override suspend fun getPaginatedProducts(queryParams: Map<String, Any>): Result<ProductsMain> {
            lastPaginationParams = queryParams
            return paginatedProductsResult
        }

        override suspend fun getProductDetail(productId: String?): Result<Product> {
            lastProductDetailId = productId
            return productDetailResult
        }

        override suspend fun getSearchedProducts(query: String?): Result<ProductsMain> {
            lastSearchQuery = query
            return searchedProductsResult
        }

        override suspend fun getProductsCategory(): Result<List<String>> = categoriesResult

        override suspend fun getProductsByCategory(productCategory: String?): Result<ProductsMain> {
            lastCategory = productCategory
            return productsByCategoryResult
        }
    }

    // endregion

    // region getProductDetail

    @Test
    fun getProductDetail_returnsSuccessFromService() = runTest {
        val expected = buildProduct(id = 42, title = "Special Item")
        val service = FakeProductsService(productDetailResult = Result.success(expected))
        val repository = RemoteProductsRepository(service)

        val result = repository.getProductDetail("42")

        assertTrue(result.isSuccess)
        assertEquals(expected, result.getOrNull())
    }

    @Test
    fun getProductDetail_forwardsProductIdToService() = runTest {
        val service = FakeProductsService()
        val repository = RemoteProductsRepository(service)

        repository.getProductDetail("99")

        assertEquals("99", service.lastProductDetailId)
    }

    @Test
    fun getProductDetail_withNullId_forwardsNullToService() = runTest {
        val service = FakeProductsService()
        val repository = RemoteProductsRepository(service)

        repository.getProductDetail(null)

        assertEquals(null, service.lastProductDetailId)
    }

    @Test
    fun getProductDetail_propagatesFailureFromService() = runTest {
        val error = RuntimeException("Network error")
        val service = FakeProductsService(productDetailResult = Result.failure(error))
        val repository = RemoteProductsRepository(service)

        val result = repository.getProductDetail("1")

        assertTrue(result.isFailure)
        assertEquals(error, result.exceptionOrNull())
    }

    // endregion

    // region getSearchedProducts

    @Test
    fun getSearchedProducts_returnsSuccessFromService() = runTest {
        val productsMain = buildProductsMain(total = 5)
        val service = FakeProductsService(searchedProductsResult = Result.success(productsMain))
        val repository = RemoteProductsRepository(service)

        val result = repository.getSearchedProducts("laptop")

        assertTrue(result.isSuccess)
        assertEquals(5, result.getOrNull()?.total)
    }

    @Test
    fun getSearchedProducts_forwardsQueryToService() = runTest {
        val service = FakeProductsService()
        val repository = RemoteProductsRepository(service)

        repository.getSearchedProducts("phone")

        assertEquals("phone", service.lastSearchQuery)
    }

    @Test
    fun getSearchedProducts_withNullQuery_forwardsNullToService() = runTest {
        val service = FakeProductsService()
        val repository = RemoteProductsRepository(service)

        repository.getSearchedProducts(null)

        assertEquals(null, service.lastSearchQuery)
    }

    @Test
    fun getSearchedProducts_propagatesFailureFromService() = runTest {
        val error = RuntimeException("Search failed")
        val service = FakeProductsService(searchedProductsResult = Result.failure(error))
        val repository = RemoteProductsRepository(service)

        val result = repository.getSearchedProducts("tablet")

        assertTrue(result.isFailure)
        assertEquals(error, result.exceptionOrNull())
    }

    // endregion

    // region getProductsCategory

    @Test
    fun getProductsCategory_returnsSuccessFromService() = runTest {
        val categories = listOf("electronics", "clothing", "furniture")
        val service = FakeProductsService(categoriesResult = Result.success(categories))
        val repository = RemoteProductsRepository(service)

        val result = repository.getProductsCategory()

        assertTrue(result.isSuccess)
        assertEquals(categories, result.getOrNull())
    }

    @Test
    fun getProductsCategory_returnsEmptyListWhenServiceReturnsEmpty() = runTest {
        val service = FakeProductsService(categoriesResult = Result.success(emptyList()))
        val repository = RemoteProductsRepository(service)

        val result = repository.getProductsCategory()

        assertTrue(result.isSuccess)
        assertEquals(emptyList(), result.getOrNull())
    }

    @Test
    fun getProductsCategory_propagatesFailureFromService() = runTest {
        val error = RuntimeException("Categories unavailable")
        val service = FakeProductsService(categoriesResult = Result.failure(error))
        val repository = RemoteProductsRepository(service)

        val result = repository.getProductsCategory()

        assertTrue(result.isFailure)
        assertEquals(error, result.exceptionOrNull())
    }

    // endregion

    // region getProductsByCategory

    @Test
    fun getProductsByCategory_returnsSuccessFromService() = runTest {
        val electronics = buildProductsMain(products = listOf(buildProduct(1, "TV"), buildProduct(2, "Laptop")), total = 2)
        val service = FakeProductsService(productsByCategoryResult = Result.success(electronics))
        val repository = RemoteProductsRepository(service)

        val result = repository.getProductsByCategory("electronics")

        assertTrue(result.isSuccess)
        assertEquals(2, result.getOrNull()?.total)
    }

    @Test
    fun getProductsByCategory_forwardsCategoryToService() = runTest {
        val service = FakeProductsService()
        val repository = RemoteProductsRepository(service)

        repository.getProductsByCategory("clothing")

        assertEquals("clothing", service.lastCategory)
    }

    @Test
    fun getProductsByCategory_withNullCategory_forwardsNullToService() = runTest {
        val service = FakeProductsService()
        val repository = RemoteProductsRepository(service)

        repository.getProductsByCategory(null)

        assertEquals(null, service.lastCategory)
    }

    @Test
    fun getProductsByCategory_propagatesFailureFromService() = runTest {
        val error = RuntimeException("Category not found")
        val service = FakeProductsService(productsByCategoryResult = Result.failure(error))
        val repository = RemoteProductsRepository(service)

        val result = repository.getProductsByCategory("unknown")

        assertTrue(result.isFailure)
        assertEquals(error, result.exceptionOrNull())
    }

    // endregion

    // region getPaginatedProducts

    @Test
    fun getPaginatedProducts_returnsNonNullFlow() {
        val service = FakeProductsService()
        val repository = RemoteProductsRepository(service)

        val flow = repository.getPaginatedProducts()

        assertNotNull(flow)
    }

    // endregion
}
