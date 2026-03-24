package com.example.pizzas

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    private lateinit var gridView: GridView
    private val imagenes = intArrayOf(R.drawable.pizza_americana, R.drawable.pizza_mozarella, R.drawable.gondola_pizza)
    private val nombres = arrayOf("Pizza Americana", "Pizza Mozarella", "Pizza Gondola")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        gridView = findViewById(R.id.gridViewPizzas)

        gridView.adapter = object : BaseAdapter()
        {
            override fun getCount(): Int = imagenes.size
            override fun getItem(position: Int): Any = imagenes[position]
            override fun getItemId(position: Int): Long = position.toLong()
            override fun getView(position: Int, convertView: View?, parent: ViewGroup?):
                    View {
                        val imageView = ImageView(this@MainActivity)
                        imageView.setImageResource(imagenes[position])
                        imageView.layoutParams = ViewGroup.LayoutParams(300, 300)
                        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                return imageView
            }
        }

        gridView.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(this, nombres[position], Toast.LENGTH_SHORT).show()
        }
    }
}