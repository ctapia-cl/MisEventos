package com.example.miseventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MuestraEventos extends AppCompatActivity {

    private ListView lvEventos;
    private Button btnVolver2;
    private String usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_eventos);

        referencias();
        eventos();
        consultaUsuario();

        ArrayList<Eventos> listaeventos = new ArrayList<Eventos>();
        Eventos evento;

        AdministradorBaseDatos adbd = new AdministradorBaseDatos(this, "BDAplicacion", null, 1);
        SQLiteDatabase miBD = adbd.getWritableDatabase();

        Cursor c = miBD.rawQuery("Select * from Eventos order by id", null);

            if (c.moveToFirst()) {
                do {
                        evento = new Eventos(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5),c.getString(6));
                        Log.d("TAG_","get 0 " + c.getCount());
                        if(c.getString(1).equals(usuario)) {
                            listaeventos.add(evento);
                        }
                } while (c.moveToNext());
            }
            ArrayAdapter<Eventos> adapter = new ArrayAdapter<Eventos>(getApplicationContext(), android.R.layout.simple_list_item_1, listaeventos);
            lvEventos.setAdapter(adapter);
        }


    //region mostrar ultimo usuario
    private void consultaUsuario(){

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
    }
    //endregion

    //region extraccion BD

    private void consultaSQL(){

        AdministradorBaseDatos adbd = new AdministradorBaseDatos(this, "BDAplicacion", null, 1);
        SQLiteDatabase miBD = adbd.getWritableDatabase();
        try {
            Cursor c = miBD.rawQuery("Select * from Eventos", null);
            if(c.moveToFirst()){
                Log.d("TAG_","Registros recuperados " + c.getCount());
                do{
                    Log.d("TAG_", "nombre de usuario " + c.getString(0) +
                            ", contrasena " + c.getString(1));

                    if(c.getString(0).equals(usuario)){
                        Log.d("TAG_", "nombre de usuario  " + usuario);



                    }
                }while(c.moveToNext());
            }
        }catch (Exception ex){
            Log.e("TAG_", ex.toString());
        }finally {
            miBD.close();
        }
    }
//endregion


    private void eventos(){
        btnVolver2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void referencias() {

        lvEventos = findViewById(R.id.lvEventos);
        btnVolver2 = findViewById(R.id.btnVolver2);


    }
}