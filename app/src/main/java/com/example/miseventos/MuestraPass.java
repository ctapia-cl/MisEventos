package com.example.miseventos;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MuestraPass extends AppCompatActivity {

    private Button btnVolver;
    private TextView tvMuestraText, tvContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_pass);

        referencias();
        consultaSQL();
        eventos();
    }

    //region extraccion BD

    private void consultaSQL(){
        Bundle recibeRespuesta = getIntent().getExtras();
        String infoRespuesta = recibeRespuesta.getString("respuesta");

        AdministradorBaseDatos adbd = new AdministradorBaseDatos(this, "BDAplicacion", null, 1);
        SQLiteDatabase miBD = adbd.getWritableDatabase();
        try {
            Cursor c = miBD.rawQuery("Select * from usuarios order by username desc", null);
            if(c.moveToFirst()){
                Log.d("TAG_","Registros recuperados " + c.getCount());
                do{
                    Log.d("TAG_", "respuesta " + c.getString(3) +
                            ", contrasena " + c.getString(1));

                    if(c.getString(3).equals(infoRespuesta) && ){
                        tvContrasena.setText(c.getString(1));

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
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void referencias() {

        tvContrasena = findViewById(R.id.tvContrasena);
        btnVolver = findViewById(R.id.btnVolver);


    }
}