package com.br.romulodiego.storeapp.domain.usecase

import com.br.romulodiego.storeapp.domain.repository.ProductRepository

class IsProductInWishlistUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(productId: Int): Boolean {
        return productRepository.isProductInWishlist(productId)
    }
}