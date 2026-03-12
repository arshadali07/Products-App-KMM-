package com.example.exploreproducts.domain.model.response

data class ProductsMain(
    val products: List<Product>?,
    val limit: Int?,
    val skip: Int?,
    val total: Int?
)

data class Product(
    val availabilityStatus: String?,
    val brand: String?,
    val category: String?,
    val description: String?,
    val dimensions: ProductDimensions?,
    val discountPercentage: Double?,
    val id: Int?,
    val images: List<String>?,
    val meta: ProductMeta?,
    val minimumOrderQuantity: Int?,
    val price: Double?,
    val rating: Double?,
    val returnPolicy: String?,
    val reviews: List<ProductReview>?,
    val shippingInformation: String?,
    val sku: String?,
    val stock: Int?,
    val tags: List<String>?,
    val thumbnail: String?,
    val title: String?,
    val warrantyInformation: String?,
    val weight: Int?
)

data class ProductDimensions(
    val depth: Double?,
    val height: Double?,
    val width: Double?
)

data class ProductMeta(
    val barcode: String?,
    val createdAt: String?,
    val qrCode: String?,
    val updatedAt: String?
)

data class ProductReview(
    val comment: String?,
    val date: String?,
    val rating: Int?,
    val reviewerEmail: String?,
    val reviewerName: String?
)