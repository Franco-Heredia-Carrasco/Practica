package com.example.practica;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalleObjetoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_objeto_activity);

        // 1. Obtener las vistas
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})

        TextView txtNombre = findViewById(R.id.txtNombreRecibido);
        TextView txtCategoria = findViewById(R.id.txtCategoriaRecibida);
        Button botonVolver = findViewById(R.id.botonVolverInicio);

        // 2. Obtener el Intent que trajo los datos
        Intent intent = getIntent();

        // 3. Extraer los datos usando las MISMAS claves que en FormularioActivity
        String nombreRecibido = intent.getStringExtra("EXTRA_NOMBRE_OBJETO");
        String categoriaRecibida = intent.getStringExtra("EXTRA_CATEGORIA");

        // 4. Mostrar los datos en los TextViews
        if (nombreRecibido != null && !nombreRecibido.isEmpty()) {
            txtNombre.setText(nombreRecibido);
        } else {
            txtNombre.setText("Sin nombre");
        }

        if (categoriaRecibida != null && !categoriaRecibida.isEmpty()) {
            txtCategoria.setText(categoriaRecibida);
        } else {
            txtCategoria.setText("Sin categoría");
        }

        // 5. Configurar el botón de retorno (limpia el back stack)
        botonVolver.setOnClickListener(v -> {
            Intent intentVolver = new Intent(DetalleObjetoActivity.this, MainActivity.class);
            // Banderas para limpiar el historial intermedio
            intentVolver.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intentVolver);
            finish(); // Opcional: cierra esta actividad para que no se pueda volver atrás
        });
    }
}