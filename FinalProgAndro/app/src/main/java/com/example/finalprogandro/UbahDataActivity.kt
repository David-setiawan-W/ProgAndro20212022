package com.example.finalprogandro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_data.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.btnUbahData
import kotlinx.android.synthetic.main.activity_ubah_data.*

class UbahDataActivity : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah_data)

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

        btnCariData.setOnClickListener {
            db?.collection("penduduk")?.whereEqualTo("nik",edtNik.text.toString()).get()!!
                .addOnSuccessListener { doc ->
                    var output= ""
                    for (i in doc ){
                        output = "${i["nama"]}"
                }
                    edtNama.setText(output)
                }
        }

        btnUbahData.setOnClickListener {
            val penduduk = penduduk(edtNama.text.toString(), edtNik.text.toString(), spnJenisKel.getSelectedItem().toString())
            db.collection("penduduk").document(penduduk.NIK).set(penduduk)
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
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