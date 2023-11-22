package com.example.notificaciones;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OperacionesActivity extends AppCompatActivity {

    private TextView txtSaludo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operaciones);

        //Localizar los controles
        txtSaludo = (TextView) findViewById(R.id.txtSaludo);
        //Recuperamos la información pasada en el intento
        Bundle bundle = this.getIntent().getExtras();
        //Construimos el mensaje a mostrar
        String mens="";
        if (bundle.getString("COMPR").equals("false")){
            mens="INCORRECTO";
        }else {
            mens="CORRECTO";
        }
        txtSaludo.setText("El resultado de la operacion es\n" + mens);
    }

    public void volver(View v){
        //Creamos el Intent
        Intent intent = new Intent( OperacionesActivity.this,
                MainActivity.class);

        //Iniciamos / lanzamos la nueva actividad
        startActivity(intent);
    }
}