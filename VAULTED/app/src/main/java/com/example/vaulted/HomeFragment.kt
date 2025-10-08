package com.example.vaulted

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.vaulted.databinding.HomefragmentBinding

class HomeFragment : Fragment() {

    private var _binding: HomefragmentBinding? = null
    private val binding get() = _binding!!

    private val productNames = arrayOf(
        "Yeezy Slides", "Nike Air Max 95", "Air Max Plus VII", "Air Max 95 Ash & Solar Red",
        "Air Max Plus VII (Alt)", "Nike Air 95 Blue", "Product 7", "Product 8",
        "Product 9", "Product 10", "Product 11", "Product 12"
    )

    private val productDescriptions = arrayOf(
        "Minimalist slide sandal with soft foam comfort.",
        "Classic Air Max cushioning and retro style.",
        "Bold silhouette with modern comfort features.",
        "Iconic Air Max look with upgraded materials.",
        "Lightweight runner inspired casual shoe.",
        "Retro runner with fresh colourway.",
        "Placeholder short description 7.",
        "Placeholder short description 8.",
        "Placeholder short description 9.",
        "Placeholder short description 10.",
        "Placeholder short description 11.",
        "Placeholder short description 12."
    )

    private val productImages = arrayOf(
        R.drawable.yeezyslides, R.drawable.nikeair95blu, R.drawable.airmaxplusviivarsitymaize,
        R.drawable.airmax95mediumashandsolarred, R.drawable.airmaxplusviivarsitymaize,
        R.drawable.nikeair95blu, R.drawable.yeezyslides, R.drawable.nikeair95blu,
        R.drawable.airmaxplusviivarsitymaize, R.drawable.airmax95mediumashandsolarred,
        R.drawable.nikeair95blu, R.drawable.yeezyslides
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomefragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        // Attach click listeners to all cards
        val cardViews = listOf(
            binding.card1, binding.card2, binding.card3, binding.card4,
            binding.card5, binding.card6, binding.card7, binding.card8,
            binding.card9, binding.card10, binding.card11, binding.card12
        )

        cardViews.forEachIndexed { idx, cardView ->
            cardView.isClickable = true
            cardView.isFocusable = true
            cardView.setOnClickListener {
                Log.d("HomeFragment", "Card $idx clicked")

                val product = Product(
                    id = idx.toString(),
                    name = productNames.getOrNull(idx) ?: "Product",
                    brand = "Vaulted",
                    description = productDescriptions.getOrNull(idx) ?: "",
                    imageRes = productImages.getOrNull(idx) ?: R.drawable.placeholder_image,
                    storeUrl = "https://example.com/product/$idx"
                )

                val bundle = Bundle().apply {
                    putParcelable("product", product)
                }

                val productFragment = ProductViewFragment().apply {
                    arguments = bundle
                }

                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, productFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}