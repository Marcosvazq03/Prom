package com.example.ficheros1;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    String filename = "mi_archivo.txt";
    EditText textInput;
    TextView textOutput;
    private static final int WRITE_EXTERNAL_STORAGE_CODE = 1;
    private static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        textInput = findViewById(R.id.editTextE);
        textOutput = findViewById(R.id.textViewE);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_CODE);
        }

        Button button = findViewById(R.id.button);
        final TextView textViewR = findViewById(R.id.textViewR);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputStream inputStream = getResources().openRawResource(R.raw.mi_archivo);
                    Scanner scanner = new Scanner(inputStream);
                    StringBuilder stringBuilder = new StringBuilder();
                    while (scanner.hasNextLine()) {
                        stringBuilder.append(scanner.nextLine());
                    }
                    textViewR.setText(stringBuilder.toString());
                } catch (Exception e) {
                    textViewR.setText("Ha ocurrido un error al leer el archivo.");
                    e.printStackTrace();
                }
            }
        });
    }

    public void addContent(View view) {
        String text = editText.getText().toString();
        FileOutputStream fos;

        try {
            fos = openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
            Toast.makeText(this, "El contenido se ha añadido al archivo", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(View view) {
        File dir = getFilesDir();
        File file = new File(dir, filename);
        boolean deleted = file.delete();

        if(deleted)
            Toast.makeText(this, "Archivo eliminado", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "El archivo no se pudo eliminar", Toast.LENGTH_SHORT).show();
    }

    public void viewContent(View view) {
        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    openFileInput(filename)));
            String inputString;
            StringBuilder stringBuffer = new StringBuilder();
            while ((inputString = inputReader.readLine()) != null) {
                stringBuffer.append(inputString + "\n");
            }

            textView.setText(stringBuffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveText(View view) {
        String text = textInput.getText().toString();
        try {
            File file = new File(getExternalFilesDir(null), "myFile.txt");
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(text);
            osw.close();
            fos.close();
            Toast.makeText(this, "Texto guardado en el fichero", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFileE(View view) {
        File file = new File(getExternalFilesDir(null), "myFile.txt");
        if (file.exists()) {
            file.delete();
            Toast.makeText(this, "Fichero borrado", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "No existe fichero", Toast.LENGTH_SHORT).show();
        }
    }

    public void readFile(View view) {
        try {
            File file = new File(getExternalFilesDir(null), "myFile.txt");
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            int charRead;
            String s = "";
            while ((charRead = isr.read(inputBuffer)) > 0) {
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                s += readString;
            }
            isr.close();
            fis.close();
            textOutput.setText(s);
            Toast.makeText(this, "Texto leído del fichero", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
