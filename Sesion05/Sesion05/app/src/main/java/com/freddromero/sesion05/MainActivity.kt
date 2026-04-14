package com.freddromero.sesion05

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var productos: MutableList<Producto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerProductos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        productos = cargar()
        recyclerView.adapter = Vista(productos)
    }
    private fun guardar(productos: List<Producto>) {
        val prefs = getSharedPreferences("ProductosXML", MODE_PRIVATE)
        val editor = prefs.edit()
        editor.clear()
        for ((index, producto) in productos.withIndex()) {
            editor.putString("nombre_$index", producto.nombre)
            editor.putInt("cantidad_$index", producto.cantidad)
            editor.putFloat("precio_$index", producto.precio.toFloat())
        }
        editor.putInt("total", productos.size)
        editor.apply()
    }
    private fun cargar(): MutableList<Producto> {
        val prefs = getSharedPreferences("ProductosXML", MODE_PRIVATE)
        val total = prefs.getInt("total", 0)
        val lista = mutableListOf<Producto>()
        if (total == 0) {
            lista.add(Producto("Laptop", 5, 3500.0))
            lista.add(Producto("Mouse", 50, 25.5))
            lista.add(Producto("Teclado", 10, 80.0))
            lista.add(Producto("Monitor", 7, 00.0))
            guardar(lista)
        }
        else {
            for (i in 0 until total) {
                val nombre = prefs.getString("nombre_$i", "") ?: ""
                val cantidad = prefs.getInt("cantidad_$i", 0)
                val precio = prefs.getFloat("precio_$i", 0f).toDouble()
                lista.add(Producto(nombre, cantidad, precio))
            }
        }
        return lista
    }
}