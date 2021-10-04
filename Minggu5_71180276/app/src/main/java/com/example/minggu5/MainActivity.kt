package com.example.minggu5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username = findViewById<EditText>(R.id.usernameedt)
        val password = findViewById<EditText>(R.id.passwordedt)

        val btnLogin = findViewById<Button>(R.id.loginbtn)
        val btnRegister = findViewById<Button>(R.id.registerbtn)

        fun username():Boolean{
            if(username.text.isEmpty()){
                username.error = "Username tidak boleh kosong"
                return false
            }
            return true
        }

        fun login():Boolean{
            if (password.text.contentEquals("1234")){
                return true
            }
            if (password.text.isEmpty()){
                password.error = "Password tidak boleh kosong"
                return false
            }
            password.error = "Password Salah"
            return false
        }


        btnLogin.setOnClickListener {
            if (username()&&login()){
            val intent = Intent(this,homeActivity :: class.java)
            intent.putExtra("username",username.text.toString())
            startActivity(intent)
            }
        }

        btnRegister.setOnClickListener {
            val intent = Intent(this,register :: class.java)
            startActivity(intent)
        }
    }
}