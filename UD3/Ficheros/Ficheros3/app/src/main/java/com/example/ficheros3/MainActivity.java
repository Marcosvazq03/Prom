package com.example.ficheros3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        List<WebFavorita> listaWebs = cargarDatos();

        WebAdapter adapter = new WebAdapter(this, listaWebs);
        listView.setAdapter(adapter);
    }

    private List<WebFavorita> cargarDatos() {
        Resources res = getResources();
        String[] lineas = res.getStringArray(R.array.webs_favoritas);

        List<WebFavorita> listaWebs = new ArrayList<>();
        for (String linea : lineas) {
            String[] partes = linea.split(";");
            String nombre = partes[0];
            String enlace = partes[1];
            int logo = res.getIdentifier(partes[2], "drawable", getPackageName());
            String id = partes[3];

            listaWebs.add(new WebFavorita(nombre, enlace, logo, id));
        }
        return listaWebs;
    }
}