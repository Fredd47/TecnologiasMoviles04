package com.example.seguimiento

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseProgressActivity : AppCompatActivity() {
    private val TIMER_RUNTIME = 4000
    private lateinit var progressBar: ProgressBar
    abstract fun getLayoutResId(): Int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barra)
        val container = findViewById<android.widget.FrameLayout>(R.id.contentContainer)
        layoutInflater.inflate(getLayoutResId(), container, true)
        progressBar = findViewById(R.id.progressBar)
        iniciarProgreso()
    }

    private fun iniciarProgreso() {
        val hilo = object : Thread() {
            override fun run() {
                var tiempo = 0
                try {
                    while (tiempo <= TIMER_RUNTIME) {
                        sleep(200)
                        tiempo += 200
                        actualizarProgress(tiempo)
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    runOnUiThread {
                        Toast.makeText(
                            this@BaseProgressActivity,
                            "Carga completa",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("Progress", "Carga completa")
                    }
                }
            }
        }
        hilo.start()
    }

    private fun actualizarProgress(tiempo: Int) {
        val progreso = progressBar.max * tiempo / TIMER_RUNTIME
        runOnUiThread {
            progressBar.progress = progreso
        }
    }
}