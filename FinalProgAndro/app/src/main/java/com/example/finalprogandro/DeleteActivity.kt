package com.example.finalprogandro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore

class DeleteActivity : AppCompatActivity() {

    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)
        setSupportActionBar(findViewById(R.id.menubar_def))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val edtNik = findViewById<EditText>(R.id.edtNik)
        val btnDel = findViewById<Button>(R.id.btnDelete)
        val dataRef = db.collection("penduduk")

        btnDel.setOnClickListener {
            dataRef.document(edtNik.text.toString()).delete()
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menubar_deletedata, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem)= when(item.itemId) {
        R.id.btnBack ->{
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
            true
        }
        else ->{
            super.onOptionsItemSelected(item)
        }
    }
}