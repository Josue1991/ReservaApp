package com.example.appreserva;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Pantalla_Principal extends AppCompatActivity {
    private static final String PREF_FILE_NAME = "Prueba";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla_principal);
        Intent pantallaMain = getIntent();
        String mensaje = pantallaMain.getStringExtra("msj");
        TextView texto = findViewById(R.id.textoNombre);
        texto.setText("Bienvenido, " + mensaje);

    }
}