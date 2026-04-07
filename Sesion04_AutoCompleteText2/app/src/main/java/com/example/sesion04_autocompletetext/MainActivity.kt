package com.example.sesion04_autocompletetext

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.AutoCompleteTextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val presidents = arrayOf(
            "José Luis Bustamante",
            "Zenón Noriega",
            "Manuel Odriá",
            "Manuel Prado",
            "Fco Morales Bermúdez",
            "Fernando Belaunde",
            "Alberto Fujimori",
            "Valentín Paniagua",
            "Alejandro Toledo",
            "Alan García",
            "Pedro Pablo Kuczynski",
            "Martín Alberto Vizcarra",
            "Pedro Castillo Terrones",
            "Dina Boluarte Zegarra",
            "Jose Jeri Ore"
        )
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, presidents)
        val textView = findViewById<AutoCompleteTextView>(R.id.txtPresidentes)
        textView.threshold = 3
        textView.setAdapter(adapter)
    }
    fun onTimePicker(view: View) {
        val intent = Intent(this, TimePicker::class.java)
        startActivity(intent)
    }
    fun onDatePicker(view: View) {
        val intent = Intent(this, DatePicker::class.java)
        startActivity(intent)
    }
}