package com.example.appreserva.database;

import android.provider.BaseColumns;

public final class Contrato {
    private Contrato() {}

    public static class Usuarios implements BaseColumns {
        public static final String TABLE_NAME = "usuarios";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_USUARIOS = "usuarios";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_REPASSWORD = "re_password";
    }
    public static class Reserva implements BaseColumns {
        public static final String TABLE_NAME = "reserva";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NOMBRE = "nombreUsuario";
        public static final String COLUMN_FECHA = "fecha";
        public static final String COLUMN_HORA = "hora";
        public static final String COLUMN_CAMPO = "campo";
        public static final String COLUMN_PRECIO = "precio";
    }
}
