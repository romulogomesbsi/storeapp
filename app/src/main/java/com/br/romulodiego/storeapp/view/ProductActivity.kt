// ProductActivity.kt
package com.br.romulodiego.storeapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.romulodiego.storeapp.R
import com.br.romulodiego.storeapp.adapter.ProductAdapter
import com.br.romulodiego.storeapp.databinding.ActivityProductBinding
import com.br.romulodiego.storeapp.viewmodel.ProductViewModel

class ProductActivity : AppCompatActivity() {
    private val productViewModel: ProductViewModel by viewModels()
    private lateinit var binding: ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Produtos"
        val category = intent.getStringExtra("category") ?: return

        productViewModel.products.observe(this, Observer { products ->
            binding.recyclerViewProducts.layoutManager = LinearLayoutManager(this)
            binding.recyclerViewProducts.adapter = ProductAdapter(products) { product ->
                val intent = Intent(this, ProductDetailActivity::class.java)
                intent.putExtra("product", product)
                startActivity(intent)
            }
        })

        productViewModel.fetchProductsByCategory(category)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.action_favorite -> {
                val intent = Intent(this, ProductsFavoritesActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}