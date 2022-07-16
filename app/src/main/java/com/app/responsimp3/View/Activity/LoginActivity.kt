package com.app.responsimp3.View.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.app.responsimp3.R

class LoginActivity : AppCompatActivity() {
    private lateinit var btnLogin : Button
    private lateinit var etEmailIn : EditText
    private lateinit var etPassIn : EditText
    private lateinit var tvForgotPass : TextView
    private lateinit var tvregister : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin = findViewById(R.id.btnLogIn)
        etEmailIn = findViewById(R.id.etEmailLogIn)
        etPassIn = findViewById(R.id.etPasswordLogIn)
        tvregister = findViewById(R.id.tvRegister)
        tvForgotPass = findViewById(R.id.tvForgotPass)

        tvregister.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        tvForgotPass.setOnClickListener {
            startActivity(Intent(this, RecoveryActivity::class.java))
        }
    }
}