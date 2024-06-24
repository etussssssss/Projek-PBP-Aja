package com.example.projekpbpdr

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var btnstart:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        var btnStartAct = findViewById<Button>(R.id.btnMainStart)
        btnStartAct.setOnClickListener {
                intent = Intent(this, Login::class.java)
                startActivity(intent)
                super.onStart()
        }



//        if (FirebaseAuth.getInstance().currentUser != null){
//            val intent = Intent(this@MainActivity, Inside::class.java)
//            startActivity(intent)
//        } else {
//            FirebaseAuth.getInstance().signOut()
//            btnStartAct.setOnClickListener {
//                intent = Intent(this, Login::class.java)
//                startActivity(intent)
//                super.onStart()
//            }
//        }


    }


}