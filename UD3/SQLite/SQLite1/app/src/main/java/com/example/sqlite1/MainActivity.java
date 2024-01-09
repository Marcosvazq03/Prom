package com.example.sqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private Button btnAgregar, btnEliminar, btnActualizar, btnConsultar;
    private EditText etId, etNombre, etTelefono;
    private ArrayList<String> contactos;
    private ListView listaContactos;
    private ArrayAdapter<String> adapterContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAgregar = findViewById(R.id.btnAgregar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnConsultar = findViewById(R.id.btnConsultar);
        etId = findViewById(R.id.etId);
        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);
        listaContactos = findViewById(R.id.listaContactos);

        contactos = new ArrayList<>();
        adapterContactos = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactos);

        listaContactos.setAdapter(adapterContactos);

        btnAgregar.setOnClickListener(v -> {
            int id = Integer.parseInt(etId.getText().toString());
            String nombre = etNombre.getText().toString();
            int telefono = Integer.parseInt(etTelefono.getText().toString());
            agregarContacto(id, nombre, telefono);
        });

        btnActualizar.setOnClickListener(v -> {
            int id = Integer.parseInt(etId.getText().toString());
            String nombre = etNombre.getText().toString();
            int telefono = Integer.parseInt(etTelefono.getText().toString());
            actualizarContacto(id, nombre, telefono);
        });

        btnEliminar.setOnClickListener(v -> {
            int id = Integer.parseInt(etId.getText().toString());
            eliminarContacto(id);
        });

        btnConsultar.setOnClickListener(v -> consultarContacto());

        dbHelper = new DatabaseHelper(this);
    }

    public void agregarContacto(int id, String nombre, int telefono) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("nombre", nombre);
        values.put("telefono", telefono);
        db.insert("contactos", null, values);
        Toast.makeText(getApplicationContext(), "Contacto agregado: " + nombre, Toast.LENGTH_SHORT).show();
    }

    public void actualizarContacto(int id, String nombre, int telefono) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("telefono", telefono);
        db.update("contactos", values, "id = ?", new String[]{String.valueOf(id)});
        Toast.makeText(getApplicationContext(), "Contacto actualizado: " + nombre, Toast.LENGTH_SHORT).show();
    }

    public void eliminarContacto(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("contactos", "id = ?", new String[]{String.valueOf(id)});
        Toast.makeText(getApplicationContext(), "contacto eliminado con id: " + id, Toast.LENGTH_SHORT).show();
    }

    public void consultarContacto() {
        contactos.clear();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM contactos", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
                int telefono = cursor.getInt(cursor.getColumnIndexOrThrow("telefono"));
                String contacto = "Contacto: " + id + ", " + nombre + ", tlf: " + telefono ;
                contactos.add(contacto);
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapterContactos.notifyDataSetChanged();
    }
}