package com.example.projekpbpdr

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class Detail : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.detail, container, false)

        val imageUrl = arguments?.getString("IMAGE_URL")
        val detailImageView: ImageView = view.findViewById(R.id.imageView8)
        val comment:ImageButton = view.findViewById(R.id.imageButtonCmnt3)

        // Memuat gambar menggunakan Glide
        Glide.with(requireContext())
            .load(imageUrl)
            .into(detailImageView)


        val nameUser: TextView = view.findViewById(R.id.nameUser)
        val imageUser:ImageView = view.findViewById(R.id.imageUser)

        val auth = FirebaseAuth.getInstance().currentUser
        val db = FirebaseFirestore.getInstance()

        if (auth != null) {
            // UID pengguna yang sedang masuk
            val uid = auth.uid
            // Mengambil data pengguna dari Firestore berdasarkan UID
            db.collection("user")
                .document(uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val displayName = document.getString("nama")
                        val imageDef = document.getString("image")
                        if (displayName != null) {
                            // Menampilkan nama pengguna di TextView
                            nameUser.text = displayName
//                            Glide.get(requireContext())
//                                .load(imageDef)
//                                .into(imageUser)
                        } else {
                            Toast.makeText(context, "Nama pengguna tidak tersedia", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Dokumen tidak ditemukan", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener { e ->
                    Toast.makeText(context, "Gagal mengambil data pengguna", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(context, "Pengguna tidak terautentikasi", Toast.LENGTH_SHORT).show()
        }

        comment.setOnClickListener {
            Toast.makeText(context, "Klik Comment", Toast.LENGTH_SHORT).show()
            val intent = view.Intent(this, Register::class.java)
            startActivity(intent)
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Load image detail or other necessary actions
    }
}
