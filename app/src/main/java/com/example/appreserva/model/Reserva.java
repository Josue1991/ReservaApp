package com.example.appreserva.model;

import java.util.Date;

public class Reserva {
    private int id;
    private String Usuario;
    private Date fecha;
    private String hora;
    private String nombreCampo;

    public Reserva() {
    }

    public Reserva(int id, String usuario, Date fecha, String hora, String nombreCampo) {
        this.id = id;
        Usuario = usuario;
        this.fecha = fecha;
        this.hora = hora;
        this.nombreCampo = nombreCampo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNombreCampo() {
        return nombreCampo;
    }

    public void setNombreCampo(String nombreCampo) {
        this.nombreCampo = nombreCampo;
    }
}
