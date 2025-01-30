package com.br.romulodiego.storeapp.view


import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.br.romulodiego.storeapp.R
import com.br.romulodiego.storeapp.databinding.ActivityProductDetailBinding
import com.br.romulodiego.storeapp.model.Product
import com.br.romulodiego.storeapp.model.ProductFavorite
import com.jamiltondamasceno.applistatarefas.database.ProdutoFavoritoDAO
import kotlin.random.Random

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

            val images = listOf(R.drawable.image1, R.drawable.image2, R.drawable.image3)
            val randomImage = images[Random.nextInt(images.size)]
            binding.productImage.setImageResource(randomImage)
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

    fun salvarFavorito(view: View){
        val produtoFavoritoDAO = ProdutoFavoritoDAO(this) // Instancia o DAO
        val product = intent.getParcelableExtra<Product>("product")
        product?.let {
            val images = listOf(R.drawable.image1, R.drawable.image2, R.drawable.image3)
            val randomImage = images[Random.nextInt(images.size)]
            binding.productImage.setImageResource(randomImage)
            binding.productTitle.text = it.title
            binding.productPrice.text = it.price.toString()

            val productFavorite = ProductFavorite(
                id = 0, // ID será gerado automaticamente pelo banco de dados
                title = it.title,
                price = it.price,
            )
            val sucesso = produtoFavoritoDAO.salvar(productFavorite)
            if (sucesso) {
                Toast.makeText(this, "Produto salvo na lista de favoritos", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Erro ao salvar produto na lista de favoritos", Toast.LENGTH_LONG).show()
            }
        }


    }
}