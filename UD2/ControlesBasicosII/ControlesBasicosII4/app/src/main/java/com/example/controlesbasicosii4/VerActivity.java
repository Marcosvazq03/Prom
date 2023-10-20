package com.example.controlesbasicosii4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class VerActivity extends AppCompatActivity {

    private TextView txtNombre, txtApellido, txtSexo, txtAficiones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        //Localizar los controles
        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtAficiones = (TextView) findViewById(R.id.txtAficiones);
        txtApellido = (TextView) findViewById(R.id.txtApellidos);
        txtSexo = (TextView) findViewById(R.id.txtSexo);
        //Recuperamos la información pasada en el intento
        Bundle bundle = this.getIntent().getExtras();
        //Construimos el mensaje a mostrar
        txtNombre.setText("Nombre: " + bundle.getString("NOMBRE"));
        txtApellido.setText("Apellidos: " + bundle.getString("APELLIDOS"));
        txtSexo.setText("Sexo: " + bundle.getString("SEXO"));
        txtAficiones.setText("Aficiones: \n");
        if (bundle.getString("MUSICA")!=null){
            txtAficiones.setText(txtAficiones.getText().toString()+bundle.getString("MUSICA")+"\n");
        }
        if (bundle.getString("LECTURA")!=null){
            txtAficiones.setText(txtAficiones.getText().toString()+bundle.getString("LECTURA")+"\n");
        }
        if (bundle.getString("DEPORTES")!=null){
            txtAficiones.setText(txtAficiones.getText().toString()+bundle.getString("DEPORTES")+"\n");
        }
        if (bundle.getString("VIAJAR")!=null){
            txtAficiones.setText(txtAficiones.getText().toString()+bundle.getString("VIAJAR")+"\n");
        }
    }

    public void volver(View v){
        //Creamos el Intent
        Intent intent = new Intent( VerActivity.this,
                MainActivity.class);

        //Iniciamos / lanzamos la nueva actividad
        startActivity(intent);
    }

}