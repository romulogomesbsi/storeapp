// CategoryActivity.kt
package com.br.romulodiego.storeapp.presentation.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.romulodiego.storeapp.presentation.adapter.CategoryAdapter
import com.br.romulodiego.storeapp.databinding.ActivityCategoryBinding
import com.br.romulodiego.storeapp.presentation.viewmodel.CategoryViewModel

class CategoryActivity : AppCompatActivity() {
    private val categoryViewModel: CategoryViewModel by viewModels()
    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.title = "Selecione uma categoria"

        categoryViewModel.categories.observe(this, Observer { categories ->
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