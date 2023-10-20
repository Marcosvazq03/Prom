package com.example.controlesbasicosii1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre, txtApe;
    private TextView txtCondiciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApe = (EditText) findViewById(R.id.txtApe);
        txtCondiciones = (TextView) findViewById(R.id.txtCondiciones);
        //Recuperamos la información pasada en el intento
        Bundle bundle = this.getIntent().getExtras();
        //Construimos el mensaje a mostrar
        if (bundle != null) {
            txtCondiciones.setText("Aceptas condiciones: " + bundle.getString("CONDICIONES"));
        }
    }

    public void verificar(View v){
        //Creamos el Intent
        Intent intent = new Intent( MainActivity.this,
                CondicionesActivity.class);

        //Creamos la información a pasar entre actividades
        Bundle b = new Bundle();
        b.putString("NOMBRE", txtNombre.getText().toString()+ " "+ txtApe.getText().toString());

        //Añadimos la información al intento
        intent.putExtras(b);

        //Iniciamos / lanzamos la nueva actividad
        startActivity(intent);
    }
}