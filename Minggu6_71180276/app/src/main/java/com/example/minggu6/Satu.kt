package com.example.minggu6

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class Satu : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val frag = inflater.inflate(R.layout.activity_satu, container, false)
        val btnFrag = frag.findViewById<Button>(R.id.btnFragHal1)
        btnFrag.setOnClickListener {
            val intent = Intent(context, Halaman1 :: class.java)
            context?.startActivity(intent)
            Toast.makeText(context, "Halaman 1", Toast.LENGTH_SHORT).show()
        }
        return frag
    }
}