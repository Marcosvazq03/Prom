package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetalleActivity extends AppCompatActivity {

    public static final String EXTRA_TEXTO = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        MovieDetailFragment detalle = (MovieDetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragDetalle);
        detalle.mostrarDetalle(getIntent().getStringExtra(EXTRA_TEXTO));
    }
}