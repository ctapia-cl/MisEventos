package com.example.miseventos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdministradorBaseDatos extends SQLiteOpenHelper {
    public AdministradorBaseDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuarios (username text primary key, contrasena text, pregunta text, respuesta text)");
        db.execSQL("create table ultimoUsuario (usuario text primary key)");
        db.execSQL("create table eventos (usuario text primary key, titulo text, fecha text, lugar text, importancia text, observacion text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL("DROP TABLE IF EXISTS usuarios" );
     db.execSQL("DROP TABLE IF EXISTS ultimoUsuario" );
     db.execSQL("DROP TABLE IF EXISTS eventos" );
     onCreate(db);
    }
}
