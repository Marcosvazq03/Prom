package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MovieDetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false);
    }
    public void mostrarDetalle (String texto) {
        TextView txtDetalle =
                (TextView)getView().findViewById(R.id.descriptionTextView);
        txtDetalle.setText(texto);
    }
}
