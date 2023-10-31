package com.example.listaseintents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spOpciones;
    private TextView txtTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spOpciones = (Spinner) findViewById(R.id.spElementos);
        txtTexto = (TextView) findViewById(R.id.txtTexto);

        //Creamos el Array
        final String[] datos = new String [] {"España" ,"Portugal",
                "Francia", "Alemania", "Rusia", "Ucrania"} ;
        //Creamos el adaptador para el spinner
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);

        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spOpciones.setAdapter(adaptador);

        //Eventos ratón para el spinner de los dias de la semna
        spOpciones.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int position, long id) {
        TextView tvMensaje=(TextView) view;
        txtTexto.setText("Has seleccionado " + tvMensaje.getText()
                + " en la posición "+ position);
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        txtTexto.setText("No se ha seleccionado ninguna opción");
    }
}