package com.example.eduardopalacios.ficherosejemplo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eduardopalacios.ficherosejemplo.clases.FicheroME;
import com.example.eduardopalacios.ficherosejemplo.clases.FicheroMI;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int requestCode;
    private int grantResults[];

    EditText texto;
    FicheroMI ficheroMI;
    FicheroME ficheroME;
    Button btnGuardarInterno,btnEliminarInterno,btnGuardarExterno,btnEliminarExterno,btnMostrarFicheroInterno,btnMostratFicheroExterno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texto = findViewById(R.id.EtArchivo);

        btnGuardarInterno = findViewById(R.id.btnGuardar);
        btnGuardarInterno.setOnClickListener(this);

        btnEliminarInterno = findViewById(R.id.btnEliminar);
        btnEliminarInterno.setOnClickListener(this);

        btnGuardarExterno = findViewById(R.id.btnGuardarSD);
        btnGuardarExterno.setOnClickListener(this);

        btnEliminarExterno = findViewById(R.id.btnEliminarSD);
        btnEliminarExterno.setOnClickListener(this);

        btnMostrarFicheroInterno = findViewById(R.id.btnMostrar1);
        btnMostrarFicheroInterno.setOnClickListener(this);

        btnMostratFicheroExterno = findViewById(R.id.btnMostrar2);
        btnMostratFicheroExterno.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            //if you dont have required permissions ask for it (only required for API 23+)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestCode);


            onRequestPermissionsResult(requestCode, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, grantResults);


        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnGuardar:

                ficheroMI=new FicheroMI(texto.getText().toString(),this);
                ficheroMI.crearFichero();

                break;

            case R.id.btnEliminar:
                ficheroMI=new FicheroMI(this);
                ficheroMI.eliminarFichero();

                break;

            case R.id.btnMostrar1:

                ficheroMI=new FicheroMI(this);
                texto.setText(ficheroMI.leerFichero());

                break;

            case R.id.btnGuardarSD:
                ficheroME=new FicheroME(texto.getText().toString(),this);
                ficheroME.crearFicheroSD();

                break;
            case R.id.btnEliminarSD:

                ficheroME=new FicheroME();
                ficheroME.eliminarFichero();
                break;

            case R.id.btnMostrar2:

                ficheroME=new FicheroME();
                texto.setText(ficheroME.leerFicheroSD());
                break;

                default:

                    break;
        }

    }

    @Override // android recommended class to handle permissions
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.d("permission", "granted");
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.uujm
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();

                    //app cannot function without this permission for now so close it...
                    onDestroy();
                }
                return;
            }

            // other 'case' line to check fosr other
            // permissions this app might request
        }
    }
}
