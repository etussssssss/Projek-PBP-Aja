package com.example.projekpbpdr

import ImageAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projekpbpdr.data.ImageModel
import com.example.projekpbpdr.databinding.ActivityInsideBinding
import com.google.firebase.Firebase
import com.google.firebase.storage.storage



class Inside : AppCompatActivity() {
    private lateinit var binding: ActivityInsideBinding
    private var activeFragmentId: Int = R.id.home // Default active fragment
    private var i:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomnav.setOnItemSelectedListener { menuItem ->
            if (menuItem.itemId != activeFragmentId) {
                when (menuItem.itemId) {
                    R.id.home -> {
                        replaceFragment(Home())
                        true
                    }
                    R.id.search -> {
                        replaceFragment(Search())
                        true
                    }
                    R.id.profil -> {
                        replaceFragment(Profile())
                        true
                    }
                    // Add more cases for other menu items if needed
                    else -> false
                }.also {
                    activeFragmentId = menuItem.itemId
                }
            } else {
                Toast.makeText(this, "Menu ini sudah dipilih", Toast.LENGTH_SHORT).show()
                true // Return true to indicate event has been handled
            }
        }

        // Set the initial selected fragment
        replaceFragment(Home()) // Example: Start with Home fragment
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }


    override fun onBackPressed() {
        i++
        if (i == 2){
            super.onDestroy()
            super.onBackPressed()
        }else {
            Toast.makeText(this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show()
        }
    }
}