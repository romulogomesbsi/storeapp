//// WishlistActivity.kt
//package com.br.romulodiego.storeapp.view
//
//import android.os.Bundle
//import androidx.activity.viewModels
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.Observer
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.br.romulodiego.storeapp.adapter.WishlistAdapter
//import com.br.romulodiego.storeapp.databinding.ActivityWishlistBinding
//import com.br.romulodiego.storeapp.viewmodel.WishlistViewModel
//
//class WishlistActivity : AppCompatActivity() {
//    private val wishlistViewModel: WishlistViewModel by viewModels()
//    private lateinit var binding: ActivityWishlistBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityWishlistBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
//        val wishlistAdapter = WishlistAdapter()
//        binding.recyclerViewWishlist.layoutManager = LinearLayoutManager(this)
//        binding.recyclerViewWishlist.adapter = wishlistAdapter
//
//        wishlistViewModel.wishlist.observe(this, Observer { wishlistItems ->
//            wishlistAdapter.submitList(wishlistItems)
//        })
//    }
//}