package com.example.appreserva.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "reserva1.db";
    private static final int DATABASE_VERSION = 1;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_USERS_TABLE = "CREATE TABLE " + Contrato.Usuarios.TABLE_NAME + " (" +
                Contrato.Usuarios.COLUMN_ID + " INTEGER PRIMARY KEY," +
                Contrato.Usuarios.COLUMN_NOMBRE + " TEXT," +
                Contrato.Usuarios.COLUMN_EMAIL + " TEXT," +
                Contrato.Usuarios.COLUMN_USUARIOS + " TEXT," +
                Contrato.Usuarios.COLUMN_PASSWORD + " TEXT," +
                Contrato.Usuarios.COLUMN_REPASSWORD + " TEXT)";

        String SQL_CREATE_RESERVA_TABLE = "CREATE TABLE " + Contrato.Reserva.TABLE_NAME + " (" +
                Contrato.Reserva.COLUMN_ID + " INTEGER PRIMARY KEY," +
                Contrato.Reserva.COLUMN_CAMPO + " TEXT," +
                Contrato.Reserva.COLUMN_FECHA + " TEXT," +
                Contrato.Reserva.COLUMN_HORA + " TEXT," +
                Contrato.Reserva.COLUMN_PRECIO + " TEXT," +
                Contrato.Reserva.COLUMN_NOMBRE + " TEXT)";
        db.execSQL(SQL_CREATE_USERS_TABLE);
        db.execSQL(SQL_CREATE_RESERVA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS reserva");
        onCreate(db);
    }
}