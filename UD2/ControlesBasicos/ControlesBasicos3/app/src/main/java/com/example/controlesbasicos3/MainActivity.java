package com.example.controlesbasicos3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private EditText txtNum, txtLetra;
    private TextView txtValidar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNum = (EditText) findViewById(R.id.txtNum);
        txtLetra = (EditText) findViewById(R.id.txtLetra);
        txtValidar = (TextView) findViewById(R.id.txtValidar);
    }

    public void validar(View v){

        String[] asinacionL = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

        int num = Integer.parseInt(txtNum.getText().toString());
        int numL = num % 23;

        if (asinacionL[numL].equalsIgnoreCase(txtLetra.getText().toString())){
            txtValidar.setText("DNI Valido");
        }else {
            txtValidar.setText("DNI NO Valido");
        }


    }

}