package com.example.miseventos;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
        private TextInputLayout tilUsuario, tilPass;
        private Button btnIngresar, btnRegistrar;
        private TextView tvRecuperarpass;
        private ImageView ivAvatar;

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

    //region extraccion BD



    private void consultaSQL(){
        String usuario = tilUsuario.getEditText().getText().toString();
        String contrasena = tilPass.getEditText().getText().toString();
            
        AdministradorBaseDatos adbd = new AdministradorBaseDatos(this, "BDAplicacion", null, 1);
        SQLiteDatabase miBD = adbd.getWritableDatabase();
        try {
            Cursor c = miBD.rawQuery("Select * from usuarios order by username desc", null);
            if(c.moveToFirst()){
                Log.d("TAG_","Registros recuperados " + c.getCount());
                do{
                    Log.d("TAG_", "nombre de usuario " + c.getString(0) +
                            ", contrasena " + c.getString(1));

                    if(c.getString(0).equals(usuario) && c.getString(1).equals(contrasena)){
                        mostrarsegundaactividad();
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
    //region Eventos y referencias
    private void eventos(){
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultaSQL();
            }
        });
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
        ivAvatar = findViewById(R.id.ivAvatar);
    }}


