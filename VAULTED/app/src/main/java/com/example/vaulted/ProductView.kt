package com.example.vaulted

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import coil.load

class ProductViewFragment : Fragment() {

    private lateinit var productImage: ImageView
    private lateinit var productName: TextView
    private lateinit var productDescription: TextView
    private lateinit var productUrl: TextView
    private lateinit var wishlistButton: ImageView
    private lateinit var wishlistManager: WishlistManager
    private var currentProduct: Product? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.productview_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Toolbar setup
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        // Initialize views
        productImage = view.findViewById(R.id.productImage)
        productName = view.findViewById(R.id.productName)
        productDescription = view.findViewById(R.id.productDescription)
        productUrl = view.findViewById(R.id.itemURL)
        wishlistButton = view.findViewById(R.id.bookmarkButton)
        wishlistManager = WishlistManager(requireContext())

        // Get product from arguments
        currentProduct = arguments?.getParcelable("product")

        currentProduct?.let { product ->
            // Display product info
            productName.text = "${product.brand} ${product.name}"
            productDescription.text = product.description
            productImage.load(product.imageRes) {
                crossfade(true)
                placeholder(R.drawable.placeholder_image)
                error(R.drawable.image_error)
            }

            // Set store URL and make it clickable
            productUrl.text = product.storeUrl
            productUrl.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(product.storeUrl))
                startActivity(intent)
            }

            // Set wishlist icon
            updateWishlistButton(wishlistManager.isWishlisted(product.id))

            // Toggle wishlist
            wishlistButton.setOnClickListener {
                val isSaved = wishlistManager.isWishlisted(product.id)
                if (isSaved) {
                    wishlistManager.removeItem(product.id)
                    updateWishlistButton(false)
                    Toast.makeText(context, "Removed from wishlist", Toast.LENGTH_SHORT).show()
                } else {
                    wishlistManager.addItem(product)
                    updateWishlistButton(true)
                    Toast.makeText(context, "Added to wishlist", Toast.LENGTH_SHORT).show()
                }
            }
        } ?: run {
            Toast.makeText(context, "Product data missing", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateWishlistButton(isInWishlist: Boolean) {
        wishlistButton.setImageResource(
            if (isInWishlist) R.drawable.outline_bookmark_check_24
            else R.drawable.outline_bookmark_heart_24
        )
    }
}