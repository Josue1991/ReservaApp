package com.example.appreserva;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appreserva.controladores.UsuariosController;
import com.example.appreserva.model.Usuarios;

public class MainActivity extends AppCompatActivity {
    UsuariosController usuariosController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        usuariosController = new UsuariosController(MainActivity.this);
        Button pulsar = findViewById(R.id.btnIngreso);
        Button registrar = findViewById(R.id.btnRegistrarse);


        pulsar.setOnClickListener(v -> {
            TextView usuarioIn = findViewById(R.id.txtEmail);
            String user = usuarioIn.getText().toString();
            TextView claveIn = findViewById(R.id.txtPassword);
            String pass = claveIn.getText().toString();

            Usuarios nuevo = new Usuarios();
            nuevo.setEmail(user);
            nuevo.setPassword(pass);
            Usuarios respuesta = usuariosController.validarIngreso(nuevo);
            if(respuesta.getNombre() != null){
                Intent i = new Intent(MainActivity.this, Pantalla_Principal.class);
                i.putExtra("msj", respuesta.getNombre());
                startActivity(i);
            }
            else{
                Toast.makeText(MainActivity.this, "Usuario no existe!!", Toast.LENGTH_LONG).show();
            }
        });

        registrar.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, Registro.class);
            startActivity(i);
        });


    }
}
