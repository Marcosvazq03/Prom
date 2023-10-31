package com.example.listaseintents2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ListView lvLista;
    private Titular [] datosTitular = new Titular[] {
            new Titular("Titulo 1", "Subtitulo 1",R.drawable.youtube,"1"),
            new Titular("Titulo 2", "Subtitulo 2",R.drawable.twich,"2"),
            new Titular("Titulo 3", "Subtitulo 3",R.drawable.instagram,"3")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Adaptador
        AdaptadorTitulares adaptadorTitulares =
                new AdaptadorTitulares(this, datosTitular);
        lvLista = (ListView) findViewById(R.id.lvLista);
        lvLista.setAdapter(adaptadorTitulares);
    }

}