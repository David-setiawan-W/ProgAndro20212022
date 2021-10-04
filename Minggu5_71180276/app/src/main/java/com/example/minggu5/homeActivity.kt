package com.example.minggu5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class homeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val usernametxt = intent.getStringExtra("username")
        val getUsernameLogin = findViewById<TextView>(R.id.iniHomeTxv)

        getUsernameLogin.text = "Selamat datang ${usernametxt}"

    }
}