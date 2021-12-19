package com.example.finalprogandro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.finalprogandro.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var firebaseAuth: FirebaseAuth

    var db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.menubar_def))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val shwData = findViewById<TextView>(R.id.listData)


        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        btnAddData.setOnClickListener {
            startActivity(Intent(this, AddDataActivity::class.java))
            finish()
        }

        btnDElData.setOnClickListener {
            startActivity(Intent(this, DeleteActivity::class.java))
            finish()
        }

        btnUbahData.setOnClickListener {
            startActivity(Intent(this, UbahDataActivity::class.java))
            finish()
        }

        db.collection("penduduk").get()
            .addOnSuccessListener { docs ->
                var output = ""
                for (doc in docs){
                    output += "\n${doc["nik"]} | ${doc["nama"]} | ${doc["jenisKel"]}"
                }
                shwData.setText(output)
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menubar_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem)= when(item.itemId) {
        R.id.btnLogout ->{
            firebaseAuth.signOut()
            checkUser()
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
            true
        }
        else ->{
            super.onOptionsItemSelected(item)
        }
    }


    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
//        else{
//            val email = firebaseUser.email
//            binding.emailLogger.text = email
//        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val option: String = parent?.getItemAtPosition(position) as String
        Toast.makeText(applicationContext, "$position", Toast.LENGTH_SHORT).show()
    }
}