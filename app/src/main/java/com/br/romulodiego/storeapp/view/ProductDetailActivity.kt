package com.br.romulodiego.storeapp.view


import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.br.romulodiego.storeapp.R
import com.br.romulodiego.storeapp.databinding.ActivityProductDetailBinding
import com.br.romulodiego.storeapp.model.Product

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val product = intent.getParcelableExtra<Product>("product")
        product?.let {
            binding.productImage.setImageResource(R.drawable.ic_launcher_background) // Placeholder image
            binding.productTitle.text = it.title
            binding.productPrice.text = it.price.toString()
            binding.productCategory.text = it.category
            binding.productDescription.text = it.description
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}