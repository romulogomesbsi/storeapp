package com.jamiltondamasceno.applistatarefas.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context, NOME_BANCO_DADOS, null, VERSAO
) {


    companion object {
        const val NOME_BANCO_DADOS = "ProdutosFavoritos.db"
        const val VERSAO = 1
        const val NOME_TABELA_PRODUTO_FAVORITO = "produtos_favoritos"
        const val COLUNA_ID_PRODUTO_FAVORITO = "id"
        const val COLUNA_TITLE_PRODUTO_FAVORITO = "title"
        const val COLUNA_PRICE_PRODUTO_FAVORITO = "price"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val sql = "CREATE TABLE IF NOT EXISTS $NOME_TABELA_PRODUTO_FAVORITO(" +
                "$COLUNA_ID_PRODUTO_FAVORITO INTEGER not NULL PRIMARY KEY AUTOINCREMENT," +
                "$COLUNA_TITLE_PRODUTO_FAVORITO VARCHAR(255)," +
                "$COLUNA_PRICE_PRODUTO_FAVORITO DOUBLE NOT NULL" +
                ");"

        try {
            db?.execSQL( sql )
            Log.i("info_db", "Sucesso ao criar a tabela")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao criar a tabela")
        }

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}