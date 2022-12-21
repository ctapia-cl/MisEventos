package com.example.miseventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class RecuperarPass extends AppCompatActivity {

    private TextInputLayout tilIngusuario, tilIngnewpass;
    private Button btnVolver, btnEnviar;
    private TextView tvIngdatos2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_pass);
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

        tilIngusuario = findViewById(R.id.tilIngusuario);
        tilIngnewpass = findViewById(R.id.tilIngnewpass);
        btnVolver = findViewById(R.id.btnVolver);
        btnEnviar = findViewById(R.id.btnEnviar);
        tvIngdatos2 = findViewById(R.id.tvIngdatos2);

    }
}