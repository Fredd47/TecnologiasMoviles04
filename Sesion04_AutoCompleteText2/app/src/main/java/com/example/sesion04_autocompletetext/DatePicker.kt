package com.example.sesion04_autocompletetext

import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DatePicker : AppCompatActivity() {
    private lateinit var timePicker: TimePicker //primero variable : Objeto
    private lateinit var datePicker: DatePicker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_date_picker)
        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

    }
    fun onClick(view : View) {
        val day = datePicker.dayOfMonth
        val month = datePicker.month + 1
        val year = datePicker.year
        Toast.makeText(getBaseContext(), "Hora seleccionada: " + timePicker.getHour() + ":" + timePicker.getMinute(),
            Toast.LENGTH_SHORT).show()
    }
}