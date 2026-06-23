package com.ucsm.tm04_sesion11_ejercicios

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class MainActivity : AppCompatActivity() {

    private lateinit var txtResult: TextView
    private val barcodeLauncher = registerForActivityResult(ScanContract()) { result ->
        if (result.contents != null) {
            txtResult.text = "Resultado: ${result.contents}"
        } else {
            txtResult.text = "Escaneo cancelado"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnScan: Button = findViewById(R.id.btnScan)
        txtResult = findViewById(R.id.txtResult)
        btnScan.setOnClickListener {
            iniciarEscaneo()
        }
    }

    private fun iniciarEscaneo() {
        val options = ScanOptions()
        options.setPrompt("Escanea un código")
        options.setBeepEnabled(true)
        options.setOrientationLocked(true)
        barcodeLauncher.launch(options)
    }
}