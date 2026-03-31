package com.example.seguimiento

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log;
import android.widget.ProgressBar;
class SegundaActividad : BaseProgressActivity() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_segunda_actividad
    }
    private var TIMER_RUNTIME = 10_000
    private var nbActivo = false
    private lateinit var nProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_segunda_actividad)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nProgressBar = findViewById(R.id.progressBar)
        val timerThread = object : Thread() {
            override fun run() {
                nbActivo = true
                try {
                    var espera1 = 0
                    while (nbActivo && espera1 < TIMER_RUNTIME) {
                        sleep(200)
                        if (nbActivo) {
                            espera1 += 200
                            actualizarProgress(espera1)
                        }
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    onContinuar()
                }
            }
        }
        timerThread.start()
    }
    private fun actualizarProgress(timePassed: Int) {
        if (::nProgressBar.isInitialized) {
            val progress =
                nProgressBar.max * timePassed / TIMER_RUNTIME

            runOnUiThread {
                nProgressBar.progress = progress
            }
        }
    }
    private fun onContinuar() {
        Log.d("mensajeFinal", "Su barra de progreso acaba de cargar")
    }
}
