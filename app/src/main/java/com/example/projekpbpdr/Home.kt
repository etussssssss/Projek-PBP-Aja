package com.example.projekpbpdr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projekpbpdr.data.ImageModel
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import ImageAdapter
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var imageAdapter: ImageAdapter
    private val imageList = mutableListOf<ImageModel>()
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.home, container, false)

        // Initialize views from the inflated layout
        recyclerView = view.findViewById(R.id.recyclerViewInMain)
        recyclerView.layoutManager = LinearLayoutManager(requireContext()) // Use requireContext() instead of this
        imageAdapter = ImageAdapter(imageList)
        recyclerView.adapter = imageAdapter

        // Fetch images from Firebase Storage
        fetchImagesFromFirebase()

        return view
    }

    private fun fetchImagesFromFirebase() {
        val storage = Firebase.storage
        val imagesRef = storage.reference.child("image")

        // Clear the existing list before fetching new images
        imageList.clear()

        // Fetch images from Firebase Storage
        imagesRef.listAll().addOnSuccessListener { result ->
            for (fileRef in result.items) {
                fileRef.downloadUrl.addOnSuccessListener { uri ->
                    val imageModel = ImageModel(uri.toString())
                    imageList.add(imageModel)
                    imageAdapter.notifyDataSetChanged()
                }.addOnFailureListener { exception ->
                    Toast.makeText(requireContext(), "Failed to fetch image: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(requireContext(), "Failed to list images: ${exception.message}", Toast.LENGTH_SHORT).show()
        }
    }
}

