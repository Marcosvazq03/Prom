package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieFragment.MovieListener {

    private RecyclerView recyclerView;
    private MovieListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieFragment fragmentListado = (MovieFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragLista);
        fragmentListado.setMovieListener(this);
    }

    @Override
    public void onMovieSeleccionado(Movie c) {
        boolean hayDetalle = (getSupportFragmentManager().
                findFragmentById(R.id.fragDetalle)!= null);
        if (hayDetalle) {
            ((MovieDetailFragment)getSupportFragmentManager().
                    findFragmentById(R.id.fragDetalle)).
                    mostrarDetalle(c.getDescription());
        }
        else {
            Intent i = new Intent(this, DetalleActivity.class);
            i.putExtra(DetalleActivity.EXTRA_TEXTO, c.getDescription());
            startActivity(i);
        }
    }
}