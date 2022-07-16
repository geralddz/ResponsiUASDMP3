package com.app.responsimp3.View.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import com.app.responsimp3.R
import com.app.responsimp3.View.CustomDialog
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var btnLogin : Button
    private lateinit var etEmailIn : EditText
    private lateinit var etPassIn : EditText
    private lateinit var tvForgotPass : TextView
    private lateinit var tvregister : TextView
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        btnLogin = findViewById(R.id.btnLogIn)
        etEmailIn = findViewById(R.id.etEmailLogIn)
        etPassIn = findViewById(R.id.etPasswordLogIn)
        tvregister = findViewById(R.id.tvRegister)
        tvForgotPass = findViewById(R.id.tvForgotPass)

        tvregister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        tvForgotPass.setOnClickListener {
            startActivity(Intent(this, RecoveryActivity::class.java))
        }

        btnLogin.setOnClickListener {
            val email = etEmailIn.text.toString().trim()
            val pass = etPassIn.text.toString().trim()
            CustomDialog.showLoading(this)
            if (CheckValidation(email, pass)){
                LoginToServer(email,pass)
            }
        }
    }

    private fun CheckValidation(email: String, pass: String): Boolean {
        if(email.isEmpty()) {
            etEmailIn.error = "Masukkan Email Anda"
            etEmailIn.requestFocus()
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmailIn.error = "Masukkan Email dengan Benar"
            etEmailIn.requestFocus()
        } else if (pass.isEmpty()) {
            etPassIn.error = "Masukkan Password Ada"
            etPassIn.requestFocus()
        } else{
            return true
        }
        CustomDialog.hideLoading()
        return false
    }

    private fun LoginToServer(email: String, pass: String) {
        val credential = EmailAuthProvider.getCredential(email,pass)
        firebaseAuth(credential)
    }

    private fun firebaseAuth(credential: AuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    CustomDialog.hideLoading()
                    startActivity(Intent(this,HomeActivity::class.java))
                    Toast.makeText(this, "Sign In Berhasil", Toast.LENGTH_SHORT).show()
                    finishAffinity()
                }else{
                    Toast.makeText(this, "Sign In Gagal", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                CustomDialog.hideLoading()
                Toast.makeText(this, "Pastikan Email dan Password Sudah Benar", Toast.LENGTH_SHORT).show()
            }
    }

}