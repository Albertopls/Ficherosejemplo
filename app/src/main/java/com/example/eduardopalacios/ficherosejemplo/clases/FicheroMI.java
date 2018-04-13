package com.example.eduardopalacios.ficherosejemplo.clases;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by eduardopalacios on 12/04/18.
 */

public class FicheroMI {

    String texto;
    Context context;

    String nombreFichero="Archivo1.txt";




    public FicheroMI(String texto,Context context) {

        this.texto = texto;
        this.context=context;
    }
    public FicheroMI(Context context)
    {
        this.context=context;
    }

    public void crearFichero()
    {
        FileOutputStream outputStream;

        try {

            outputStream=context.openFileOutput(nombreFichero,Context.MODE_PRIVATE);
            outputStream.write(texto.getBytes());
            outputStream.close();


        }catch (Exception e)
        {

        }
    }

    public String leerFichero()
    {
        FileInputStream file;
        String contenido="";
        try {

           file=context.openFileInput(nombreFichero);

           BufferedReader lector=new BufferedReader(new InputStreamReader(file));

            String line;


           while ((line=lector.readLine())!=null){

               contenido+=line;
           }

        }catch (Exception e)
        {

        }

        return contenido;
    }

    public  void eliminarFichero()
    {
        String path="/data/data/com.example.eduardopalacios.ficherosejemplo/files/";
        File archivo=new File(path,nombreFichero);
        archivo.delete();
    }
}
