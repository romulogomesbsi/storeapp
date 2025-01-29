package com.br.romulodiego.storeapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.br.romulodiego.storeapp.dao.FavoriteProductDao
import com.br.romulodiego.storeapp.model.FavoriteProduct

@Database(entities = [FavoriteProduct::class], version = 1) abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteProductDao(): FavoriteProductDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "products_favorites"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}