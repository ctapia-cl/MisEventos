package com.example.miseventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;

public class registro extends AppCompatActivity {

    private TextInputLayout tilUsername, tilContrasena, tilPregunta, tilRespuesta;
    private Button btnVolver, btnGuardar, btnSalir;
    private Spinner spnPregunta;

    //spinner trabaja con adaptador para asignar importancia al evento
    private String[] preguntaSecreta;
    private ArrayAdapter adapterPregunta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        poblar();
        referencias();
        eventos();

    }

    private void poblar() {
        preguntaSecreta = new String[4];

        preguntaSecreta[0] = "Seleccione una pregunta secreta";
        preguntaSecreta[1] = "Nombre de Mascota";
        preguntaSecreta[2] = "Mes de nacimiento";
        preguntaSecreta[3] = "Ciudad de nacimiento";
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
        spnPregunta = findViewById(R.id.spnPregunta);
        tilRespuesta = findViewById(R.id.tilRespuesta);
        btnVolver = findViewById(R.id.btnVolver);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnSalir = findViewById(R.id.btnGuardar);


        //adaptador de spinner
        adapterPregunta = new ArrayAdapter(this, android.R.layout.simple_spinner_item, preguntaSecreta);
        //se selecciona desde donde se adaptaran los datos
        spnPregunta.setAdapter(adapterPregunta);
    }

}
