package com.example.vaulted

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val imageRes: Int = 0,
    val storeUrl: String = "",
    val brand: String = "",
    val releaseDate: String = ""
) : Parcelable