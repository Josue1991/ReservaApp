package com.example.appreserva;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.appreserva.controladores.ReservaController;
import com.example.appreserva.controladores.UsuariosController;
import com.example.appreserva.model.Reserva;

public class Pantalla_Principal extends AppCompatActivity {

    ReservaController reservaController;
    UsuariosController usuariosController;
    Long respuesta = 0L;
    // Identificador único para la notificación
    private static final int NOTIFICATION_ID = 1;
    // ID del canal de notificación (obligatorio para Android 8.0 y superior)
    private static final String CHANNEL_ID = "my_channel";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla_principal);

        Button agendar = findViewById(R.id.btnAgendar);
        Button ver = findViewById(R.id.btnVerReserva);

        reservaController =  new ReservaController(Pantalla_Principal.this);
        usuariosController = new UsuariosController(Pantalla_Principal.this);

        Intent pantallaMain = getIntent();
        String mensaje = pantallaMain.getStringExtra("msj");
        TextView texto = findViewById(R.id.txtUsuario);
        texto.setText("Bienvenido, " + mensaje);

        CalendarView calendarView = findViewById(R.id.calenadario);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                                                 @Override
                                                 public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                                                     String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                                                     TextView fecha = findViewById(R.id.txFechaHora);
                                                     fecha.setText(date);
                                                 }
                                             });

            agendar.setOnClickListener(v -> {
                EditText campo = findViewById(R.id.txtCampo);
                EditText hora = findViewById(R.id.txtHora);
                TextView fecha = findViewById(R.id.txFechaHora);
                Reserva nueva = new Reserva();
                nueva.setCampo(campo.getText().toString());
                nueva.setNombreUsuario(mensaje);
                nueva.setHora(hora.getText().toString());
                nueva.setFecha(fecha.getText().toString());
                respuesta = reservaController.nuevaReserva(nueva);

                if(respuesta != -1){
                    createNotificationChannel();
                    sendNotification();
                    Intent i = new Intent(Pantalla_Principal.this, ViewReserva.class);
                    i.putExtra("msj", respuesta.toString());
                    startActivity(i);
                }
                else{
                    Toast.makeText(Pantalla_Principal.this, "Juego no Agendado", Toast.LENGTH_LONG).show();
                }
            });

            ver.setOnClickListener(x -> {
                Intent i = new Intent(Pantalla_Principal.this, ViewReserva.class);
                i.putExtra("msj", respuesta.toString());
                startActivity(i);
            });
    }
    private void sendNotification() {
        // Crear un intent para abrir la actividad cuando se hace clic en la notificación
        Intent intent = new Intent(this, ViewReserva.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Crear la notificación
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.icono)
                .setContentTitle("Juego Agendado")
                .setContentText("Su juego ha sido correctamente agendado!")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        // Obtener el NotificationManager
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Mostrar la notificación
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void createNotificationChannel() {
        // Crear el canal de notificación solo para Android 8.0 y superior
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Agendado";
            String description = "Juego Agendado";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Registrar el canal en el NotificationManager
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}