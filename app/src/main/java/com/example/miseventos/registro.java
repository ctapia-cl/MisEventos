package com.example.miseventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class registro extends AppCompatActivity {

    private TextInputLayout tilUsername, tilContrasena, tilRespuesta;
    private Button btnVolver, btnGuardar, btnSalir;
    private Spinner spnPregunta;
    private TextView tvIngdatos;

    //spinner trabaja con adaptador para asignar importancia al evento
    private String[] preguntaSecreta;
    private ArrayAdapter adapterPregunta;

    //Usuarios
    private ArrayList<Usuarios> losUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        poblar();
        referencias();
        eventos();

    }

    private void grabarUsuario(){
        String userName, contrasena, pregunta, respuesta = "";
        boolean userNameOk = true;

        userName = tilUsername.getEditText().getText().toString();
        contrasena = tilContrasena.getEditText().getText().toString();
        pregunta = spnPregunta.getSelectedItem().toString();
        respuesta = tilRespuesta.getEditText().getText().toString();

        for(Usuarios u : losUsuarios){
            if(u.getNombre().equals(userName)) {
                userNameOk = false;
                break;
            }
        }
        if(userName.isEmpty() || contrasena.isEmpty() || spnPregunta.getSelectedItemPosition() == 0 || respuesta.isEmpty()){
            //userName.setError("Tiene errores de validación");
        }else {
            if(userNameOk) {
                Usuarios uss = new Usuarios(userName, contrasena, pregunta, respuesta);
                losUsuarios.add(uss);

                //grabarBaseDatos(cli);

                Toast.makeText(registro.this, "Grabado exitosamente", Toast.LENGTH_SHORT).show();
            }else{
              //  userName.setError("Rut ya está ingresado");
                Toast.makeText(registro.this, "NOOOO Grabado", Toast.LENGTH_SHORT).show();

            }
        }
    }
    private void poblar() {
        preguntaSecreta = new String[4];

        preguntaSecreta[0] = "Seleccione una pregunta secreta";
        preguntaSecreta[1] = "Nombre de Mascota";
        preguntaSecreta[2] = "Mes de nacimiento";
        preguntaSecreta[3] = "Ciudad de nacimiento";

        losUsuarios = new ArrayList<Usuarios>();
        losUsuarios.add(new Usuarios("admin", "admin123", "Mes de nacimiento", "septiembre" ));

    }

    private void mostrarprimeraActividad(){
        Intent primeraPantalla = new Intent(this,MainActivity.class);
        startActivity(primeraPantalla);

    }
    //region Eventos y referencias
    private void eventos(){

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabarUsuario();
            }
        });
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
        tvIngdatos = findViewById(R.id.tvIngdatos);


        //adaptador de spinner
        adapterPregunta = new ArrayAdapter(this, android.R.layout.simple_spinner_item, preguntaSecreta);
        //se selecciona desde donde se adaptaran los datos
        spnPregunta.setAdapter(adapterPregunta);
    }

}
