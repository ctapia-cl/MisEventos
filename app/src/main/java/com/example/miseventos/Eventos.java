package com.example.miseventos;

import java.util.Date;

public class Eventos {
    private String titulo;
    private String fecha;
    private String lugar;
    private String importancia;
    private String observacion;
    private String usuario;
    private Integer id;

    public Eventos(Integer id, String usuario,String titulo, String fecha, String lugar, String importancia, String observacion) {

        this.id = id;
        this.titulo = titulo;
        this.fecha = fecha;
        this.lugar = lugar;
        this.importancia = importancia;
        this.observacion = observacion;
        this.usuario = usuario;
    }



    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getImportancia() {
        return importancia;
    }

    public void setImportancia(String importancia) {
        this.importancia = importancia;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public String toString() {
        return
                "titulo: " + titulo +
                ", fecha: " + fecha +
                ", lugar: " + lugar +
                ", importancia: " + importancia +
                ", observacion: " + observacion;

    }
}
