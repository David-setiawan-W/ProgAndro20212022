package com.example.minggu6

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class Dua : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val frag = inflater.inflate(R.layout.activity_dua, container, false)
        val btnFrag = frag.findViewById<Button>(R.id.btnFragHal2)
        btnFrag.setOnClickListener {
            val intent = Intent(context, Halaman2 :: class.java)
            context?.startActivity(intent)
            Toast.makeText(context, "Halaman 2", Toast.LENGTH_SHORT).show()
        }
        return frag
    }
}