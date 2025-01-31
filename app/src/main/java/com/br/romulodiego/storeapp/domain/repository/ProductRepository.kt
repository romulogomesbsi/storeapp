package com.br.romulodiego.storeapp.domain.repository

import com.br.romulodiego.storeapp.data.models.Product

interface ProductRepository {
    suspend fun getProductsByCategory(category: String): List<Product>
    suspend fun searchProducts(query: String): List<Product>
    suspend fun isProductInWishlist(productId: Int): Boolean
}