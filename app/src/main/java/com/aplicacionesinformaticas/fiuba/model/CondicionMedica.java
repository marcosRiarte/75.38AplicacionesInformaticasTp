package com.aplicacionesinformaticas.fiuba.model;

/*Clase que representa la condicio medica de un cliente*/

import java.lang.String;

public class CondicionMedica {

    private int id;
    private std::String descripcion;

    public CondicionMedica (String descripcion,int ID){
        this.id = ID;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
