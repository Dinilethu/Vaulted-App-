package com.example.vaulted

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.vaulted.databinding.HomepageActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: HomepageActivityBinding
    private lateinit var sessionManager: SessionManager

    // Create fragment instances once for persistence
    private val homeFragment = Homepage()
    private val wishlistFragment = WishlistFragment()
    private val settingsFragment = SettingsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = HomepageActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        // 🔒 Redirect to login if not logged in
        if (!sessionManager.isLoggedIn()) {
            startActivity(Intent(this, LoginPage::class.java))
            finish()
            return
        }

        // Load default fragment (Home)
        setCurrentFragment(homeFragment)

        // Setup bottom navigation instant swaps
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> setCurrentFragment(homeFragment)
                R.id.nav_wishlist -> setCurrentFragment(wishlistFragment)
                R.id.nav_settings -> setCurrentFragment(settingsFragment)
            }
            true
        }
    }

    // Replace fragment without recreation (instant swap)
    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .replace(R.id.frame_layout, fragment)
            .commit()
    }
}
