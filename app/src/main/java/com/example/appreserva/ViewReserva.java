package com.example.appreserva;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appreserva.controladores.ReservaController;
import com.example.appreserva.model.Reserva;

public class ViewReserva extends AppCompatActivity {
    ReservaController reservaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_reserva);
        Button cancelar = findViewById(R.id.btnCancelarRes);
        Button buscar = findViewById(R.id.btnBuscar);
        Intent pantallaMain = getIntent();

        TextView numero = findViewById(R.id.txNumero);
        TextView nombre = findViewById(R.id.txNombre);
        TextView fecha = findViewById(R.id.txFechaHora);
        TextView precio = findViewById(R.id.txPrecio);

        reservaController =  new ReservaController(ViewReserva.this);
        String idReserva = pantallaMain.getStringExtra("msj");
        if(idReserva != null || idReserva != "") {
            Reserva reserva = reservaController.verReserva(idReserva);
            if(reserva.getId() != 0) {
                numero.setText("Numero de Reserva: " + reserva.getId() + " - " + reserva.getCampo());
                nombre.setText("Nombre:" + reserva.getNombreUsuario());
                fecha.setText("Fecha y Hora: " + reserva.getFecha() + "-" + reserva.getHora());
                precio.setText("Precio: $ 20");
            }
        }else{
            Toast.makeText(ViewReserva.this, "No se encontro Reserva", Toast.LENGTH_LONG).show();
        }

        buscar.setOnClickListener( s -> {
            EditText codigoTx = findViewById(R.id.txCodigo);
            String codigo = codigoTx.getText().toString();
            if(codigo != null || codigo != ""){
            Reserva reserva = reservaController.verReserva(codigo);
                if(reserva.getId() != 0) {
                    numero.setText("Numero de Reserva: " + reserva.getId() + " - " + reserva.getCampo());
                    nombre.setText("Nombre:" + reserva.getNombreUsuario());
                    fecha.setText("Fecha y Hora: " + reserva.getFecha() + "-" + reserva.getHora());
                    precio.setText("Precio: $ 20");
                }
                else{
                    Toast.makeText(ViewReserva.this, "No se encontro Reserva", Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(ViewReserva.this, "No ingreso codigo de reserva", Toast.LENGTH_LONG).show();
            }
        });

        cancelar.setOnClickListener( b -> {
            Intent i = new Intent(ViewReserva.this, MainActivity.class);
            startActivity(i);
        });
    }
}