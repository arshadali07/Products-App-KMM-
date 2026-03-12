package com.example.exploreproducts.data.networking

import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

object UrlConstants {
    const val BASE_URL_HTTP = "https://dummyjson.com"
}

fun constructRoute(route: String): String {
    return when {
        route.contains(UrlConstants.BASE_URL_HTTP) -> route
        route.startsWith("/") -> "${UrlConstants.BASE_URL_HTTP}$route"
        else -> "${UrlConstants.BASE_URL_HTTP}/$route"
    }
}

suspend inline fun <reified T> safeCall(
    apiCall: suspend () -> T?,
): Result<T> {
    return try {
        val result = apiCall()
        if (result != null) {
            Result.success(result)
        } else {
            Result.failure(EmptyResultException(message = "Empty result"))
        }
    } catch (t: Throwable) {
        Result.failure(t)
    }
}

class EmptyResultException(override val message: String) : Exception(message)
class SomeRemoteException(override val message: String) : Exception(message)

suspend inline fun <reified T> responseToResult(response: HttpResponse): Result<T> {
    return when(val code = response.status.value) {
        in 200..299 -> {
            try {
                Result.success(response.body<T>())
            } catch(e: NoTransformationFoundException) {
                Result.failure(e)
            }
        }
        else -> Result.failure(SomeRemoteException("Code = $code, Message = ${response.status.value} is not implemented"))
    }
}