package com.example.appreserva.model;

public class Usuarios {
    private int id;
    private String nombre;
    private String email;
    private String password;
    private String rePassword;
    private String usuario;

    public Usuarios(){

    }
    public Usuarios(int id,String nombre, String email, String password, String rePassword, String usuario) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rePassword = rePassword;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
