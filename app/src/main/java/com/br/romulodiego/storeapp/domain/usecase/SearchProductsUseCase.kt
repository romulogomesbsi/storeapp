package com.br.romulodiego.storeapp.domain.usecase

import com.br.romulodiego.storeapp.data.models.Product
import com.br.romulodiego.storeapp.domain.repository.ProductRepository

class SearchProductsUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(query: String): List<Product> {
        return productRepository.searchProducts(query)
    }
}