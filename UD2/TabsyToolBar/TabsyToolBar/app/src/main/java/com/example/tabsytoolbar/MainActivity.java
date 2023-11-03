package com.example.tabsytoolbar;


import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TabHost;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private Menu menu;

    private ListView lvLista,lvLista2,lvLista3;
    private Titular [] datosTitular1 = new Titular[] {
            new Titular("Titulo 1", "Subtitulo 1",R.drawable.ic_launcher_background,"1"),
            new Titular("Titulo 2", "Subtitulo 2",R.drawable.ic_launcher_background,"2"),
            new Titular("Titulo 3", "Subtitulo 3",R.drawable.ic_launcher_background,"3")};

    private Titular [] datosTitular2 = new Titular[] {
            new Titular("Titulo 1", "Subtitulo 1",R.drawable.ic_launcher_background,"1"),
            new Titular("Titulo 2", "Subtitulo 2",R.drawable.ic_launcher_background,"2")};

    private Titular [] datosTitular3 = new Titular[] {
            new Titular("Titulo 4", "Subtitulo 4",R.drawable.ic_launcher_background,"1"),
            new Titular("Titulo 5", "Subtitulo 5",R.drawable.ic_launcher_background,"2"),
            new Titular("Titulo 6", "Subtitulo 6",R.drawable.ic_launcher_background,"3")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adaptador1
        AdaptadorTitulares adaptadorTitulares =
                new AdaptadorTitulares(this, datosTitular1);
        lvLista = (ListView) findViewById(R.id.lvLista);
        lvLista.setAdapter(adaptadorTitulares);

        //Adaptador2
        AdaptadorTitulares adaptadorTitulares2 =
                new AdaptadorTitulares(this, datosTitular2);
        lvLista2 = (ListView) findViewById(R.id.lvLista2);
        lvLista2.setAdapter(adaptadorTitulares2);

        //Adaptador3
        AdaptadorTitulares adaptadorTitulares3 =
                new AdaptadorTitulares(this, datosTitular3);
        lvLista3 = (ListView) findViewById(R.id.lvLista3);
        lvLista3.setAdapter(adaptadorTitulares3);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Resources res = getResources();
        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Llamadas");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Chats");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("mitab3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Contactos");
        tabs.addTab(spec);

        tabs.setCurrentTab(0);
        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                Log.i("AndroidTabsDemo","Pulsada pestaña: "+s);

                if (s.equals("mitab1")){
                    menu.getItem(1).setIcon(R.drawable.ic_otro);
                }

                if (s.equals("mitab2")){
                    menu.getItem(1).setIcon(R.drawable.ic_otro2);
                }

                if (s.equals("mitab3")){
                    menu.getItem(1).setIcon(R.drawable.ic_otro3);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        this.menu = menu;
        return true;
    }

}