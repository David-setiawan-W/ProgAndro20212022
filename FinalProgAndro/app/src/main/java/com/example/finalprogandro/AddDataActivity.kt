package com.example.finalprogandro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_data.*

class AddDataActivity : AppCompatActivity(){
    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)

        setSupportActionBar(findViewById(R.id.menubar_def))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val edtNama = findViewById<EditText>(R.id.edtNama)
        val edtNik = findViewById<EditText>(R.id.edtNik)
        val spnJenisKel = findViewById<Spinner>(R.id.spnJenisKel)

        ArrayAdapter.createFromResource(this, R.array.jenisKelamin, android.R.layout.simple_spinner_item)
            .also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnJenisKel.adapter = adapter
            }

        btnSubmit.setOnClickListener {
            val penduduk = penduduk(edtNama.text.toString(), edtNik.text.toString(), spnJenisKel.getSelectedItem().toString())
            db.collection("penduduk").document(penduduk.NIK).set(penduduk)
            edtNama.text.clear()
            edtNik.text.clear()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menubar_adddata, menu)
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