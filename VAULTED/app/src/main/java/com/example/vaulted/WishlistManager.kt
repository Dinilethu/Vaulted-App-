package com.example.vaulted

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WishlistManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("wishlist_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val key = "wishlist_items"

    // Get all wishlist items
    fun getWishlist(): MutableList<Product> {
        val json = prefs.getString(key, null)
        val type = object : TypeToken<MutableList<Product>>() {}.type
        return gson.fromJson(json, type) ?: mutableListOf()
    }

    // Add a product to wishlist
    fun addItem(product: Product) {
        val list = getWishlist()
        if (list.none { it.id == product.id }) {
            list.add(product)
            saveList(list)
        }
    }

    // Remove a product by ID
    fun removeItem(productId: String) {
        val list = getWishlist()
        list.removeAll { it.id == productId }
        saveList(list)
    }

    // Check if a product is wishlisted
    fun isWishlisted(productId: String): Boolean {
        return getWishlist().any { it.id == productId }
    }

    // Save list to SharedPreferences
    private fun saveList(list: MutableList<Product>) {
        prefs.edit().putString(key, gson.toJson(list)).apply()
    }

    // Clear all wishlist items
    fun clearAll() {
        prefs.edit().clear().apply()
    }
}