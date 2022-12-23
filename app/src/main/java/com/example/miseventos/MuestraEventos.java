package com.example.miseventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MuestraEventos extends AppCompatActivity {

    private ListView lvEventos;
    private Button btnVolver2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_eventos);

        referencias();
        eventos();
       // consultaSQL();
    }






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