package com.example.listaseintents2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Titular {

    private String titulo;
    private String subtitulo;
    private int img;
    private String id;
    public Titular(String tit, String sub, int img, String id){
        titulo = tit;
        subtitulo = sub;
        this.img=img;
        this.id=id;
    }
    public String getTitulo(){
        return titulo;
    }
    public String getSubtitulo(){
        return subtitulo;
    }
    public int getImg(){
        return img;
    }
    public String getId(){
        return id;
    }

}
class AdaptadorTitulares extends ArrayAdapter<Titular> {

    private Titular [] datos;
    public AdaptadorTitulares(@NonNull Context context, Titular[] datos) {
        super(context, R.layout.listitem_titular, datos);
        this.datos=datos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.listitem_titular, null);
        TextView tvTitulo = (TextView)item.findViewById(R.id.tvTitulo);
        tvTitulo.setText(datos[position].getTitulo());
        TextView tvSubtitulo = (TextView)item.findViewById(R.id.tvSubTitulo);
        tvSubtitulo.setText(datos[position].getSubtitulo());
        ImageView tvImg = (ImageView)item.findViewById(R.id.imgWeb);
        tvImg.setImageResource(datos[position].getImg());
        TextView tvId= (TextView)item.findViewById(R.id.tvID);
        tvId.setText(datos[position].getId());
        return (item);
    }
}
