package com.nashwa.api_product.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.constraintlayout.motion.widget.MotionScene.Transition.TransitionOnClick
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nashwa.api_product.R
import com.nashwa.api_product.model.ModelProduk

class ProdukAdapter(
    private val onClick: (ModelProduk) -> Unit
) : ListAdapter<ModelProduk, ProdukAdapter.ProdukViewHolder>(ProdukCallBack) {

    class ProdukViewHolder(itemView: View, val onClick: (ModelProduk) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val imgProduk: ImageView = itemView.findViewById(R.id.imgProduk)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val brand: TextView = itemView.findViewById(R.id.brand)
        private val price: TextView = itemView.findViewById(R.id.price)

        //cel produk yg saat ini

        private var currentProduct: ModelProduk? = null

        init {
            itemView.setOnClickListener() {
                currentProduct?.let {
                    onClick(it)
                }
            }
        }

        fun bind(produk: ModelProduk){
            currentProduct = produk

            //set ke widget
            title.text = produk.title
            brand.text = produk.brand
            price.text = produk.price.toString()

            //untuk menampilkan gambar pada widget (fetching gambar dengan gldie)
            Glide.with(itemView).load(produk.thumbnail).centerCrop()
                .into(imgProduk)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdukViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.row_product,
            parent, false
        )
        return ProdukViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ProdukViewHolder, position: Int) {
        val produk = getItem(position)
        holder.bind(produk)
    }


}

//
object ProdukCallBack : DiffUtil.ItemCallback<ModelProduk>() {
    override fun areItemsTheSame(oldItem: ModelProduk, newItem: ModelProduk): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ModelProduk, newItem: ModelProduk): Boolean {
        return oldItem.id == newItem.id
    }
}