// CategoryViewModel.kt
package com.br.romulodiego.storeapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.romulodiego.storeapp.model.Category
import com.br.romulodiego.storeapp.network.RetrofitInstance
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {
    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>> get() = _categories

    fun fetchCategories() {
        viewModelScope.launch {
            val categories = RetrofitInstance.api.getCategories()
            _categories.postValue(categories)
        }
    }
}