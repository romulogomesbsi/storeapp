// WishlistItem.kt
package com.br.romulodiego.storeapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishlist_items")
data class WishlistItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productId: Int,
    val productImage: String,
    val productName: String,
    val productPrice: Double
)