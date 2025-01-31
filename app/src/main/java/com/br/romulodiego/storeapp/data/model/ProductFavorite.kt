// FavoriteProduct.kt
package com.br.romulodiego.storeapp.data.model

import java.io.Serializable

data class ProductFavorite(
    val id: Int,
    val title: String,
    val price: Double,
) : Serializable