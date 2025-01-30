

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.romulodiego.storeapp.databinding.ItemProductFavoriteBinding
import com.br.romulodiego.storeapp.model.ProductFavorite

class ProdutoFavoritoAdapter(
    val onClickExcluir: (Int) -> Unit
) : RecyclerView.Adapter<ProdutoFavoritoAdapter.ProdutosFavoritosViewHolder>() {

    private var produtosFavoritosList: List<ProductFavorite> = emptyList()

    fun adicionarLista( lista: List<ProductFavorite> ){
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