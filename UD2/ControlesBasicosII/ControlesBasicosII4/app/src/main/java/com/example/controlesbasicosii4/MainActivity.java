package com.example.controlesbasicosii4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txtMal;
    private EditText txtNombre, txtApellidos;
    private RadioButton rdMasculino, rdFemenino, rdOtro;
    private CheckBox cbMusica, cbLectura, cbDeportes, cbViajar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellidos = (EditText) findViewById(R.id.txtApellidos);
        rdMasculino = (RadioButton) findViewById(R.id.rdMasculino);
        rdFemenino = (RadioButton) findViewById(R.id.rdFemenino);
        rdOtro = (RadioButton) findViewById(R.id.rdOtro);
        cbMusica = (CheckBox) findViewById(R.id.cbMusica);
        cbLectura = (CheckBox) findViewById(R.id.cbLectura);
        cbDeportes = (CheckBox) findViewById(R.id.cbDeportes);
        cbViajar = (CheckBox) findViewById(R.id.cbViajar);
        txtMal = (TextView) findViewById(R.id.txtMal);
        txtMal.setTextColor(Color.RED);
    }

    public void enviar(View v){
        boolean mal = false;
        txtMal.setText("");
        if (txtNombre.getText().toString().equals("")){
            txtMal.setText("Es obligatorio introducir Nombre\n");
            mal=true;
        }
        if (txtApellidos.getText().toString().equals("")){
            txtMal.setText(txtMal.getText().toString()+"Es obligatorio introducir Apellidos\n");
            mal=true;
        }
        if (!rdMasculino.isChecked() && !rdFemenino.isChecked()){
            if (rdOtro.isChecked()){
                txtMal.setText(txtMal.getText().toString()+"Es obligatorio seleccionar un sexo Masculino o Femenino\n");
            }else {
                txtMal.setText(txtMal.getText().toString()+"Es obligatorio seleccionar un RadioButon\n");
            }
            mal=true;
        }
        if (mal==false){
            //Creamos el Intent
            Intent intent = new Intent( MainActivity.this,
                    VerActivity.class);

            //Creamos la información a pasar entre actividades
            Bundle b = new Bundle();
            b.putString("NOMBRE", txtNombre.getText().toString());
            b.putString("APELLIDOS", txtApellidos.getText().toString());
            if (rdMasculino.isChecked()){
                b.putString("SEXO", "Masculino");
            }
            if (rdFemenino.isChecked()){
                b.putString("SEXO", "Femenino");
            }
            if (cbMusica.isChecked()){
                b.putString("MUSICA", "Musica");
            }
            if (cbLectura.isChecked()){
                b.putString("LECTURA", "Lectura");
            }
            if (cbViajar.isChecked()){
                b.putString("VIAJAR", "Viajar");
            }
            if (cbDeportes.isChecked()){
                b.putString("DEPORTES", "Deportes");
            }
            //Añadimos la información al intento
            intent.putExtras(b);

            //Iniciamos / lanzamos la nueva actividad
            startActivity(intent);
        }
    }
}