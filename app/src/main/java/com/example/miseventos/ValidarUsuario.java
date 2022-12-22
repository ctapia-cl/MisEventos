package com.example.miseventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ValidarUsuario extends AppCompatActivity {
    private EditText etIngUsuario;
    private Button btnVolver, btnEnviar;
    private TextView tvIngData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validar_usuario);


        referencias();
        eventos();
    }


    private void mostrarrecuperarpass(){
        Intent recuperarpass = new Intent(this,RecuperarPass.class);
        startActivity(recuperarpass);

    }
    //region Eventos y referencias
    private void eventos(){
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle usuario = new Bundle();
                usuario.putString("usuario", etIngUsuario.getText().toString());

                Intent intent = new Intent(ValidarUsuario.this, RecuperarPass.class);
                intent.putExtras(usuario);
                startActivity(intent);

               // mostrarrecuperarpass();
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
        etIngUsuario = findViewById(R.id.etIngUsuario);
        btnVolver = findViewById(R.id.btnVolver);
        btnEnviar = findViewById(R.id.btnEnviar);
        tvIngData = findViewById(R.id.tvIngData);
    }
}