package com.example.miseventos;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class registroEventos extends AppCompatActivity {
    private EditText etTitulo, etFechaEvento, etLugar, etObservacion;
    private Spinner spnImportancia;
    private Button btnGrabar;

    //spinner trabaja con adaptador para asignar importancia al evento
    //private String[] importanciaEvento;
    //private ArrayAdapter adapterImportancia;

    //Eventos arreglo de lista


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_eventos);


        referencias();


    }


    private void referencias() {
        etTitulo = findViewById(R.id.etTitulo);
        etFechaEvento = findViewById(R.id.etFechaEvento);
        spnImportancia = findViewById(R.id.spnImportancia);
        btnGrabar = findViewById(R.id.btnGrabar);
        etLugar = findViewById(R.id.etLugar);
        //btnEliminar = findViewById(R.id.btnEliminar);
        etObservacion = findViewById(R.id.etObservacion);

    }

    private void poblar() {
    }

    private void grabarEvento() {

    }

    private void eventos() {

    }

    private void limpiarPantalla() {

    }
}