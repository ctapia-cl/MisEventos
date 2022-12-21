package com.example.miseventos;

public class Usuarios {
    private String username, contrasena, pregunta, respuesta;

    //region constructor
    public Usuarios(String username, String contrasena, String pregunta, String respuesta) {
        this.username = username;
        this.contrasena = contrasena;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }
    //endregion

    //region get and set

    public String getUsername() {
        return username;
    }

    public void setUsername(String nombre) { this.username = username; }

    public String getContrasena() { return contrasena;  }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }


    //endregion

}
