// ProductsFavorites.kt
package com.br.romulodiego.storeapp.presentation.view

import ProductFavoriteAdapter
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.romulodiego.storeapp.databinding.ActivityProductsFavoritesBinding
import com.br.romulodiego.storeapp.data.model.ProductFavorite
import com.br.romulodiego.storeapp.data.local.ProductFavoriteDAO

class ProductsFavoritesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductsFavoritesBinding

    private var productFavoriteList = emptyList<ProductFavorite>()
    private var productFavoriteAdapter: ProductFavoriteAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Favoritos"

        productFavoriteAdapter = ProductFavoriteAdapter { id -> confirmDelete(id) }
        binding.recyclerViewFavorites.adapter = productFavoriteAdapter
        binding.recyclerViewFavorites.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        updateProductsFavoritiesList()
    }

    private fun confirmDelete(id: Int) {

        val alertBuilder = AlertDialog.Builder(this)

        alertBuilder.setTitle("Confirmar exclusão")
        alertBuilder.setMessage("Deseja realmente excluir a tarefas?")

        alertBuilder.setPositiveButton("Sim"){ _, _ ->

            val productFavoriteDAO = ProductFavoriteDAO(this)
            if ( productFavoriteDAO.remover( id ) ){
                updateProductsFavoritiesList()
                Toast.makeText(
                    this,
                    "Sucesso ao remover tarefa", Toast.LENGTH_SHORT
                ).show()
            }else{
                Toast.makeText(
                    this,
                    "Erro ao remover tarefa", Toast.LENGTH_SHORT
                ).show()
            }

        }

        alertBuilder.setNegativeButton("Não"){ _, _ -> }

        alertBuilder.create().show()

    }


    private fun updateProductsFavoritiesList(){

        val productFavoriteDAO = ProductFavoriteDAO(this)
        productFavoriteList = productFavoriteDAO.listar()
        productFavoriteAdapter?.addToList( productFavoriteList )
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