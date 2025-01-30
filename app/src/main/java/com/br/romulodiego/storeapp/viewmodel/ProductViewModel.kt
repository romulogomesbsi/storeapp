// ProductViewModel.kt
package com.br.romulodiego.storeapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.br.romulodiego.storeapp.database.ProdutoFavoritoDAO
import com.br.romulodiego.storeapp.model.Product
import com.br.romulodiego.storeapp.network.RetrofitInstance
import kotlinx.coroutines.launch

class ProductViewModel(private val context: Context) : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    private val wishlist = mutableSetOf<Int>()

    private suspend fun isProductInWishlist(productId: Int): Boolean {
        val produtoFavoritoDAO = ProdutoFavoritoDAO(context)
        return produtoFavoritoDAO.buscarPorId(productId)
    }

    fun fetchProductsByCategory(category: String) {
        viewModelScope.launch {
            val products = RetrofitInstance.api.getProductsByCategory(category)
            val updatedProducts = products.map { product ->
                val isInWishlist = isProductInWishlist(product.id)
                product.copy(isInWishlist = isInWishlist)
            }
            _products.postValue(updatedProducts)
        }
    }

    fun searchProducts(query: String) {
        viewModelScope.launch {
            val products = RetrofitInstance.api.searchProducts(query)
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

