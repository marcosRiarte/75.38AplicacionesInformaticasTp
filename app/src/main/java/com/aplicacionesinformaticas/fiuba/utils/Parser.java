package com.aplicacionesinformaticas.fiuba.utils;

import android.content.Context;

import com.aplicacionesinformaticas.fiuba.model.Plato;
import com.aplicacionesinformaticas.fiuba.model.User;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Gregorio on 7/21/2017.
 */

public class Parser {
    private static Parser instance;
    private Context context;

    private Parser(Context context){
        this.context = context;
    }

    public static Parser getInstance(Context context){
        if (instance == null) {
            instance = new Parser(context);
        } else {
            instance.setContext(context);
        }

        return instance;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Plato> getPlatosArrayList(String json){
        Gson gson = new Gson();
        PlatosContainer platosContainer =  gson.fromJson(json, PlatosContainer.class);

        return platosContainer.platos;
    }

    class PlatosContainer{
        ArrayList<Plato> platos;

        public PlatosContainer(){

        }
    }
}
