package com.example.sesion04_autocompletetext

import android.os.Bundle
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast;
import android.view.View;


class TimePicker : AppCompatActivity() {
    private lateinit var timePicker: TimePicker //primero variable : Objeto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_time_picker)
        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
    }
    fun onClick(view : View) {
        Toast.makeText(getBaseContext(), "Hora seleccionada: " + timePicker.getHour() + ":" + timePicker.getMinute(),
            Toast.LENGTH_SHORT).show()
    }
}