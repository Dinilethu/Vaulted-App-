package com.example.vaulted

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vaulted.databinding.LoginActivityBinding

class LoginPage : AppCompatActivity() {

    private lateinit var binding: LoginActivityBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        // --- Auto-skip if user already logged in ---
        if (sessionManager.isLoggedIn()) {
            goToMainActivity()
            return
        }

        // --- Handle login button ---
        binding.loginBtn.setOnClickListener {
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()

            val savedEmail = sessionManager.getUserEmail()
            val savedPassword = sessionManager.getUserPassword()
            val savedUsername = sessionManager.getUsername()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (email == savedEmail && password == savedPassword) {
                // ✅ Create user session
                sessionManager.saveUserSession(email, savedUsername ?: "User")
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                goToMainActivity()
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }

        // --- Handle navigation to register ---
        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterPage::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
