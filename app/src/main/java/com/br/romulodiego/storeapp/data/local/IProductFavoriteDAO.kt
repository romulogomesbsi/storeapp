package com.br.romulodiego.storeapp.data.local

import com.br.romulodiego.storeapp.model.ProductFavorite


interface IProdutoFavoritoDAO {

    fun salvar( tarefa: ProductFavorite): Boolean
    fun remover( idTarefa: Int ): Boolean
    fun listar(): List<ProductFavorite>

    fun buscarPorId( idTarefa: Int ): Boolean
}