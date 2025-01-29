// ProductAdapter.kt
package com.br.romulodiego.storeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.romulodiego.storeapp.R
import com.br.romulodiego.storeapp.databinding.ItemProductBinding
import com.br.romulodiego.storeapp.model.Product

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
            binding.productImage.setImageResource(R.drawable.ic_launcher_background) // Placeholder image
            binding.productTitle.text = product.title
            binding.productPrice.text = product.price.toString()
            binding.productCategory.text = product.category
            binding.productDescription.text = product.description
            binding.root.setOnClickListener { onClick(product) }
        }
    }
}