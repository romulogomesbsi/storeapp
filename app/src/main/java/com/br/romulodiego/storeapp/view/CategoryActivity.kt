// CategoryActivity.kt
package com.br.romulodiego.storeapp.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.romulodiego.storeapp.adapter.CategoryAdapter
import com.br.romulodiego.storeapp.databinding.ActivityCategoryBinding
import com.br.romulodiego.storeapp.viewmodel.CategoryViewModel

class CategoryActivity : AppCompatActivity() {
    private val categoryViewModel: CategoryViewModel by viewModels()
    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        categoryViewModel.categories.observe(this, Observer { categories ->
            // Update UI with categories
            binding.recyclerViewCategories.layoutManager = LinearLayoutManager(this)
            binding.recyclerViewCategories.adapter = CategoryAdapter(categories) { category ->
                val intent = Intent(this, ProductActivity::class.java)
                intent.putExtra("category", category.toString())
                startActivity(intent)
            }
        })

        categoryViewModel.fetchCategories()
    }
}