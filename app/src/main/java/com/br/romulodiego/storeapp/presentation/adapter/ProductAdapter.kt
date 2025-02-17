// ProductAdapter.kt
package com.br.romulodiego.storeapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.romulodiego.storeapp.R
import com.br.romulodiego.storeapp.databinding.ItemProductBinding
import com.br.romulodiego.storeapp.data.models.Product
import com.squareup.picasso.Picasso
import kotlin.random.Random

class ProductAdapter(
    private val products: List<Product>,
    private val onClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position], onClick)
    }

    override fun getItemCount(): Int = products.size

    class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product, onClick: (Product) -> Unit) {
            val imageUrl = product.image
            Picasso.get()
                .load(imageUrl)
                .into(binding.productImage)
            binding.productTitle.text = product.title
            binding.productPrice.text = product.price.toString()
            binding.productCategory.text = product.category
            binding.productDescription.text = product.description
            binding.textFavoriteItem.visibility = if (product.isInWishlist) View.VISIBLE else View.GONE
            binding.wishlistIndicator.visibility = if (product.isInWishlist) View.VISIBLE else View.GONE
            binding.root.setOnClickListener { onClick(product) }
        }
    }
}