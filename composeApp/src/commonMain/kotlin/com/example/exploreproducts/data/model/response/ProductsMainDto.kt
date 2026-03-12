package com.example.exploreproducts.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductsMainDto(
    @SerialName("products")
    val products: List<ProductDto>? = null,
    @SerialName("limit")
    val limit: Int? = null,
    @SerialName("skip")
    val skip: Int? = null,
    @SerialName("total")
    val total: Int? = null
)

@Serializable
data class ProductDto(
    @SerialName("availabilityStatus")
    val availabilityStatus: String? = null,
    @SerialName("brand")
    val brand: String? = null,
    @SerialName("category")
    val category: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("dimensions")
    val dimensions: ProductDimensionsDto? = null,
    @SerialName("discountPercentage")
    val discountPercentage: Double? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("images")
    val images: List<String>? = null,
    @SerialName("meta")
    val meta: ProductMetaDto? = null,
    @SerialName("minimumOrderQuantity")
    val minimumOrderQuantity: Int? = null,
    @SerialName("price")
    val price: Double? = null,
    @SerialName("rating")
    val rating: Double? = null,
    @SerialName("returnPolicy")
    val returnPolicy: String? = null,
    @SerialName("reviews")
    val reviews: List<ProductReviewDto>? = null,
    @SerialName("shippingInformation")
    val shippingInformation: String? = null,
    @SerialName("sku")
    val sku: String? = null,
    @SerialName("stock")
    val stock: Int? = null,
    @SerialName("tags")
    val tags: List<String>? = null,
    @SerialName("thumbnail")
    val thumbnail: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("warrantyInformation")
    val warrantyInformation: String? = null,
    @SerialName("weight")
    val weight: Int? = null
)

@Serializable
data class ProductDimensionsDto(
    @SerialName("depth")
    val depth: Double? = null,
    @SerialName("height")
    val height: Double? = null,
    @SerialName("width")
    val width: Double? = null
)

@Serializable
data class ProductMetaDto(
    @SerialName("barcode")
    val barcode: String? = null,
    @SerialName("createdAt")
    val createdAt: String? = null,
    @SerialName("qrCode")
    val qrCode: String? = null,
    @SerialName("updatedAt")
    val updatedAt: String? = null
)

@Serializable
data class ProductReviewDto(
    @SerialName("comment")
    val comment: String? = null,
    @SerialName("date")
    val date: String? = null,
    @SerialName("rating")
    val rating: Int? = null,
    @SerialName("reviewerEmail")
    val reviewerEmail: String? = null,
    @SerialName("reviewerName")
    val reviewerName: String? = null
)