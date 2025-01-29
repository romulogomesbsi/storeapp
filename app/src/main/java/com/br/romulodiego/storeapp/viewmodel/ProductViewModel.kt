// ProductViewModel.kt
package com.br.romulodiego.storeapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.romulodiego.storeapp.model.Product
import com.br.romulodiego.storeapp.network.RetrofitInstance
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    fun fetchProductsByCategory(category: String) {
        viewModelScope.launch {
            val products = RetrofitInstance.api.getProductsByCategory(category)
            _products.postValue(products)
        }
    }

    fun searchProducts(query: String) {
        viewModelScope.launch {
            val products = RetrofitInstance.api.searchProducts(query)
            _products.postValue(products)
        }
    }
}