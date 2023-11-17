package com.example.fragment;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieListAdapter extends ArrayAdapter<Movie> {

    private Activity context;
    private List<Movie> movies;

    public MovieListAdapter(Fragment context, List<Movie> movies) {
        super(context.getActivity(), R.layout.fragment_movie, movies);
        this.movies = movies;
        this.context=context.getActivity();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.list_item_movie, null);
        TextView lblDe = (TextView) item.findViewById(R.id.titleTextView);
        lblDe.setText(movies.get(position).getTitle());
        TextView lblAsunto = (TextView)item.findViewById(R.id.yearTextView);
        lblAsunto.setText(movies.get(position).getYear()+"");
        return (item);
    }

}
