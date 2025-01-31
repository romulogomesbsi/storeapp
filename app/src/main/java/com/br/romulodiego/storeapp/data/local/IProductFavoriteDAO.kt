package com.br.romulodiego.storeapp.data.local

import com.br.romulodiego.storeapp.data.model.ProductFavorite


interface IProductFavoriteDAO {

    fun salvar( tarefa: ProductFavorite): Boolean
    fun remover( idTarefa: Int ): Boolean
    fun listar(): List<ProductFavorite>

    fun buscarPorId( idTarefa: Int ): Boolean
}