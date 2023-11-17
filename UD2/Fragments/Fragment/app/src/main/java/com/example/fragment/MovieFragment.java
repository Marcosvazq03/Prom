package com.example.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MovieFragment extends Fragment {

    private MovieListener listener;
    private List<Movie> movies;
    private ListView lista;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        movies = new ArrayList<>();
        movies.add(new Movie("Pelicula 1", "Descripción de la película 1", 2020));
        movies.add(new Movie("Pelicula 2", "Descripción de la película 2", 2019));
        movies.add(new Movie("Pelicula 3", "Descripción de la película 3", 2018));
        lista =(ListView) getView().findViewById(R.id.list);

        lista.setAdapter(new MovieListAdapter(this, movies));

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null)
                    listener.onMovieSeleccionado(
                            (Movie)lista.getAdapter().getItem(position));
            }
        });
    }

    public interface MovieListener{
        void onMovieSeleccionado(Movie c);
    }
    public void setMovieListener (MovieListener listener){
        this.listener =listener;
    }
}