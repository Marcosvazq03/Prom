package com.example.controlesbasicos2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {

    private LinearLayout layoutP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutP = (LinearLayout) findViewById(R.id.layoutP);

    }

    public void encender(View v){

        layoutP.setBackgroundColor(Color.RED);

    }

    public void apagar(View v){

        layoutP.setBackground(null);

    }
}