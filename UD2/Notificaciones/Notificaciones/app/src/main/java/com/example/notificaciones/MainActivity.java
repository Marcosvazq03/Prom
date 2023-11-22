package com.example.notificaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txtNum1, txtNum2, txtResp;
    private TextView txtPuntuacion;
    private static int bien,mal,num1,num2;
    private static boolean compr=true;
    private static final String CHANNEL_ID = "channel_id";
    private static final String CHANNEL_NAME = "channel_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNum1 = (EditText) findViewById(R.id.txtNum1);
        txtNum2 = (EditText) findViewById(R.id.txtNum2);
        txtResp = (EditText) findViewById(R.id.txtResp);
        if (compr){
            num1=(int)(Math.random()*100);
            num2=(int)(Math.random()*100);
        }
        txtNum1.setText(""+num1);
        txtNum2.setText(""+num2);
        txtPuntuacion = (TextView) findViewById(R.id.txtPuntuacion);
        txtPuntuacion.setText("PREGUNTAS CORRECTAS:"+bien+" INCORRECTAS:"+mal);
    }

    public void verificar(View v){
        int resp = Integer.parseInt(txtNum1.getText().toString()) + Integer.parseInt(txtNum2.getText().toString());

        if (txtResp.getText().toString().equals("")){
            txtResp.setText("0");
        }
        if (resp == Integer.parseInt(txtResp.getText().toString())){
            compr = true;
            bien=bien+1;
            if (bien==10){
                showNotification();
            }
        }else {
            compr = false;
            mal=mal+1;
        }
        //Creamos el Intent
        Intent intent = new Intent( MainActivity.this,
                OperacionesActivity.class);

        //Creamos la información a pasar entre actividades
        Bundle b = new Bundle();
        b.putString("COMPR", compr+"");

        //Añadimos la información al intento
        intent.putExtras(b);

        //Iniciamos / lanzamos la nueva actividad
        startActivity(intent);

    }

    private void showNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        Notification.Builder builder = new Notification.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Notificación")
                .setContentText("Has acertado 10 vez!")
                .setAutoCancel(true);

        notificationManager.notify(0, builder.build());
    }
}