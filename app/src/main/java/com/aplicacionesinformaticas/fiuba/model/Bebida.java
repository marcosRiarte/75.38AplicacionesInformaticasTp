package com.aplicacionesinformaticas.fiuba.model;

/*Clase que representa los bebidas de los que una orden
ademas cuenta con un vector de condciones medicas las cuales indican para que condiciones medicas la bebida  esta prohibida
 */

import java.lang.String;
import java.util.ArrayList;

public class Bebida {


    private String nombre;
    private float precio;
    private ArrayList <CondicionMedica>  condicionesMedicasDondeEstaProhibida;

    public Bebida (String nombre,float precioXunidad ) {
        this.nombre = nombre;
        this.precio = precioXunidad;
    }

    public float getPrecio () {
        return this.precio;
    }

    public void setPrecio(float nuevoPrecio) {
        this.precio = nuevoPrecio;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre (String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    public ArrayList<CondicionMedica> getCondicionesMedicasDondeEstaProhibida() {
        return condicionesMedicasDondeEstaProhibida;
    }

    public void agregarCondicionMedica (CondicionMedica condicionMedica) {
        this.condicionesMedicasDondeEstaProhibida.add(condicionMedica);
    }


}
