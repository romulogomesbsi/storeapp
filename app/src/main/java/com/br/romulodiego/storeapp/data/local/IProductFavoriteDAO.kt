package com.br.romulodiego.storeapp.data.local

import com.br.romulodiego.storeapp.data.models.ProductFavorite


interface IProductFavoriteDAO {

    fun save(productFavorite: ProductFavorite): Boolean
    fun delete(idProduct: Int ): Boolean
    fun listAll(): List<ProductFavorite>

    fun findById(idProduct: Int ): Boolean
}