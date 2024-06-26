package com.example.appreserva;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appreserva.controladores.UsuariosController;
import com.example.appreserva.model.Usuarios;

public class Registro extends AppCompatActivity {
    UsuariosController usuariosController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        Button registrar = findViewById(R.id.btIngresarNew);
        Button cancelar = findViewById(R.id.btnCancelar);


        usuariosController = new UsuariosController(Registro.this);

        registrar.setOnClickListener(v -> mostrarAlertDialog());

        cancelar.setOnClickListener(v -> {
            Intent i = new Intent(Registro.this, MainActivity.class);
            startActivity(i);
            Toast.makeText(Registro.this, "Usuario Ingresado", Toast.LENGTH_LONG).show();
        });
    }
    public void mostrarAlertDialog() {
        EditText nombre = findViewById(R.id.txNewNombre);
        EditText email = findViewById(R.id.txtNewEmail);
        EditText password = findViewById(R.id.txtNewPassword);
        EditText repassword = findViewById(R.id.idRePassword);
        EditText usuario = findViewById(R.id.txtNewUsusario);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Registro de nuevo usuario");
        builder.setMessage("Desea ingresar al usuario: " + nombre.getText().toString());

        // Botón positivo (Aceptar)
        builder.setPositiveButton("Si, Ingresar", (dialog, which) -> {

            Toast.makeText(Registro.this, "Botón Aceptar clickeado", Toast.LENGTH_SHORT).show();
            Usuarios nuevo = new Usuarios(
                    0,
                    nombre.getText().toString(),
                    email.getText().toString(),
                    password.getText().toString(),
                    repassword.getText().toString(),
                    usuario.getText().toString());
            long respuesta = usuariosController.nuevoUsuario(nuevo);
            if(respuesta != -1){
                Intent i = new Intent(Registro.this, Pantalla_Principal.class);
                i.putExtra("msj", nuevo.getNombre());
                startActivity(i);
            }
            else{
                Toast.makeText(Registro.this, "Usuario no ha sido ingresado", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        builder.setNegativeButton("No", (dialog, which) -> Toast.makeText(Registro.this, "Registro no ingresado", Toast.LENGTH_SHORT).show());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}