package com.example.eduardopalacios.ficherosejemplo.clases;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by eduardopalacios on 12/04/18.
 */

public class FicheroME {

    boolean disponibleSD=false;
    boolean disponibleEscrituraSD=false;
    final String NOMBREFICHERO="fichero5.txt";

    String texto;
    Context context;

    public  FicheroME(String texto, Context context)
    {
        this.texto=texto;
        this.context=context;
    }
    public  FicheroME()
    {

    }

    public void crearFicheroSD()
    {
        comprobarMemoriaSD();
        if (disponibleSD && disponibleEscrituraSD)
        {


            try {
                File ruta=Environment.getExternalStorageDirectory();
                File file=new File(ruta.getAbsolutePath(),NOMBREFICHERO);

                OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream(file));
                writer.write(texto);
                writer.close();

            }catch (Exception e)
            {

                Log.d("tag",e.getMessage());
            }
        }
    }

    public  String leerFicheroSD()
    {
        String contenido="";
        File directorio=new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        File file= new File(directorio,NOMBREFICHERO);
        try {

            BufferedReader lector=new BufferedReader(new InputStreamReader(new FileInputStream(file)));

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
        File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath(),NOMBREFICHERO);

        file.delete();

    }
    public void comprobarMemoriaSD()
    {


        String estado= Environment.getExternalStorageState();

        if (estado.equals(Environment.MEDIA_MOUNTED))
        {
            disponibleSD=true;
            disponibleEscrituraSD=true;
        }
        else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
        {
            disponibleSD=true;
            disponibleEscrituraSD=false;
        }
        else {
            disponibleSD=false;
            disponibleEscrituraSD=false;

        }

    }
}
