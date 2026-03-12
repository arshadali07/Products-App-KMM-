package com.example.exploreproducts

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform