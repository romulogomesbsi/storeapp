// ProductActivity.kt
package com.br.romulodiego.storeapp.presentation.view

import android.content.Intent
import android.os.Bundle
import android.os.DeadObjectException
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.romulodiego.storeapp.R
import com.br.romulodiego.storeapp.presentation.adapter.ProductAdapter
import com.br.romulodiego.storeapp.databinding.ActivityProductBinding
import com.br.romulodiego.storeapp.presentation.viewmodel.ProductViewModel
import com.br.romulodiego.storeapp.presentation.viewmodel.ProductViewModelFactory
class ProductActivity : AppCompatActivity() {
    private val productViewModel: ProductViewModel by viewModels { ProductViewModelFactory(this) }
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
                intent.putExtra("isWishList", product.isInWishlist)
                startActivity(intent)
            }
        })

        productViewModel.fetchProductsByCategory(category)
    }

    override fun onResume() {
        super.onResume()
        val category = intent.getStringExtra("category") ?: return
        productViewModel.fetchProductsByCategory(category)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = "Search for products"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    try {
                        productViewModel.searchProducts(it)
                    } catch (e: DeadObjectException) {
                        Log.e("ProductActivity", "Remote service has died", e)
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (it.isNotEmpty()) {
                        productViewModel.searchProducts(it)
                    } else {
                        val category = intent.getStringExtra("category") ?: return false
                        productViewModel.fetchProductsByCategory(category)
                    }
                }
                return true
            }
        })
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