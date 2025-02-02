package com.br.romulodiego.storeapp.presentation.view


import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.br.romulodiego.storeapp.databinding.ActivityProductDetailBinding
import com.br.romulodiego.storeapp.data.models.Product
import com.br.romulodiego.storeapp.data.models.ProductFavorite
import com.br.romulodiego.storeapp.data.local.ProductFavoriteDAO
import com.squareup.picasso.Picasso

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val product = intent.getParcelableExtra<Product>("product")
        val isInWishlist = intent.getBooleanExtra("isWishList", false)
        if (isInWishlist) {
            binding.favoriteButton.visibility = View.INVISIBLE
        }
        product?.let {

            val imageUrl = product.image
            Picasso.get()
                .load(imageUrl)
                .into(binding.productImage)
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
    fun saveFavorite(view: View) {
        val productFavoriteDAO = ProductFavoriteDAO(this)
        val product = intent.getParcelableExtra<Product>("product")
        product?.let {
//            val imageUrl = product.image
//            Picasso.get()
//                .load(imageUrl)
//                .into(binding.productImage)
//            binding.productImage = it
//            binding.productTitle.text = it.title
//            binding.productPrice.text = it.price.toString()


            val productFavorite = ProductFavorite(
                id = it.id,
                image = it.image,
                title = it.title,
                price = it.price,
            )
            val sucesso = productFavoriteDAO.save(productFavorite)
            if (sucesso) {
                Toast.makeText(this, "Produto salvo na lista de favoritos", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Erro ao salvar produto na lista de favoritos", Toast.LENGTH_LONG).show()
            }
        }

    }
}