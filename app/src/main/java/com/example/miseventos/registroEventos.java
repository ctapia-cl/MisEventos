package com.example.miseventos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    private EditText etTitulo, etFecha, etLugar, etObservacion;
    private Spinner spnImportancia;
    private Button btnEnviar, btnSalir, btnLista;

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
        etFecha = findViewById(R.id.etFecha);
        spnImportancia = findViewById(R.id.spnImportancia);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnSalir = findViewById(R.id.btnSalir);
        etLugar = findViewById(R.id.etLugar);
        //btnEliminar = findViewById(R.id.btnEliminar);
        etObservacion = findViewById(R.id.etObservacion);
        btnLista = findViewById(R.id.btnLista);

        //adaptador de spinner
        adapterImportancia = new ArrayAdapter(this, android.R.layout.simple_spinner_item, importanciaEvento);
        //se selecciona desde donde se adaptaran los datos
        spnImportancia.setAdapter(adapterImportancia);
    }


    private void limpiarPantalla(){
        etTitulo.setText(""); etObservacion.setText(""); spnImportancia.setSelection(0);
        etLugar.setText(""); etFecha.setText("");
        // tvPaginacion.setText("" + losClientes.size());
        //indiceActual = -1;
    }


    private void grabarEvento(){
        String titulo, fecha, lugar, importancia, observacion,usuario = "";
        Integer id = 0;
        boolean tituloOk = true;

        //region extrae usuario en linea
        AdministradorBaseDatos adbd = new AdministradorBaseDatos(this, "BDAplicacion", null, 1);
        SQLiteDatabase miBD = adbd.getWritableDatabase();
        try {
            Cursor c = miBD.rawQuery("Select * from ultimoUsuario order by id desc", null);
            if(c.moveToFirst()){
                usuario = c.getString(1);
            }
        }catch (Exception ex){
            Log.e("TAG_", ex.toString());
        }finally {
            miBD.close();
        }

        //endregion

        titulo = etTitulo.getText().toString();
        fecha= etFecha.getText().toString();
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
                Eventos event = new Eventos(id,usuario,titulo, fecha, lugar, importancia, observacion);
                losEventos.add(event);

                grabarBaseDatos(event);

                Toast.makeText(registroEventos.this, "Grabado exitosamente", Toast.LENGTH_LONG).show();
                Log.i("TAG_","evento registrado " + titulo);

                //region enviar usuario a Muestraeventos
                Bundle usuario2 = new Bundle();
                usuario2.putString("usuario", usuario);

                Intent intent = new Intent(registroEventos.this, MuestraEventos.class);
                intent.putExtras(usuario2);
                startActivity(intent);

                //endregion


                limpiarPantalla();
            }else{
                Toast.makeText(registroEventos.this, "Usuario ya se encuentra registrado", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void grabarBaseDatos(Eventos event){
        try{
            AdministradorBaseDatos adbd = new AdministradorBaseDatos(this, "BDAplicacion", null, 1);
            SQLiteDatabase miBD = adbd.getWritableDatabase();

            //Forma android
            ContentValues reg = new ContentValues();
            reg.put("usuario", event.getUsuario());
            reg.put("titulo", event.getTitulo());
            reg.put("fecha", event.getFecha());
            reg.put("lugar", event.getLugar());
            reg.put("importancia", event.getImportancia());
            reg.put("observacion", event.getObservacion());

            miBD.insert("eventos", null, reg);

            miBD.close();
        }catch (Exception ex){
            Log.e("TAG_", ex.toString());
        }

        //consultaSQL();
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

    private void mostrarlista(){
        Intent pantallaLista = new Intent(this,MuestraEventos.class);
        startActivity(pantallaLista);

    }

    private void eventos() {
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabarEvento();

            }
        });

        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarlista();

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