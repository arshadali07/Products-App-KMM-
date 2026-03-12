package com.example.exploreproducts.presentation.details

import com.example.exploreproducts.domain.model.response.Product
import com.example.exploreproducts.domain.model.response.ProductDimensions
import com.example.exploreproducts.domain.model.response.ProductMeta
import com.example.exploreproducts.domain.model.response.ProductReview
import com.example.exploreproducts.domain.model.response.ProductsMain

object ProductDetailsPreviewData {

    val reviews = listOf(
        ProductReview(
            rating = 5,
            comment = "Great product!",
            date = "2025-04-30T09:41:02.053Z",
            reviewerName = "Emma Miller",
            reviewerEmail = "emma.miller@test.com"
        ),
        ProductReview(
            rating = 4,
            comment = "Would buy again!",
            date = "2025-04-30T09:41:02.053Z",
            reviewerName = "John Carter",
            reviewerEmail = "john.carter@test.com"
        )
    )

    val dimensions = ProductDimensions(
        width = 15.14,
        height = 13.08,
        depth = 22.99
    )

    val meta = ProductMeta(
        barcode = "5784719087687",
        createdAt = "2025-04-30T09:41:02.053Z",
        updatedAt = "2025-04-30T09:41:02.053Z",
        qrCode = "https://cdn.dummyjson.com/public/qr-code.png"
    )

    val product1 = Product(
        id = 1,
        title = "Essence Mascara Lash Princess",
        description = "Popular mascara known for volumizing and lengthening effects.",
        category = "beauty",
        price = 9.99,
        discountPercentage = 10.48,
        rating = 4.5,
        stock = 99,
        tags = listOf("beauty", "mascara"),
        brand = "Essence",
        sku = "BEA-ESS-ESS-001",
        weight = 4,
        dimensions = dimensions,
        warrantyInformation = "1 week warranty",
        shippingInformation = "Ships in 3-5 business days",
        availabilityStatus = "In Stock",
        reviews = reviews,
        returnPolicy = "No return policy",
        minimumOrderQuantity = 48,
        meta = meta,
        images = listOf("https://cdn.dummyjson.com/product-images/beauty/essence-mascara-lash-princess/1.webp"),
        thumbnail = "https://cdn.dummyjson.com/product-images/beauty/essence-mascara-lash-princess/thumbnail.webp"
    )

    val product2 = Product(
        id = 2,
        title = "Eyeshadow Palette with Mirror",
        description = "Versatile range of eyeshadow shades for stunning looks.",
        category = "beauty",
        price = 19.99,
        discountPercentage = 18.19,
        rating = 4.2,
        stock = 34,
        tags = listOf("beauty", "eyeshadow"),
        brand = "Glamour Beauty",
        sku = "BEA-GLA-EYE-002",
        weight = 9,
        dimensions = dimensions,
        warrantyInformation = "1 year warranty",
        shippingInformation = "Ships in 2 weeks",
        availabilityStatus = "In Stock",
        reviews = reviews,
        returnPolicy = "7 days return policy",
        minimumOrderQuantity = 20,
        meta = meta,
        images = listOf("https://cdn.dummyjson.com/product-images/beauty/eyeshadow-palette-with-mirror/1.webp"),
        thumbnail = "https://cdn.dummyjson.com/product-images/beauty/eyeshadow-palette-with-mirror/thumbnail.webp"
    )

    val product3 = Product(
        id = 3,
        title = "Calvin Klein CK One",
        description = "Fresh and clean unisex fragrance for everyday wear.",
        category = "fragrances",
        price = 49.99,
        discountPercentage = 1.89,
        rating = 4.3,
        stock = 29,
        tags = listOf("fragrances", "perfumes"),
        brand = "Calvin Klein",
        sku = "FRA-CAL-CAL-006",
        weight = 7,
        dimensions = dimensions,
        warrantyInformation = "1 week warranty",
        shippingInformation = "Ships overnight",
        availabilityStatus = "In Stock",
        reviews = reviews,
        returnPolicy = "90 days return policy",
        minimumOrderQuantity = 9,
        meta = meta,
        images = listOf("https://cdn.dummyjson.com/product-images/fragrances/calvin-klein-ck-one/1.webp"),
        thumbnail = "https://cdn.dummyjson.com/product-images/fragrances/calvin-klein-ck-one/thumbnail.webp"
    )

    val productsMain = ProductsMain(
        products = listOf(product1, product2, product3),
        limit = 10,
        skip = 0,
        total = 194
    )
}