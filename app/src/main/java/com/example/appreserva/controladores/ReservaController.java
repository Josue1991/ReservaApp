package com.example.appreserva.controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.appreserva.database.Contrato;
import com.example.appreserva.database.Database;
import com.example.appreserva.model.Reserva;
import com.example.appreserva.model.Usuarios;

public class ReservaController {
    private final Database db;
    private SQLiteDatabase database;
    public ReservaController(Context context) {
        db = new Database(context);
    }
    public long nuevaReserva(Reserva nueva) {
        long retorno = 0;
        try{
            SQLiteDatabase baseDeDatos = db.getWritableDatabase();
            ContentValues valoresParaInsertar = new ContentValues();
            valoresParaInsertar.put("nombreUsuario", nueva.getNombreUsuario());
            valoresParaInsertar.put("fecha", nueva.getFecha().toString());
            valoresParaInsertar.put("hora", nueva.getHora());
            valoresParaInsertar.put("campo", nueva.getCampo());
            retorno = baseDeDatos.insert("reserva", null, valoresParaInsertar);
            baseDeDatos.close();
        }catch (Exception e){
            Log.e("Error", e.toString());
        }
        return retorno;
    }

    public Reserva verReserva(String numero) {
        Reserva retorno = new Reserva();
        int id = Integer.parseInt(numero);
        try{
        SQLiteDatabase base = db.getWritableDatabase();
        String[] columnasAConsultar = {
                Contrato.Reserva.COLUMN_ID,
                Contrato.Reserva.COLUMN_NOMBRE,
                Contrato.Reserva.COLUMN_FECHA,
                Contrato.Reserva.COLUMN_HORA,
                Contrato.Reserva.COLUMN_CAMPO,
                Contrato.Reserva.COLUMN_PRECIO};
        String selection = Contrato.Reserva.COLUMN_ID + " = ?";
        String[] selectionArgs = {numero};

        Cursor cursor = base.query(
                Contrato.Reserva.TABLE_NAME,
                columnasAConsultar,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            int codigo = cursor.getInt(cursor.getColumnIndexOrThrow(Contrato.Reserva.COLUMN_ID));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(Contrato.Reserva.COLUMN_NOMBRE));
            String fecha = cursor.getString(cursor.getColumnIndexOrThrow(Contrato.Reserva.COLUMN_FECHA));
            String hora = cursor.getString(cursor.getColumnIndexOrThrow(Contrato.Reserva.COLUMN_HORA));
            String campo = cursor.getString(cursor.getColumnIndexOrThrow(Contrato.Reserva.COLUMN_CAMPO));
            Double precio = cursor.getDouble(cursor.getColumnIndexOrThrow(Contrato.Reserva.COLUMN_PRECIO));
            if(codigo > 0){
                retorno.setId(codigo);
                retorno.setNombreUsuario(nombre);
                retorno.setFecha(fecha);
                retorno.setHora(hora);
                retorno.setPrecio(precio);
                retorno.setCampo(campo);
            }
        }
        cursor.close();
        }catch (Exception e){
            Log.e("Error", e.toString());
        }
        return retorno;
    }

}
