package com.example.controlesbasicosii1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CondicionesActivity extends AppCompatActivity {

    private TextView txtSaludo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condiciones);

        //Localizar los controles
        txtSaludo = (TextView) findViewById(R.id.txtSaludo);
        //Recuperamos la información pasada en el intento
        Bundle bundle = this.getIntent().getExtras();
        //Construimos el mensaje a mostrar
        txtSaludo.setText("Hola " + bundle.getString("NOMBRE") + " ¿Aceptas las condiciones?");
    }

    public void aceptado(View v){
        //Creamos el Intent
        Intent intent = new Intent( CondicionesActivity.this,
                MainActivity.class);

        //Creamos la información a pasar entre actividades
        Bundle b = new Bundle();
        b.putString("CONDICIONES", "ACEPTADO");

        //Añadimos la información al intento
        intent.putExtras(b);

        //Iniciamos / lanzamos la nueva actividad
        startActivity(intent);
    }

    public void rechazado(View v){
        //Creamos el Intent
        Intent intent = new Intent( CondicionesActivity.this,
                MainActivity.class);

        //Creamos la información a pasar entre actividades
        Bundle b = new Bundle();
        b.putString("CONDICIONES", "RECHAZADO");

        //Añadimos la información al intento
        intent.putExtras(b);

        //Iniciamos / lanzamos la nueva actividad
        startActivity(intent);
    }

}
