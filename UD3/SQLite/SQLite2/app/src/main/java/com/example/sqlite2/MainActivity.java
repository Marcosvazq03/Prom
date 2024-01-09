package com.example.sqlite2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ListView listaLibros, listaUsuarios;
    private ArrayAdapter<String> adapterLibros, adapterUsuarios;
    private ArrayList<String> libros, usuarios;
    private EditText codLibro, tituloLibro, autorLibro, idUsuario, nombreUsuario;
    private Button btnAgregarLibro, btnAgregarUsuario, btnPrestarLibro, btnEliminarLibro, btnMostrarLibros, btnMostrarUsuarios;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar las vistas
        codLibro = findViewById(R.id.codLibro);
        tituloLibro = findViewById(R.id.tituloLibro);
        autorLibro = findViewById(R.id.autorLibro);
        idUsuario = findViewById(R.id.idUsuario);
        nombreUsuario = findViewById(R.id.nombreUsuario);
        btnAgregarLibro = findViewById(R.id.btnAgregarLibro);
        btnAgregarUsuario = findViewById(R.id.btnAgregarUsuario);
        btnPrestarLibro = findViewById(R.id.btnPrestarLibro);
        btnEliminarLibro = findViewById(R.id.btnEliminarLibro);
        btnMostrarLibros = findViewById(R.id.btnMostrarLibros);
        btnMostrarUsuarios = findViewById(R.id.btnMostrarUsuarios);
        listaLibros = findViewById(R.id.listaLibros);
        listaUsuarios = findViewById(R.id.listaUsuarios);

        // Inicializar los ArrayList y los ArrayAdapter
        libros = new ArrayList<>();
        usuarios = new ArrayList<>();
        adapterLibros = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, libros);
        adapterUsuarios = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, usuarios);

        // Asignar los ArrayAdapter a los ListView
        listaLibros.setAdapter(adapterLibros);
        listaUsuarios.setAdapter(adapterUsuarios);

        // Manejar las acciones de los botones
        btnAgregarLibro.setOnClickListener(v -> {
            int cod = Integer.parseInt(codLibro.getText().toString());
            String titulo = tituloLibro.getText().toString();
            String autor = autorLibro.getText().toString();
            agregarLibro(cod, titulo, autor, 0);
        });

        btnAgregarUsuario.setOnClickListener(v -> {
            int id = Integer.parseInt(idUsuario.getText().toString());
            String nombre = nombreUsuario.getText().toString();
            agregarUsuario(id, nombre, 0);
        });

        btnPrestarLibro.setOnClickListener(v -> {
            int cod = Integer.parseInt(codLibro.getText().toString());
            int id = Integer.parseInt(idUsuario.getText().toString());
            prestarLibro(cod, id);
        });

        btnEliminarLibro.setOnClickListener(v -> {
            int cod = Integer.parseInt(codLibro.getText().toString());
            eliminarLibro(cod);
        });

        btnMostrarLibros.setOnClickListener(v -> mostrarLibros());
        btnMostrarUsuarios.setOnClickListener(v -> mostrarUsuarios());

        dbHelper = new DBHelper(this);

        // Ejemplo de uso de las funcionalidades
        agregarLibro(1, "Libro 1", "Autor 1", 0);
        agregarLibro(2, "Libro 2", "Autor 2", 0);
        agregarUsuario(1, "Usuario 1", 0);

        prestarLibro(1, 1);

    }

    private void agregarLibro(int cod, String titulo, String autor, int prestado) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Cod", cod);
        values.put("Titulo", titulo);
        values.put("Autor", autor);
        values.put("Prestado", prestado);
        db.insert("libro", null, values);
        Toast.makeText(getApplicationContext(), "Libro agregado: " + titulo, Toast.LENGTH_SHORT).show();
    }

    private void agregarUsuario(int id, String nombre, int libroPrestado) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Id", id);
        values.put("Nombre", nombre);
        values.put("Libro_prestado", libroPrestado);
        db.insert("usuario", null, values);
        Toast.makeText(getApplicationContext(), "Usuario agregado: " + nombre, Toast.LENGTH_SHORT).show();
    }

    private void prestarLibro(int codLibro, int idUsuario) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Prestado", 1);
        db.update("libro", values, "Cod = ?", new String[]{String.valueOf(codLibro)});
        values.clear();
        values.put("Libro_prestado", codLibro);
        db.update("usuario", values, "Id = ?", new String[]{String.valueOf(idUsuario)});
        Toast.makeText(getApplicationContext(), "Libro prestado: " + codLibro + " a Usuario: " + idUsuario, Toast.LENGTH_SHORT).show();
    }

    private void eliminarLibro(int cod) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("libro", "Cod = ?", new String[]{String.valueOf(cod)});
        Toast.makeText(getApplicationContext(), "Libro eliminado: " + cod, Toast.LENGTH_SHORT).show();
    }

    private void mostrarLibros() {
        libros.clear();
        usuarios.clear();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM libro", null);
        if (cursor.moveToFirst()) {
            do {
                int cod = cursor.getInt(cursor.getColumnIndexOrThrow("Cod"));
                String titulo = cursor.getString(cursor.getColumnIndexOrThrow("Titulo"));
                String autor = cursor.getString(cursor.getColumnIndexOrThrow("Autor"));
                int prestado = cursor.getInt(cursor.getColumnIndexOrThrow("Prestado"));
                String libro = "Libro: " + cod + ", " + titulo + ", de " + autor + ", prestado: " + prestado;
                libros.add(libro);
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapterLibros.notifyDataSetChanged();
        adapterUsuarios.notifyDataSetChanged();
    }

    private void mostrarUsuarios() {
        usuarios.clear();
        libros.clear();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM usuario", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("Id"));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow("Nombre"));
                int libroPrestado = cursor.getInt(cursor.getColumnIndexOrThrow("Libro_prestado"));
                String usuario = "Usuario: " + id + ", " + nombre + ", ID libro prestado:" + libroPrestado;
                usuarios.add(usuario);
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapterUsuarios.notifyDataSetChanged();
        adapterLibros.notifyDataSetChanged();
    }
}