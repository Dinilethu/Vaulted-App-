package com.example.vaulted

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.vaulted.databinding.HomefragmentBinding

class Homepage : Fragment() {

    private var _binding: HomefragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomefragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        sessionManager = SessionManager(requireContext())

        // Example: show a quick welcome message using stored username

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
