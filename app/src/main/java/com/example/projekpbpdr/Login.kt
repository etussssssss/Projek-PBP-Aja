package com.example.projekpbpdr

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        auth = Firebase.auth

        val btnlogin: Button = findViewById(R.id.btnLog1)
        btnlogin.setOnClickListener {
            processLogin()
        }

    }

    override fun onBackPressed() {
        super.onResume()
        super.onBackPressed()
    }

    fun goToSignUpPage(view: View) {
        // Code untuk berpindah ke halaman sign in
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
        super.onBackPressed()
    }

//    override fun onStart() {
//        super.onStart()
//        if (FirebaseAuth.getInstance().currentUser != null){
//            val intent = Intent(this@Login, Inside::class.java)
//            startActivity(intent)
//        } else {
//            FirebaseAuth.getInstance().signOut()
//        }
//    }

    fun processLogin(){
        val email = findViewById<EditText>(R.id.editTextLogEmail1)
        val pass = findViewById<EditText>(R.id.editTextlogPassword2)
        if (email.text.isEmpty() || pass.text.isEmpty()) {
            Toast.makeText(this, "Please fill all the credential", Toast.LENGTH_SHORT).show()
            return
        }

        val emailForFirebase = email.text.toString()
        val passForFirebase = pass.text.toString()

        auth.signInWithEmailAndPassword(emailForFirebase, passForFirebase).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@Login, Inside::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Login failed.", Toast.LENGTH_SHORT,).show()
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Error Occurred ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
    }


}