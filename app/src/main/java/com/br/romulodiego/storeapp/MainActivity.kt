package com.br.romulodiego.storeapp


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.br.romulodiego.storeapp.view.CategoryActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonViewCategories: Button = findViewById(R.id.button_view_categories)
        buttonViewCategories.setOnClickListener {
            startActivity(Intent(this, CategoryActivity::class.java))
        }
    }
}