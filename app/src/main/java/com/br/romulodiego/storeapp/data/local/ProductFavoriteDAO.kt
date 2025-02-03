package com.br.romulodiego.storeapp.data.local

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.br.romulodiego.storeapp.data.models.ProductFavorite

class ProductFavoriteDAO(context: Context) : IProductFavoriteDAO {

    private val escrita = DatabaseHelper(context).writableDatabase
    private val leitura = DatabaseHelper(context).readableDatabase

    override fun save(productFavorite: ProductFavorite): Boolean {

        val conteudos = ContentValues()
        conteudos.put("${DatabaseHelper.COLUNA_ID_PRODUTO_FAVORITO}", productFavorite.id)
        conteudos.put("${DatabaseHelper.COLUNA_IMAGE_PRODUTO_FAVORITO}", productFavorite.image)
        conteudos.put("${DatabaseHelper.COLUNA_TITLE_PRODUTO_FAVORITO}", productFavorite.title)
        conteudos.put("${DatabaseHelper.COLUNA_PRICE_PRODUTO_FAVORITO}", productFavorite.price)

        try {
            escrita.insert(
                DatabaseHelper.NOME_TABELA_PRODUTO_FAVORITO,
                null,
                conteudos
            )
            Log.i("info_db", "Sucesso ao salvar produto favorito")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao salvar produto favorito")
            return false
        }

        return true

    }

    override fun delete(idProductFavorite: Int): Boolean {

        val args = arrayOf( idProductFavorite.toString() )
        try {
            escrita.delete(
                DatabaseHelper.NOME_TABELA_PRODUTO_FAVORITO,
                "${DatabaseHelper.COLUNA_ID_PRODUTO_FAVORITO} = ?",
                args
            )
            Log.i("info_db", "Sucesso ao remover tarefa")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao remover tarefa")
            return false
        }

        return true

    }

    override fun listAll(): List<ProductFavorite> {

        val listaProdutosFavoritos = mutableListOf<ProductFavorite>()

        val sql = "SELECT ${DatabaseHelper.COLUNA_ID_PRODUTO_FAVORITO}, " +
                "${DatabaseHelper.COLUNA_IMAGE_PRODUTO_FAVORITO}, " +
                "${DatabaseHelper.COLUNA_TITLE_PRODUTO_FAVORITO}, " +
                "${DatabaseHelper.COLUNA_PRICE_PRODUTO_FAVORITO} " +
                "FROM ${DatabaseHelper.NOME_TABELA_PRODUTO_FAVORITO}"

        val cursor = leitura.rawQuery(sql, null)

        val indiceId = cursor.getColumnIndex(DatabaseHelper.COLUNA_ID_PRODUTO_FAVORITO)
        val indiceImage = cursor.getColumnIndex(DatabaseHelper.COLUNA_IMAGE_PRODUTO_FAVORITO)
        val indiceTitle = cursor.getColumnIndex(DatabaseHelper.COLUNA_TITLE_PRODUTO_FAVORITO)
        val indicePrice = cursor.getColumnIndex(DatabaseHelper.COLUNA_PRICE_PRODUTO_FAVORITO)

        while ( cursor.moveToNext() ){

            val idProduto = cursor.getInt( indiceId )
            val image = cursor.getString( indiceImage )
            val title = cursor.getString( indiceTitle )
            val price = cursor.getDouble( indicePrice )

            listaProdutosFavoritos.add(
                ProductFavorite(idProduto, image, title, price)
            )

        }

        return listaProdutosFavoritos

    }

    override fun findById(idProductFavorite: Int): Boolean {
        val sql = "SELECT ${DatabaseHelper.COLUNA_ID_PRODUTO_FAVORITO} " +
                "FROM ${DatabaseHelper.NOME_TABELA_PRODUTO_FAVORITO} " +
                "WHERE ${DatabaseHelper.COLUNA_ID_PRODUTO_FAVORITO} = ?"

        val cursor = leitura.rawQuery(sql, arrayOf(idProductFavorite.toString()))

        return cursor.moveToFirst()
    }
}