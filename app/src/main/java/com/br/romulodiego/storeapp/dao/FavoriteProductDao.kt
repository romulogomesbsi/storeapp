// FavoriteProductDao.kt
package com.br.romulodiego.storeapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.br.romulodiego.storeapp.model.FavoriteProduct
import androidx.lifecycle.LiveData

@Dao
interface FavoriteProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteProduct: FavoriteProduct)

    @Query("SELECT * FROM favorite_products")
    fun getAll(): LiveData<List<FavoriteProduct>>

    @Query("DELETE FROM favorite_products WHERE id = :id")
    fun deleteById(id: Int): Int


}