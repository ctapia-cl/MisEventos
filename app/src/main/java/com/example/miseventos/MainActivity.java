package com.example.miseventos;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
        private TextInputLayout tilUsuario, tilPass;
        private Button btnIngresar, btnRegistrar;
        private TextView tvRecuperarpass;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            referencias();
            eventos();
        }

    private void mostrarsegundaactividad(){
        Intent segundaPantalla = new Intent(this,registro.class);
        startActivity(segundaPantalla);

    }
    private void mostrarterceraactividad(){
        Intent terceraPantalla = new Intent(this,ValidarUsuario.class);
        startActivity(terceraPantalla);

    }
    //region Eventos y referencias
    private void eventos(){
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarsegundaactividad();
            }
        });
        tvRecuperarpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarterceraactividad();
            }
        });

    }
    private void referencias() {
        tilUsuario = findViewById(R.id.tilUsuario);
        tilPass = findViewById(R.id.tilPass);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        tvRecuperarpass = findViewById(R.id.tvRecuperarpass);
    }}


