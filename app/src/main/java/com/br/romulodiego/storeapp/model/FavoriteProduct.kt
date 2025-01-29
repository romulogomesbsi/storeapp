// FavoriteProduct.kt
package com.br.romulodiego.storeapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_products")
data class FavoriteProduct(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val price: Double,
    val category: String,
    val description: String
)