package com.example.dialogos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnAcceder, btnSalir;
    private AlertDialog.Builder dialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAcceder = findViewById(R.id.btnAcceder);
        btnSalir = findViewById(R.id.btnSalir);

        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoAcceso();
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoSalir();
            }
        });
    }

    private void mostrarDialogoAcceso() {
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Acceso");
        dialogBuilder.setMessage("Introduce el usuario y contraseña");

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_acceso, null);
        dialogBuilder.setView(dialogView);

        final EditText etUsuario = dialogView.findViewById(R.id.etUsuario);
        final EditText etContraseña = dialogView.findViewById(R.id.etContraseña);

        dialogBuilder.setPositiveButton("Acceder", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String usuario = etUsuario.getText().toString();
                String contraseña = etContraseña.getText().toString();

                if (usuario.equals("usuario1") && contraseña.equals("123456")) {
                    Toast.makeText(MainActivity.this, "Acceso concedido", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error de acceso", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    private void mostrarDialogoSalir() {
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Salir");
        dialogBuilder.setMessage("¿Estás seguro de que quieres salir?");

        dialogBuilder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        dialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
}