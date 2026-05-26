package com.wearable.tm_04_sesion08

import android.widget.*
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener



class MainActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etCarrera: EditText
    private lateinit var etCurso: EditText
    private lateinit var btnGuardar: Button
    private lateinit var rvEstudiantes: RecyclerView

    private lateinit var lista: MutableList<Estudiante>
    private lateinit var adapter: EstudianteAdapt
    private lateinit var database: DatabaseReference

    private var estudianteEditando: Estudiante? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNombre = findViewById(R.id.etNombre)
        etCarrera = findViewById(R.id.etCarrera)
        etCurso = findViewById(R.id.etCurso)
        btnGuardar = findViewById(R.id.btnGuardar)
        rvEstudiantes = findViewById(R.id.rvEstudiantes)

        database = FirebaseDatabase.getInstance().getReference("Estudiante")

        lista = mutableListOf()
        adapter = EstudianteAdapt(
            lista,
            onDelete = { eliminarEstudiante(it) },
            onUpdate = { editarEstudiante(it) }
        )
        rvEstudiantes.layoutManager = LinearLayoutManager(this)
        rvEstudiantes.adapter = adapter
        btnGuardar.setOnClickListener {
            guardarEstudiante()
        }
        obtenerEstudiantes()
    }
    private fun guardarEstudiante() {
        val nombre = etNombre.text.toString()
        val carrera = etCarrera.text.toString()
        val curso = etCurso.text.toString()
        if (nombre.isEmpty() || carrera.isEmpty() || curso.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        if (estudianteEditando == null) {
            val id = database.push().key ?: return
            val estudiante = Estudiante(id, nombre, carrera, curso)
            database.child(id).setValue(estudiante)
        } else {
            val updates = mapOf(
                "nombre" to nombre,
                "carrera" to carrera,
                "curso" to curso
            )
            database.child(estudianteEditando!!.id)
                .updateChildren(updates)
            estudianteEditando = null
            btnGuardar.text = "Guardar"
        }
        limpiarCampos()
    }
    private fun obtenerEstudiantes() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                lista.clear()
                for (data in snapshot.children) {
                    val estudiante = data.getValue(Estudiante::class.java)
                    if (estudiante != null) {
                        estudiante.id = data.key ?: ""
                        lista.add(estudiante)
                    }
                }
                adapter.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, "Error al leer datos", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun eliminarEstudiante(est: Estudiante) {
        database.child(est.id).removeValue()
        Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show()
    }
    private fun editarEstudiante(est: Estudiante) {
        estudianteEditando = est
        etNombre.setText(est.nombre)
        etCarrera.setText(est.carrera)
        etCurso.setText(est.curso)
        btnGuardar.text = "Actualizar"
    }
    private fun limpiarCampos() {
        etNombre.setText("")
        etCarrera.setText("")
        etCurso.setText("")
    }
}
