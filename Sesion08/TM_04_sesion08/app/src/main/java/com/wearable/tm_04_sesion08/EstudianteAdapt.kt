package com.wearable.tm_04_sesion08

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EstudianteAdapt(
    private val lista: List<Estudiante>,
    private val onDelete: (Estudiante) -> Unit,
    private val onUpdate: (Estudiante) -> Unit
) : RecyclerView.Adapter<EstudianteAdapt.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvNombre)
        val tvCarrera: TextView = view.findViewById(R.id.tvCarrera)
        val tvCurso: TextView = view.findViewById(R.id.tvCurso)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.estudiantes, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val estudiante = lista[position]

        holder.tvNombre.text = estudiante.nombre
        holder.tvCarrera.text = estudiante.carrera
        holder.tvCurso.text = estudiante.curso

        holder.itemView.setOnClickListener {
            onUpdate(estudiante)
        }
        holder.itemView.setOnLongClickListener {
            onDelete(estudiante)
            true
        }
    }
    override fun getItemCount(): Int = lista.size
}
