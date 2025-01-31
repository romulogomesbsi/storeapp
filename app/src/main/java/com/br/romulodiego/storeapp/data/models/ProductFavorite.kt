// FavoriteProduct.kt
package com.br.romulodiego.storeapp.data.models

import java.io.Serializable

data class ProductFavorite(
    val id: Int,
    val image: String,
    val title: String,
    val price: Double,
) : Serializable