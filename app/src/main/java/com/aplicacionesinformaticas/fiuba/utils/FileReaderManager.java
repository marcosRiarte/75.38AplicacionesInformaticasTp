package com.aplicacionesinformaticas.fiuba.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Gregorio on 7/21/2017.
 */

public class FileReaderManager {

    private static FileReaderManager instance;
    private Context context;

    private  FileReaderManager(Context context){
        this.context = context;
    }

    public static FileReaderManager getInstance(Context context){
        if (instance == null){
            instance = new FileReaderManager(context);
        } else {
          instance.setContext(context);
        }

        return instance;
    }

    public void setContext(Context context) {
        this.context = context;

    }

    public String getPlatosGuardados(){
        return getStoredJson("platos.txt");
    };

    public String getIngredientesGuardados(){
        return getStoredJson("ingredientes.txt");
    };

    public String getUsuarioGuardado(){
        return getStoredJson("usuario.txt");
    };

    private String getStoredJson(String filename){
        String resultado = "";
        BufferedReader reader = null;
        try {
            InputStreamReader iReader = new InputStreamReader(context.getAssets().open(filename), "UTF-8");
            reader = new BufferedReader(iReader);

            // do reading, usually loop until end of file reading
            String line;
            while ((line = reader.readLine()) != null) {
                resultado = resultado + line;
            }
        } catch (Exception e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }

        return resultado;
    }
}
