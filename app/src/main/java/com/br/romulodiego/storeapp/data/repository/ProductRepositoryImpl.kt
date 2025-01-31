package com.br.romulodiego.storeapp.data.repository


import android.content.Context
import com.br.romulodiego.storeapp.data.local.ProductFavoriteDAO
import com.br.romulodiego.storeapp.data.models.Product
import com.br.romulodiego.storeapp.data.network.RetrofitInstance
import com.br.romulodiego.storeapp.domain.repository.ProductRepository

class ProductRepositoryImpl(private val context: Context) : ProductRepository {
    private val productFavoriteDAO = ProductFavoriteDAO(context)

    override suspend fun getProductsByCategory(category: String): List<Product> {
        return RetrofitInstance.api.getProductsByCategory(category)
    }

    override suspend fun searchProducts(query: String): List<Product> {
        return RetrofitInstance.api.searchProducts(query)
    }

    override suspend fun isProductInWishlist(productId: Int): Boolean {
        return productFavoriteDAO.findbyId(productId)
    }
}