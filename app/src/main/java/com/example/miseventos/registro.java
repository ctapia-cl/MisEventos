package com.example.miseventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class registro extends AppCompatActivity {

    private TextInputLayout tilUsername, tilContrasena, tilPregunta, tilRespuesta;
    private Button btnVolver, btnGuardar, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        referencias();
        eventos();
    }

    private void mostrarprimeraActividad(){
        Intent primeraPantalla = new Intent(this,MainActivity.class);
        startActivity(primeraPantalla);

    }
    //region Eventos y referencias
    private void eventos(){
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void referencias() {

        tilUsername = findViewById(R.id.tilUsername);
        tilContrasena = findViewById(R.id.tilContrasena);
        tilPregunta = findViewById(R.id.tilPregunta);
        tilRespuesta = findViewById(R.id.tilRespuesta);
        btnVolver = findViewById(R.id.btnVolver);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnSalir = findViewById(R.id.btnGuardar);

    }

}
