package com.example.seguimiento

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MonedaActivity : BaseProgressActivity() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_segunda_actividad
    }
    private lateinit var editTextCantidad: EditText
    private lateinit var radioDolares: RadioButton
    private lateinit var radioSoles: RadioButton
    private lateinit var radioEuros: RadioButton
    private lateinit var radioLibras: RadioButton
    private lateinit var radioRupias: RadioButton
    private lateinit var radioReales: RadioButton
    private lateinit var radioPesos: RadioButton
    private lateinit var radioYuan: RadioButton
    private lateinit var radioYen: RadioButton


    //tipos cambio
    private val aDolar = 3.65f
    private val aEuro = 4.00f
    private val aLibra = 4.60f
    private val aRupia = 0.044f
    private val aReal = 0.74f
    private val aPeso = 0.23f
    private val aYuan = 0.52f
    private val aYen = 0.023f

    //a soles
    private fun convierteDolaresASoles(valor: Float) = valor * aDolar
    private fun convierteEurosASoles(valor: Float) = valor * aEuro
    private fun convierteLibrasASoles(valor: Float) = valor * aLibra
    private fun convierteRupiasASoles(valor: Float) = valor * aRupia
    private fun convierteRealesASoles(valor: Float) = valor * aReal
    private fun conviertePesosASoles(valor: Float) = valor * aPeso
    private fun convierteYuanASoles(valor: Float) = valor * aYuan
    private fun convierteYenASoles(valor: Float) = valor * aYen


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moneda)

        editTextCantidad = findViewById(R.id.editTextText)
        radioDolares = findViewById(R.id.radio0)
        radioSoles = findViewById(R.id.radio1)
        radioEuros = findViewById(R.id.radio2)
        radioLibras = findViewById(R.id.radio3)
        radioRupias = findViewById(R.id.radio4)
        radioReales = findViewById(R.id.radio5)
        radioPesos = findViewById(R.id.radio8)
        radioYuan = findViewById(R.id.radio6)
        radioYen = findViewById(R.id.radio7)
    }

    private fun convertirMoneda() {
        val cantidadTexto = editTextCantidad.text.toString().trim()

        // Validación mejorada
        if (cantidadTexto.isEmpty()) {
            mostrarError("Ingresa una cantidad")
            return
        }

        val cantidad = cantidadTexto.toFloatOrNull()
        if (cantidad == null || cantidad <= 0) {
            mostrarError("Ingresa un número válido mayor a 0")
            return
        }

        // Realizar conversión
        when {

            radioDolares.isChecked ->
                mostrarResultado(
                    "$cantidad dólares = ${
                        formato(
                            convierteDolaresASoles(cantidad)
                        )
                    } soles"
                )

            radioEuros.isChecked ->
                mostrarResultado(
                    "$cantidad euros = ${
                        formato(
                            convierteEurosASoles(cantidad)
                        )
                    } soles"
                )

            radioLibras.isChecked ->
                mostrarResultado(
                    "$cantidad libras = ${
                        formato(
                            convierteLibrasASoles(cantidad)
                        )
                    } soles"
                )

            radioRupias.isChecked ->
                mostrarResultado(
                    "$cantidad rupias = ${
                        formato(
                            convierteRupiasASoles(cantidad)
                        )
                    } soles"
                )

            radioReales.isChecked ->
                mostrarResultado(
                    "$cantidad reales = ${
                        formato(
                            convierteRealesASoles(cantidad)
                        )
                    } soles"
                )

            radioPesos.isChecked ->
                mostrarResultado(
                    "$cantidad pesos = ${
                        formato(
                            conviertePesosASoles(cantidad)
                        )
                    } soles"
                )

            radioYuan.isChecked ->
                mostrarResultado(
                    "$cantidad yuanes = ${
                        formato(
                            convierteYuanASoles(cantidad)
                        )
                    } soles"
                )

            radioYen.isChecked ->
                mostrarResultado(
                    "$cantidad yenes = ${
                        formato(
                            convierteYenASoles(cantidad)
                        )
                    } soles"
                )

            else ->
                mostrarError("Selecciona una moneda")
        }
    }

    fun miClicManejador(view: View) {
        when (view.id) {
            R.id.btnConvertir -> convertirMoneda()
        }
    }

    // Funciones auxiliares para mejorar la legibilidad
    private fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
        editTextCantidad.requestFocus()
    }

    private fun mostrarResultado(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    private fun formato(valor: Float): String {
        return String.format("%.2f", valor)
    }
}




