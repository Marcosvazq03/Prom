package com.example.controlesbasicos4;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgLogo = (ImageView) findViewById(R.id.imgLogo);
    }

    public void yahoo(View v){
        imgLogo.setImageResource(R.drawable.yahoo);
    }

    public void bing(View v){
        imgLogo.setImageResource(R.drawable.bing);
    }

    public void google(View v){
        imgLogo.setImageResource(R.drawable.google);
    }
}