package com.aplicacionesinformaticas.fiuba.model;

/*Clase que representa los ingredientes de los que se compone un plato
ademas cuenta con un vector de condciones medicas las cuales indican para que condiciones medicas el ingrediente esta prohibido
 */

import java.lang.String;
import java.util.ArrayList;

public class Ingrediente {


    private String nombre;
    private float precio;
    private ArrayList <CondicionMedica>  condicionesMedicasDondeEstaProhibido;

    public Ingrediente (String nombre,float precioXunidadDeMedida ) {
        this.nombre = nombre;
        this.precio = precioXunidadDeMedida;
        this.condicionesMedicasDondeEstaProhibido = new ArrayList<CondicionMedica>();
    }

    public Ingrediente (String nombre,float precioXunidadDeMedida, ArrayList<CondicionMedica> prohibido ) {
        this.nombre = nombre;
        this.precio = precioXunidadDeMedida;
        this.condicionesMedicasDondeEstaProhibido = prohibido;
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
    
    public ArrayList<CondicionMedica> getCondicionesMedicasDondeEstaProhibido() {
        return condicionesMedicasDondeEstaProhibido;
    }

    public void agregarCondicionMedica (CondicionMedica condicionMedica) {
        this.condicionesMedicasDondeEstaProhibido.add(condicionMedica);
    }


}
