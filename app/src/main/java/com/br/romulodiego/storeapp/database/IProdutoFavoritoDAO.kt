package com.jamiltondamasceno.applistatarefas.database

import com.br.romulodiego.storeapp.model.ProductFavorite


interface IProdutoFavoritoDAO {

    fun salvar( tarefa: ProductFavorite): Boolean
    fun remover( idTarefa: Int ): Boolean
    fun listar(): List<ProductFavorite>

}