package com.example.miseventos;

import android.os.Bundle;
import android.util.Log;
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
    private Button btnEnviar, btnSalir;

    //spinner trabaja con adaptador para asignar importancia al evento
    private String[] importanciaEvento;
    private ArrayAdapter adapterImportancia;

    //Eventos arreglo de lista
    private ArrayList<Eventos> losEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_eventos);

        poblar();
        referencias();
        eventos();
    }


    private void referencias() {
        etTitulo = findViewById(R.id.etTitulo);
        etFechaEvento = findViewById(R.id.etFechaEvento);
        spnImportancia = findViewById(R.id.spnImportancia);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnSalir = findViewById(R.id.btnSalir);
        etLugar = findViewById(R.id.etLugar);
        //btnEliminar = findViewById(R.id.btnEliminar);
        etObservacion = findViewById(R.id.etObservacion);

        //adaptador de spinner
        adapterImportancia = new ArrayAdapter(this, android.R.layout.simple_spinner_item, importanciaEvento);
        //se selecciona desde donde se adaptaran los datos
        spnImportancia.setAdapter(adapterImportancia);
    }


    private void limpiarPantalla(){
        etTitulo.setText(""); etObservacion.setText(""); spnImportancia.setSelection(0);
        etLugar.setText(""); etFechaEvento.setText("");
    }

    private void grabarEvento(){
        String titulo, fecha, lugar, importancia, observacion = "";
        boolean tituloOk = true;

        titulo = etTitulo.getText().toString();
        fecha= etFechaEvento.getText().toString();
        importancia = spnImportancia.getSelectedItem().toString();
        lugar = etLugar.getText().toString();
        observacion = etObservacion.getText().toString();

        for(Eventos e : losEventos) {
            if (e.getTitulo().equals(titulo)) {
                tituloOk = false;
                Toast.makeText(registroEventos.this, "Usuario ya se encuentra registrado", Toast.LENGTH_LONG).show();
                break;
            }
        }
        if(titulo.isEmpty() || fecha.isEmpty() || spnImportancia.getSelectedItemPosition() == 0 || lugar.isEmpty()){
            //username.setError("Tiene errores de validación");
            Toast.makeText(registroEventos.this, "Debe llenar todos los campos", Toast.LENGTH_LONG).show();

        }else {
            if(tituloOk) {
                Eventos event = new Eventos(titulo, fecha, lugar, importancia, observacion);
                losEventos.add(event);

                //grabarBaseDatos(event);

                Toast.makeText(registroEventos.this, "Grabado exitosamente", Toast.LENGTH_LONG).show();
                Log.i("TAG_","evento registrado " + titulo);
                limpiarPantalla();
            }else{
                Toast.makeText(registroEventos.this, "Usuario ya se encuentra registrado", Toast.LENGTH_LONG).show();
            }
        }
    }



    private void poblar() {
        importanciaEvento = new String[4];

        importanciaEvento[0] = "Seleccione grado de importancia del evento";
        importanciaEvento[1] = "Alta";
        importanciaEvento[2] = "Media";
        importanciaEvento[3] = "Baja";

        losEventos = new ArrayList<Eventos>();
        //losUsuarios.add(new Usuarios("admin", "admin123", "Mes de nacimiento", "septiembre" ));

    }

    private void eventos() {
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabarEvento();

            }
        });
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}