//package com.example.projekpbpdr
//
//import android.annotation.SuppressLint
//import android.content.Intent
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.ProgressBar
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.projekpbpdr.data.ImageModel
//import com.google.firebase.Firebase
//import com.google.firebase.storage.storage
//
//class InsideHomeOnImage : AppCompatActivity() {
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var imageAdapter: ImageAdapter
//    private lateinit var progressBar: ProgressBar
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_inside_home_on_image)
//
//        val imageUrl = intent.getStringExtra("IMAGE_URL")
//
//        // Inisialisasi RecyclerView dan Adapter
//        recyclerView = findViewById(R.id.recyclerViewInHomeOnImg)
//        progressBar = findViewById(R.id.progressBarInside)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        imageAdapter = ImageAdapter(imageUrl)
//        recyclerView.adapter = imageAdapter
//    }
//}
//
//
//class ImageAdapter(private val imageUrl: String?) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_viewonimg, parent, false)
//        return ImageViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
//        imageUrl?.let {
//            Glide.with(holder.itemView.context)
//                .load(it)
//                .into(holder.imageView)
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return 1
//    }
//
//    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val imageView: ImageView = itemView.findViewById(R.id.imageView8)
//    }
//}
