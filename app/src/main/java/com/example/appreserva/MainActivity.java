package com.example.appreserva;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appreserva.database.Contrato;
import com.example.appreserva.database.Database;

public class MainActivity extends AppCompatActivity {
    Database base = new Database(this);
    SQLiteDatabase db = base.getReadableDatabase();
    int band = 0;
    String email;
    String contrasena;
    String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button pulsar = findViewById(R.id.btnIngreso);
        Button registrar = findViewById(R.id.btnRegistrarse);
        TextView usuarioIn = findViewById(R.id.txtEmail);
        String user = usuarioIn.getText().toString();
        TextView claveIn = findViewById(R.id.txtPassword);
        String pass = claveIn.getText().toString();

        String[] projection = {
                Contrato.Usuarios.COLUMN_EMAIL,
                Contrato.Usuarios.COLUMN_PASSWORD,
                Contrato.Usuarios.COLUMN_NOMBRE
        };

        pulsar.setOnClickListener(v -> {

            String usuarioVal = Contrato.Usuarios.COLUMN_EMAIL + " = ?";
            String[] userArgs = {user};


            Cursor cursor = db.query(
                    Contrato.Usuarios.TABLE_NAME,   // Tabla
                    projection,                            // Columnas a retornar
                    usuarioVal,                             // Cláusula WHERE
                    userArgs,                         // Argumentos de la cláusula WHERE
                    null,                                  // Agrupamiento de filas
                    null,                                  // Filtro por grupos de fila
                    null                              // Orden de los resultados
            );

            while (cursor.moveToNext()) {
                email = cursor.getString(cursor.getColumnIndexOrThrow(Contrato.Usuarios.COLUMN_EMAIL));
                contrasena = cursor.getString(cursor.getColumnIndexOrThrow(Contrato.Usuarios.COLUMN_PASSWORD));
                nombre = cursor.getString(cursor.getColumnIndexOrThrow(Contrato.Usuarios.COLUMN_NOMBRE));
                if(email.equals(user) && contrasena.equals(pass)){
                    band = 1;
                }
            }
            cursor.close();
            base.close();

            if(band == 1){
                Toast.makeText(MainActivity.this, "Usuario Aceptado", Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this, Pantalla_Principal.class);
                i.putExtra("msj", nombre);
                startActivity(i);
            }
            else{
                Toast.makeText(MainActivity.this, "Usuario y/o Clave son Incorrecto", Toast.LENGTH_LONG).show();
            }
        });

        registrar.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, Registro.class);
            startActivity(i);
        });


    }
}
