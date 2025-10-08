package com.example.vaulted

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.vaulted.databinding.SettingsPageBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: SettingsPageBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SettingsPageBinding.inflate(inflater, container, false)
        sessionManager = SessionManager(requireContext())

        // --- Retrieve user data from session ---
        val user = sessionManager.getUserDetails()
        binding.tvUserName.text = user["username"] ?: "User"
        binding.tvUserEmail.text = user["email"] ?: "user@example.com"

        // --- Handle logout ---
        binding.btnLogout.setOnClickListener {
            sessionManager.logoutUser() // clears the session
            val intent = Intent(requireContext(), LoginPage::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        // --- Handle edit profile (optional placeholder) ---
        binding.btnEditProfile.setOnClickListener {
            // TODO: Add edit functionality later
        }

        return binding.root
    }
}
