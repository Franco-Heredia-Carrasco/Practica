package com.example.practica;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormularioActivity extends AppCompatActivity {

    private EditText inputNombreObjeto;
    private RadioGroup radioGrupo;
    private Button botonGuardar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.formulario_actividad);

        inputNombreObjeto = findViewById(R.id.Poner_nombre_text);
        radioGrupo = findViewById(R.id.radioGrupo);
        botonGuardar = findViewById(R.id.botonGuardar);


        inputNombreObjeto.setOnClickListener(v -> {
            inputNombreObjeto.setText("");
        });

        botonGuardar.setOnClickListener(v -> {
            String nombreObjeto = inputNombreObjeto.getText().toString().trim();

            if (nombreObjeto.isEmpty()) {
                inputNombreObjeto.setError("El nombre del objeto no puede estar en blanco");
                inputNombreObjeto.requestFocus();
            } else {
                Toast.makeText(this, "¡Objeto registrado con éxito!", Toast.LENGTH_SHORT).show();

                // Lógica inyectada para capturar el RadioButton y enviar el Intent explícito
                int selectedId = radioGrupo.getCheckedRadioButtonId();
                if (selectedId != -1) {
                    RadioButton selectedRadioButton = findViewById(selectedId);
                    String categoriaSeleccionada = selectedRadioButton.getText().toString();

                    Intent intent = new Intent(FormularioActivity.this, DetalleObjetoActivity.class);

                    // --- NUEVO: Enviamos TAMBIÉN el nombre del objeto ---
                    intent.putExtra("EXTRA_NOMBRE_OBJETO", nombreObjeto);
                    // --- FIN NUEVO ---

                    intent.putExtra("EXTRA_CATEGORIA", categoriaSeleccionada);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Por favor selecciona una categoría", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
    }
}