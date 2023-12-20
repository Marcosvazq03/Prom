package com.example.ficheros3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class WebAdapter extends ArrayAdapter<WebFavorita> {

    public WebAdapter(Context context, List<WebFavorita> webs) {
        super(context, 0, webs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WebFavorita web = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.web_item, parent, false);
        }

        TextView nombreView = convertView.findViewById(R.id.nombre);
        TextView enlaceView = convertView.findViewById(R.id.enlace);
        ImageView logoView = convertView.findViewById(R.id.logo);

        nombreView.setText(web.getNombre());
        enlaceView.setText(web.getEnlace());
        logoView.setImageResource(web.getLogo());

        return convertView;
    }
}
