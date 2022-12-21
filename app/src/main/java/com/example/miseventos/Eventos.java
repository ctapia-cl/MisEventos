package com.example.miseventos;

public class Eventos {
    private String titulo;
    private String fechaEvento;
    private String lugar;
    private String importancia;
    private String observacion;


    public Eventos(String titulo, String fechaEvento, String lugar, String importancia, String observacion) {

        this.titulo = titulo;
        this.fechaEvento = fechaEvento;
        this.lugar = lugar;
        this.importancia = importancia;
        this.observacion = observacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(String fechaEvento) {
        this.fechaEvento = fechaEvento;
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
}
