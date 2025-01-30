// ProductsFavorites.kt
package com.br.romulodiego.storeapp.view

import ProdutoFavoritoAdapter
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.romulodiego.storeapp.databinding.ActivityProductBinding
import com.br.romulodiego.storeapp.databinding.ActivityProductsFavoritesBinding
import com.br.romulodiego.storeapp.model.ProductFavorite
import com.jamiltondamasceno.applistatarefas.database.ProdutoFavoritoDAO

class ProductsFavoritesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductsFavoritesBinding

    private var productFavoriteList = emptyList<ProductFavorite>()
    private var productFavoriteAdapter: ProdutoFavoritoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Favoritos"

        productFavoriteAdapter = ProdutoFavoritoAdapter { id -> confirmarExclusao(id) }
        binding.recyclerViewFavorites.adapter = productFavoriteAdapter
        binding.recyclerViewFavorites.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        atualizaListaProdutosFavoritos()
    }

    private fun confirmarExclusao(id: Int) {

        val alertBuilder = AlertDialog.Builder(this)

        alertBuilder.setTitle("Confirmar exclusão")
        alertBuilder.setMessage("Deseja realmente excluir a tarefas?")

        alertBuilder.setPositiveButton("Sim"){ _, _ ->

            val tarefaDAO = ProdutoFavoritoDAO(this)
            if ( tarefaDAO.remover( id ) ){
                atualizaListaProdutosFavoritos()
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


    private fun atualizaListaProdutosFavoritos(){

        val produtoFavoritoDAODAO = ProdutoFavoritoDAO(this)
        productFavoriteList = produtoFavoritoDAODAO.listar()
        productFavoriteAdapter?.adicionarLista( productFavoriteList )
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