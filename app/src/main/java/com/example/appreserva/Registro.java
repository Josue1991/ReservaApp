package com.example.appreserva;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Registro extends AppCompatActivity {
    private static final String PREF_FILE_NAME = "Prueba";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        Button registrar = findViewById(R.id.btIngresarNew);
        Button cancelar = findViewById(R.id.btnCancelar);
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        registrar.setOnClickListener(v -> {
            EditText nombre = findViewById(R.id.txNewNombre);
            EditText email = findViewById(R.id.txtNewEmail);
            EditText password = findViewById(R.id.txtPassword);
            EditText repassword = findViewById(R.id.idRePassword);
            EditText usuario = findViewById(R.id.txtNewUsusario);
            editor.putString("name", nombre.getText().toString());
            editor.putString("email", email.getText().toString());
            editor.putString("password", password.getText().toString());
            editor.putString("re-password", repassword.getText().toString());
            editor.putString("user", usuario.getText().toString());
            editor.apply();
            finish();
            Intent i = new Intent(Registro.this, Pantalla_Principal.class);
            i.putExtra("msj", "Prueba 123");
            startActivity(i);
            Toast.makeText(Registro.this, "Usuario Ingresado", Toast.LENGTH_LONG).show();
        });

        cancelar.setOnClickListener(v -> {
            Intent i = new Intent(Registro.this, MainActivity.class);
            startActivity(i);
            Toast.makeText(Registro.this, "Usuario Ingresado", Toast.LENGTH_LONG).show();
        });

    }
}