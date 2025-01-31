

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.romulodiego.storeapp.R
import com.br.romulodiego.storeapp.databinding.ItemProductFavoriteBinding
import com.br.romulodiego.storeapp.data.model.ProductFavorite

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

        fun bind( produto: ProductFavorite){
            binding.txtTitulo.text = produto.title
            binding.txtPreco.text = "R$ " + produto.price.toString()
            // Seleção aleatória de imagem
            val images = listOf(R.drawable.image1, R.drawable.image2, R.drawable.image3)
            val randomImage = images.random()
            binding.productImage.setImageResource(randomImage)
            binding.btnExcluir.setOnClickListener {
                onClickExcluir( produto.id )
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