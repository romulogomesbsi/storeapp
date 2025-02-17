// ApiService.kt
package com.br.romulodiego.storeapp.data.network

import com.br.romulodiego.storeapp.data.models.Product
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("products/categories")
    suspend fun getCategories(): List<String>

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(@Path("category") category: String): List<Product>

    @GET("products/")
    suspend fun searchProducts(@Query("q") query: String): List<Product>
}