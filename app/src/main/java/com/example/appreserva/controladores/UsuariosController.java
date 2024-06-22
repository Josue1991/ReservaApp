package com.example.appreserva.controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.appreserva.database.Contrato;
import com.example.appreserva.database.Database;
import com.example.appreserva.model.Usuarios;

public class UsuariosController {
    private final Database db;
    private SQLiteDatabase database;
    public UsuariosController(Context context) {
        db = new Database(context);
    }
    public long nuevoUsuario(Usuarios usuario) {
        long retorno = 0;
        try{
            SQLiteDatabase baseDeDatos = db.getWritableDatabase();
            ContentValues valoresParaInsertar = new ContentValues();
            valoresParaInsertar.put("email", usuario.getEmail());
            valoresParaInsertar.put("usuarios", usuario.getUsuario());
            valoresParaInsertar.put("password", usuario.getPassword());
            valoresParaInsertar.put("re_password", usuario.getRePassword());
            valoresParaInsertar.put("nombre", usuario.getNombre());
            retorno = baseDeDatos.insert("usuarios", null, valoresParaInsertar);
            baseDeDatos.close();
        }catch (Exception e){
            Log.e("Error", e.toString());
        }
        return retorno;
    }
  public int guardarCambios(Usuarios mascotaEditada) {
        SQLiteDatabase baseDeDatos = db.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombre", mascotaEditada.getNombre());
        valoresParaActualizar.put("email", mascotaEditada.getEmail());
        valoresParaActualizar.put("usuario", mascotaEditada.getUsuario());
        valoresParaActualizar.put("password", mascotaEditada.getPassword());
        valoresParaActualizar.put("re_Password", mascotaEditada.getRePassword());
        String campoParaActualizar = "id = ?";
        String[] argumentosParaActualizar = {String.valueOf(mascotaEditada.getId())};
        int retorno = baseDeDatos.update("usuarios", valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
        db.close();
        return retorno;
    }
    public Usuarios validarIngreso(Usuarios usuario) {
        SQLiteDatabase base = db.getWritableDatabase();
        String[] columnasAConsultar = {
                Contrato.Usuarios.COLUMN_EMAIL,
                Contrato.Usuarios.COLUMN_PASSWORD,
                Contrato.Usuarios.COLUMN_NOMBRE};
        String selection = Contrato.Usuarios.COLUMN_EMAIL + " = ?";
        String[] selectionArgs = {usuario.getEmail()};
        Usuarios retorno = new Usuarios();

        Cursor cursor = base.query(
                Contrato.Usuarios.TABLE_NAME,
                columnasAConsultar,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            String email = cursor.getString(cursor.getColumnIndexOrThrow(Contrato.Usuarios.COLUMN_EMAIL));
            String pass = cursor.getString(cursor.getColumnIndexOrThrow(Contrato.Usuarios.COLUMN_PASSWORD));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(Contrato.Usuarios.COLUMN_NOMBRE));
            if(usuario.getEmail().equals(email) && usuario.getPassword().equals(pass)){
                retorno.setEmail(email);
                retorno.setNombre(nombre);
            }
        }
        cursor.close();
        return retorno;
    }
}
