package com.example.projekpbpdr

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore


class Register : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth
//        val rootLayout: ConstraintLayout = findViewById(R.id.main)
//        val editText1: EditText = findViewById(R.id.editTextRegPassword2)



        //resize(rootLayout, editText1)
        processRegist()

    }

        override fun onBackPressed() {
            super.onResume()
            super.onBackPressed()
        }

        fun goToSignInPage(view: View) {

            // Code untuk berpindah ke halaman sign in
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            super.onBackPressed()
        }

        fun processRegist(){
            val nama:EditText = findViewById(R.id.editTextRegEmail1)
            val email: EditText = findViewById(R.id.editTextRegEmail2)
            val password: EditText = findViewById(R.id.editTextRegPassword1)
            val passwordSame: EditText = findViewById(R.id.editTextRegPassword2)
            val buttonRegister: Button = findViewById(R.id.btnReg1)

            buttonRegister.setOnClickListener {
                val nama = nama.text.toString()
                val email = email.text.toString()
                val password = password.text.toString()
                val passwordSame = passwordSame.text.toString()

                if (nama.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && password.equals(passwordSame) ) {
                    registerUser(nama,email, password)
                } else {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }
            }
        }

        fun registerUser(nama: String,email: String, password: String) {
//            val db = Firebase.firestore

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->

                val user = FirebaseAuth.getInstance().currentUser
                val db = FirebaseFirestore.getInstance()
                if (task.isSuccessful) {
                    if (user != null) {
                        val uid = user.uid

                        val user = hashMapOf(
                            "email" to email,
                            "nama" to nama,
                            "password" to password
                            )
                        /// Menggunakan UID sebagai ID dokumen
                        db.collection("user").document(uid).set(user).addOnSuccessListener {
                            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT)
                                .show()
                        }.addOnFailureListener { e ->
                                Toast.makeText(
                                    this,
                                    "Failed to save user data: ${e.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    }
                }else {
                    // Registrasi gagal
                    Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }


//                if (task.isSuccessful) {
//                        // Registrasi berhasil, simpan data pengguna ke Firestore
//                        val userId = auth.currentUser?.uid
//                        val user = hashMapOf(
//                            "email" to email,
//                            "nama" to nama,
//
//                        )
//
//                        db.collection("user").add(user).addOnSuccessListener {
//                                    Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
//                                    // Pindah ke halaman login atau halaman utama
//                                    val intent = Intent(this, Login::class.java)
//                                    startActivity(intent)
//                                    finish()
//                                }.addOnFailureListener { e ->
//                                    Toast.makeText(this, "Failed to save user data: ${e.message}", Toast.LENGTH_SHORT).show()
//                                }
//                    } else {
//                        // Registrasi gagal
//                        Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
//                    }
                }
        }

    fun resize(rootLayout: ConstraintLayout, editText: EditText){
        editText.setOnFocusChangeListener { event, hasFocus ->
            if (hasFocus) {
                rootLayout.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        val rect = android.graphics.Rect()
                        rootLayout.getWindowVisibleDisplayFrame(rect)
                        val screenHeight = rootLayout.height

                        val keypadHeight = screenHeight - rect.bottom

                        if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                            // Scroll to a position where the EditText is centered in the visible area
                            val scrollY = editText.top - (screenHeight / 2) + (editText.height / 2)
                            rootLayout.scrollTo(0, scrollY)
                        } else {
                            rootLayout.scrollTo(0, 0)
                        }
                        rootLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    }
                })
            }else if (!hasFocus) {
                rootLayout.scrollTo(0, 0)
            }
        }
    }



    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
    }
}