// WishlistViewModel.kt
package com.br.romulodiego.storeapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.romulodiego.storeapp.model.Product

class WishlistViewModel : ViewModel() {
    private val _wishlist = MutableLiveData<List<Product>>()
    val wishlist: LiveData<List<Product>> get() = _wishlist

    fun addToWishlist(product: Product) {
        val currentWishlist = _wishlist.value.orEmpty().toMutableList()
        currentWishlist.add(product)
        _wishlist.postValue(currentWishlist)
    }

    fun removeFromWishlist(product: Product) {
        val currentWishlist = _wishlist.value.orEmpty().toMutableList()
        currentWishlist.remove(product)
        _wishlist.postValue(currentWishlist)
    }

    fun fetchWishlist() {
        // Fetch wishlist from local storage
    }
}