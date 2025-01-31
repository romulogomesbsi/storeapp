package com.br.romulodiego.storeapp.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.romulodiego.storeapp.data.model.Product
import com.br.romulodiego.storeapp.data.repository.ProductRepositoryImpl
import com.br.romulodiego.storeapp.domain.usecase.FetchProductsByCategoryUseCase
import com.br.romulodiego.storeapp.domain.usecase.IsProductInWishlistUseCase
import com.br.romulodiego.storeapp.domain.usecase.SearchProductsUseCase
import kotlinx.coroutines.launch

class ProductViewModel(context: Context) : ViewModel() {
    private val productRepository = ProductRepositoryImpl(context)
    private val fetchProductsByCategoryUseCase = FetchProductsByCategoryUseCase(productRepository)
    private val searchProductsUseCase = SearchProductsUseCase(productRepository)
    private val isProductInWishlistUseCase = IsProductInWishlistUseCase(productRepository)

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    private val wishlist = mutableSetOf<Int>()

    fun fetchProductsByCategory(category: String) {
        viewModelScope.launch {
            val products = fetchProductsByCategoryUseCase(category)
            val updatedProducts = products.map { product ->
                val isInWishlist = isProductInWishlistUseCase(product.id)
                product.copy(isInWishlist = isInWishlist)
            }
            _products.postValue(updatedProducts)
        }
    }

    fun searchProducts(query: String) {
        viewModelScope.launch {
            val products = searchProductsUseCase(query)
            _products.postValue(products.map { it.copy(isInWishlist = wishlist.contains(it.id)) })
        }
    }

    fun toggleWishlist(product: Product) {
        if (wishlist.contains(product.id)) {
            wishlist.remove(product.id)
        } else {
            wishlist.add(product.id)
        }
        _products.value = _products.value?.map {
            if (it.id == product.id) it.copy(isInWishlist = !it.isInWishlist) else it
        }
    }
}