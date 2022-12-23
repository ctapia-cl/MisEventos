package com.example.miseventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RecuperarPass extends AppCompatActivity {

    private EditText etRespuesta;
    private Button btnVolver, btnEnviar;
    private TextView tvPreguntaSecreta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_pass);



        referencias();
        consultaSQL();
        eventos();
    }

    //region extraccion BD



    private void consultaSQL(){
        Bundle recibeUsuario = getIntent().getExtras();
        String infoUsuario = recibeUsuario.getString("usuario");

        AdministradorBaseDatos adbd = new AdministradorBaseDatos(this, "BDAplicacion", null, 1);
        SQLiteDatabase miBD = adbd.getWritableDatabase();
        try {
            Cursor c = miBD.rawQuery("Select * from usuarios order by username desc", null);
            if(c.moveToFirst()){
                Log.d("TAG_","Registros recuperados " + c.getCount());
                do{
                    Log.d("TAG_", "nombre de usuario " + c.getString(0) +
                            ", contrasena " + c.getString(1));

                    if(c.getString(0).equals(infoUsuario)){
                        tvPreguntaSecreta.setText(c.getString(2));

                    }
                }while(c.moveToNext());
            }/*else if (c.getString(0) != (infoUsuario)){
                Toast.makeText(RecuperarPass.this, "Usuario no se encuentra registrado", Toast.LENGTH_LONG).show();
                finish();
            }*/
        }catch (Exception ex){
            Log.e("TAG_", ex.toString());
        }finally {
            miBD.close();
        }
    }
//endregion

    private void mostrarpass(){
        Intent muestrapass = new Intent(this,MuestraPass.class);
        startActivity(muestrapass);

    }
    //region Eventos y referencias
    private void eventos(){
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //region Bundle de Usuario

                Bundle recibeUsuario = getIntent().getExtras();
                String infoUsuario = recibeUsuario.getString("usuario");

                Bundle entregausuario = new Bundle();
                entregausuario.putString("usuario", infoUsuario);

                //endregion

                Bundle respuesta = new Bundle();
                respuesta.putString("respuesta", etRespuesta.getText().toString());

                Intent intent = new Intent(RecuperarPass.this, MuestraPass.class);

                intent.putExtras(respuesta);
                intent.putExtras(entregausuario);

                startActivity(intent);



               // mostrarpass();
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

        tvPreguntaSecreta = findViewById(R.id.tvContrasena);
        etRespuesta = findViewById(R.id.etRespuesta);
        btnVolver = findViewById(R.id.btnVolver);
        btnEnviar = findViewById(R.id.btnEnviar);

    }
}