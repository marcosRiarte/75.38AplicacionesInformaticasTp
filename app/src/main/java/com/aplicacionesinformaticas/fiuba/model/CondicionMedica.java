package com.aplicacionesinformaticas.fiuba.model;

/*Clase que representa la condicio medica de un cliente*/

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;


public class CondicionMedica {

    public enum CONDICION {
        DIABETES, HIPERTENSION, HIPOTENSION, CELIACO
    }
    private HashMap<CONDICION, String> descripciones;
    private CONDICION tipo;

    public CondicionMedica (CONDICION tipo){
        this.tipo = tipo;
        //llenarDecripciones();
    }
/*
    private void llenarDecripciones(){
        descripciones = new HashMap<CONDICION, String>();
        descripciones.put(CONDICION.CELIACO, "Celiaco");
        descripciones.put(CONDICION.DIABETES, "Diabetes");
        descripciones.put(CONDICION.HIPERTENSION, "Hipertension");
        descripciones.put(CONDICION.HIPOTENSION, "Hipotension");
    }
    */

    public CONDICION getTipo() {
        return tipo;
    }

    public void setTipo(CONDICION tipo) {
        this.tipo = tipo;
    }
}
