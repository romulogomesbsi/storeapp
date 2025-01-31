

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.romulodiego.storeapp.databinding.ItemProductFavoriteBinding
import com.br.romulodiego.storeapp.data.models.ProductFavorite
import com.squareup.picasso.Picasso

class ProductFavoriteAdapter(
    val onClickExcluir: (Int) -> Unit
) : RecyclerView.Adapter<ProductFavoriteAdapter.ProdutosFavoritosViewHolder>() {

    private var produtosFavoritosList: List<ProductFavorite> = emptyList()

    fun addToList(lista: List<ProductFavorite> ){
        this.produtosFavoritosList = lista
        notifyDataSetChanged()
    }

    inner class ProdutosFavoritosViewHolder(itemBinding: ItemProductFavoriteBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

        private val binding: ItemProductFavoriteBinding


        init {
            binding = itemBinding
        }

        fun bind(product: ProductFavorite){
            binding.txtTitulo.text = product.title
            binding.txtPreco.text = "R$ " + product.price.toString()
            val imageUrl = product.image
            Picasso.get()
                .load(imageUrl)
                .into(binding.productImage)
            binding.btnExcluir.setOnClickListener {
                onClickExcluir( product.id )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutosFavoritosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemProdutoFavoritoBinding = ItemProductFavoriteBinding.inflate(
            layoutInflater, parent, false
        )
        return ProdutosFavoritosViewHolder(itemProdutoFavoritoBinding)
    }

    override fun onBindViewHolder(holder: ProdutosFavoritosViewHolder, position: Int) {
        val produto = produtosFavoritosList[position]
        holder.bind( produto )
    }

    override fun getItemCount(): Int {
        return produtosFavoritosList.size
    }

}