package com.example.sqlite2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "biblioteca.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla "libro"
        db.execSQL("CREATE TABLE libro (Cod INTEGER PRIMARY KEY, Titulo TEXT, Autor TEXT, Prestado INTEGER)");

        // Crear la tabla "usuario"
        db.execSQL("CREATE TABLE usuario (Id INTEGER PRIMARY KEY, Nombre TEXT, Libro_prestado INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Eliminar las tablas existentes si hay una actualización de la base de datos
        db.execSQL("DROP TABLE IF EXISTS libro");
        db.execSQL("DROP TABLE IF EXISTS usuario");
        onCreate(db);
    }
}
