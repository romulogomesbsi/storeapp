package com.br.romulodiego.storeapp.view


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.br.romulodiego.storeapp.R
import com.br.romulodiego.storeapp.view.CategoryActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this, CategoryActivity::class.java))
    }
}