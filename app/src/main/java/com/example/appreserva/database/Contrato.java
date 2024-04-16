package com.example.appreserva.database;

public final class Contrato {
    private Contrato() {}

    public static class Usuarios {
        public static final String TABLE_NAME = "usuarios";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_USUARIOS = "usuarios";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_REPASSWORD = "re_password";
    }
}
