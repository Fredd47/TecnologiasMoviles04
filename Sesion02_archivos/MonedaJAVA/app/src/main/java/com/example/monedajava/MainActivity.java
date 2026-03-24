package com.example.monedajava;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private EditText editTextCantidad;
    private RadioGroup radioGroupMonedas;
    private static final float aDolar = 3.65f;
    private static final float aEuro = 4.00f;
    private static final float aLibra = 4.60f;
    private static final float aRupia = 0.047f;
    private static final float aReal = 0.72f;
    private static final float aPeso = 0.15f;
    private static final float aYuan = 0.53f;
    private static final float aYen = 0.023f;

    private float convierteDolaresASoles(float v) {
        return v * aDolar;
    }
    private float convierteEurosASoles(float v) {
        return v * aEuro;
    }
    private float convierteLibrasASoles(float v) {
        return v * aLibra;
    }
    private float convierteRupiasASoles(float v) {
        return v * aRupia;
    }
    private float convierteRealesASoles(float v) {
        return v * aReal;
    }
    private float conviertePesosASoles(float v) {
        return v * aPeso;
    }
    private float convierteYuanASoles(float v) {
        return v * aYuan;
    }
    private float convierteYenASoles(float v) {
        return v * aYen;
    }

    private void mostrarError(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }
    private void mostrarResultado(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }
    private String formato(float valor) {
        return String.format("%.2f", valor);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editTextCantidad = findViewById(R.id.editTextText);
        radioGroupMonedas = findViewById(R.id.radioGroupMonedas);
    }

    public void miClicManejador(View view) {
        if (view.getId() == R.id.btnConvertir) {
            convertirMoneda();
        }
    }
    private void convertirMoneda() {
        String texto = editTextCantidad.getText().toString().trim();
        if (texto.isEmpty()) {
            mostrarError("Ingresa una cantidad");
            return;
        }
        float cantidad;
        try {
            cantidad = Float.parseFloat(texto);
            if (cantidad <= 0) {
                mostrarError("Ingresa un numero mayor a 0");
                return;
            }
        } catch (NumberFormatException e) {
            mostrarError("Numero invalido");
            return;
        }
        int id = radioGroupMonedas.getCheckedRadioButtonId();
        if (id == -1) {
            mostrarError("Selecciona una moneda");
            return;
        }
        float resultado;
        String mensaje;

        if (id == R.id.radio0) {
            resultado = convierteDolaresASoles(cantidad);
            mensaje = cantidad + " dolares = " + formato(resultado) + " soles";

        } else if (id == R.id.radio2) {
            resultado = convierteEurosASoles(cantidad);
            mensaje = cantidad + " euros = " + formato(resultado) + " soles";

        } else if (id == R.id.radio3) {
            resultado = convierteLibrasASoles(cantidad);
            mensaje = cantidad + " libras = " + formato(resultado) + " soles";

        } else if (id == R.id.radio4) {
            resultado = convierteRupiasASoles(cantidad);
            mensaje = cantidad + " rupias = " + formato(resultado) + " soles";

        } else if (id == R.id.radio5) {
            resultado = convierteRealesASoles(cantidad);
            mensaje = cantidad + " reales = " + formato(resultado) + " soles";

        } else if (id == R.id.radio6) {
            resultado = convierteYuanASoles(cantidad);
            mensaje = cantidad + " yuanes = " + formato(resultado) + " soles";

        } else if (id == R.id.radio7) {
            resultado = convierteYenASoles(cantidad);
            mensaje = cantidad + " yenes = " + formato(resultado) + " soles";

        } else if (id == R.id.radio8) {
            resultado = conviertePesosASoles(cantidad);
            mensaje = cantidad + " pesos = " + formato(resultado) + " soles";

        } else {
            mostrarError("Moneda no válida");
            return;
        }
        mostrarResultado(mensaje);
    }
}
