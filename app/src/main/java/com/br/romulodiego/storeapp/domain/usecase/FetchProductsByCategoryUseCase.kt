package com.br.romulodiego.storeapp.domain.usecase

import com.br.romulodiego.storeapp.data.model.Product
import com.br.romulodiego.storeapp.domain.repository.ProductRepository

class FetchProductsByCategoryUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(category: String): List<Product> {
        return productRepository.getProductsByCategory(category)
    }
}