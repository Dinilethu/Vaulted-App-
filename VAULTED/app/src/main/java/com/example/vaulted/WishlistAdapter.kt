package com.example.vaulted

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.vaulted.databinding.ItemWishlistBinding

class WishlistAdapter(
    private val items: List<Product>,
    private val onItemClick: (Product) -> Unit,
    private val onRemoveClick: (Product) -> Unit
) : RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder>() {

    inner class WishlistViewHolder(val binding: ItemWishlistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.itemName.text = product.name
            binding.itemPrice.text = product.releaseDate
            binding.itemBrand.text = product.brand
            binding.itemImage.load(product.imageRes) {
                crossfade(true)
                placeholder(R.drawable.placeholder_image)
                error(R.drawable.image_error)
            }

            binding.root.setOnClickListener { onItemClick(product) }
            binding.removeButton.setOnClickListener { onRemoveClick(product) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {
        val binding = ItemWishlistBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return WishlistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}