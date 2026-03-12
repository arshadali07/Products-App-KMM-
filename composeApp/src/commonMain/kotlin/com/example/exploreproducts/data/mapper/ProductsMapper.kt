package com.example.exploreproducts.data.mapper

import com.example.exploreproducts.data.model.response.ProductDimensionsDto
import com.example.exploreproducts.data.model.response.ProductDto
import com.example.exploreproducts.data.model.response.ProductMetaDto
import com.example.exploreproducts.data.model.response.ProductReviewDto
import com.example.exploreproducts.data.model.response.ProductsMainDto
import com.example.exploreproducts.domain.model.response.Product
import com.example.exploreproducts.domain.model.response.ProductDimensions
import com.example.exploreproducts.domain.model.response.ProductMeta
import com.example.exploreproducts.domain.model.response.ProductReview
import com.example.exploreproducts.domain.model.response.ProductsMain

// ProductsMainDto -> ProductsMain
fun ProductsMainDto.toDomain(): ProductsMain {
    return ProductsMain(
        products = products?.map { it.toDomain() },
        limit = limit,
        skip = skip,
        total = total
    )
}

// ProductDto -> Product
fun ProductDto.toDomain(): Product {
    return Product(
        availabilityStatus = availabilityStatus,
        brand = brand,
        category = category,
        description = description,
        dimensions = dimensions?.toDomain(),
        discountPercentage = discountPercentage,
        id = id,
        images = images,
        meta = meta?.toDomain(),
        minimumOrderQuantity = minimumOrderQuantity,
        price = price,
        rating = rating,
        returnPolicy = returnPolicy,
        reviews = reviews?.map { it.toDomain() },
        shippingInformation = shippingInformation,
        sku = sku,
        stock = stock,
        tags = tags,
        thumbnail = thumbnail,
        title = title,
        warrantyInformation = warrantyInformation,
        weight = weight
    )
}

// ProductDimensionsDto -> ProductDimensions
fun ProductDimensionsDto.toDomain(): ProductDimensions {
    return ProductDimensions(
        depth = depth,
        height = height,
        width = width
    )
}

// ProductMetaDto -> ProductMeta
fun ProductMetaDto.toDomain(): ProductMeta {
    return ProductMeta(
        barcode = barcode,
        createdAt = createdAt,
        qrCode = qrCode,
        updatedAt = updatedAt
    )
}

// ProductReviewDto -> ProductReview
fun ProductReviewDto.toDomain(): ProductReview {
    return ProductReview(
        comment = comment,
        date = date,
        rating = rating,
        reviewerEmail = reviewerEmail,
        reviewerName = reviewerName
    )
}