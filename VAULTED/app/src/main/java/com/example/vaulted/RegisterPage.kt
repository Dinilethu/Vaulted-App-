package com.example.vaulted

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vaulted.databinding.RegisterActivityBinding

class RegisterPage : AppCompatActivity() {

    private lateinit var binding: RegisterActivityBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegisterActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        // --- Handle register button ---
        binding.registerBtn.setOnClickListener {
            val username = binding.usernameInput.text.toString().trim()
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()
            val confirmPassword = binding.confirmpasswordInput.text.toString().trim()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ✅ Save registration info locally
            sessionManager.saveUserRegistration(email, username, password)
            Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()

            // ✅ Move to LoginPage
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
            finish()
        }

        // --- Go to login page if user already has an account ---
        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
            finish()
        }
    }
}
