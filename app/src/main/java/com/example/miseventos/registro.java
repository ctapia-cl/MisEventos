package com.example.miseventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class registro extends AppCompatActivity {

    private EditText etUsername, etPass, etRespuesta;
    private Button btnVolver, btnGuardar;
    private Spinner spnPregunta;
    private TextView tvIngdatos;

    //spinner trabaja con adaptador para asignar importancia al evento
    private String[] preguntaSecreta;
    private ArrayAdapter adapterPregunta;

    //Usuarios
    private ArrayList<Usuarios> losUsuarios;

    private int indiceActual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        poblar();
        referencias();
        eventos();
        obtenerDatosUsuario();
    }

    private void obtenerDatosUsuario(){
        if(indiceActual >= 0 && indiceActual < losUsuarios.size()) {
            Usuarios uss = losUsuarios.get(indiceActual);
            etUsername.setText(uss.getUsername());
            etPass.setText(uss.getContrasena());
            etRespuesta.setText(uss.getRespuesta());

            if(uss.getPregunta().equals("Nombre de Mascota")) spnPregunta.setSelection(1);

            if(uss.getPregunta().equals("Mes de nacimiento")) spnPregunta.setSelection(2);

            if(uss.getPregunta().equals("Ciudad de nacimiento")) spnPregunta.setSelection(3);

        }
    }

    private void grabarUsuario(){
        String username, contrasena, pregunta, respuesta = "";
        boolean userNameOk = true;

        username = etUsername.getText().toString();
        contrasena = etPass.getText().toString();
        pregunta = spnPregunta.getSelectedItem().toString();
        respuesta = etRespuesta.getText().toString();

        for(Usuarios u : losUsuarios){
            if(u.getUsername().equals(username)) {
                userNameOk = false;
                break;
            }
        }
        if(username.isEmpty() || contrasena.isEmpty() || spnPregunta.getSelectedItemPosition() == 0 || respuesta.isEmpty()){
            //userName.setError("Tiene errores de validación");
        }else {
            if(userNameOk) {
                Usuarios uss = new Usuarios(username, contrasena, pregunta, respuesta);
                losUsuarios.add(uss);

                grabarBaseDatos(uss);

                Toast.makeText(registro.this, "Grabado exitosamente", Toast.LENGTH_SHORT).show();
                Log.i("TAG_","usuario registrado " + userName);
            }else{
              //  userName.setError("Rut ya está ingresado");
                Toast.makeText(registro.this, "NOOOO Grabado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void grabarBaseDatos(Usuarios uss){
        try{
            AdministradorBaseDatos adbd = new AdministradorBaseDatos(this, "BDAplicacion", null, 1);
            SQLiteDatabase miBD = adbd.getWritableDatabase();

            //Forma android
            ContentValues reg = new ContentValues();
            reg.put("username", uss.getUsername());
            reg.put("contrasena", uss.getContrasena());
            reg.put("pregunta", uss.getPregunta());
            reg.put("respuesta", uss.getRespuesta());

            miBD.insert("usuarios", null, reg);

            miBD.close();
        }catch (Exception ex){
            Log.e("TAG_", ex.toString());
        }

        //consultaSQL();
    }

    private void poblar() {
        preguntaSecreta = new String[4];

        preguntaSecreta[0] = "Seleccione una pregunta secreta";
        preguntaSecreta[1] = "Nombre de Mascota";
        preguntaSecreta[2] = "Mes de nacimiento";
        preguntaSecreta[3] = "Ciudad de nacimiento";

        losUsuarios = new ArrayList<Usuarios>();
        //losUsuarios.add(new Usuarios("admin", "admin123", "Mes de nacimiento", "septiembre" ));

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

        etUsername = findViewById(R.id.etUsername);
        etPass = findViewById(R.id.etPass);
        spnPregunta = findViewById(R.id.spnPregunta);
        etRespuesta = findViewById(R.id.etRespuesta);
        btnVolver = findViewById(R.id.btnVolver);
        btnGuardar = findViewById(R.id.btnGuardar);
        tvIngdatos = findViewById(R.id.tvIngdatos);

        //adaptador de spinner
        adapterPregunta = new ArrayAdapter(this, android.R.layout.simple_spinner_item, preguntaSecreta);
        //se selecciona desde donde se adaptaran los datos
        spnPregunta.setAdapter(adapterPregunta);
    }

}
