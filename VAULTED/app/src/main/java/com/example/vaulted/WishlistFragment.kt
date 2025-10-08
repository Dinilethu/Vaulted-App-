package com.example.vaulted

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WishlistFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WishlistAdapter
    private lateinit var wishlistManager: WishlistManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.wishlist_fragment, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewWishlist)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        wishlistManager = WishlistManager(requireContext())
        loadWishlist()

        return view
    }

    private fun loadWishlist() {
        val wishlist = wishlistManager.getWishlist()

        adapter = WishlistAdapter(
            wishlist,
            onItemClick = { product ->
                val fragment = ProductViewFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable("product", product)
                    }
                }
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, fragment)
                    .addToBackStack(null)
                    .commit()
            },
            onRemoveClick = { product ->
                wishlistManager.removeItem(product.id)
                Toast.makeText(context, "${product.name} removed from wishlist", Toast.LENGTH_SHORT).show()
                loadWishlist() // Refresh the list
            }
        )

        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        loadWishlist()
    }
}