package com.aplicacionesinformaticas.fiuba.model;

/*Clase que representa las recompensas que dan mendiante el consumo acumulado o
la compras anteriores.Tiene un descripicion detallando la recompensa.
 */

import java.lang.String;


public class Recompensa {
    private int id;
    private String descripcion;
    private int puntosNecesarios;

    public Recompensa (int ID,int puntosParaCanjear) {
        this.id = ID;
        this.puntosNecesarios = puntosParaCanjear;
    }

    public int getId () {
        return this.id;
    }

    public int getPuntosNecesarios() {
        return puntosNecesarios;
    }
    
    public void setPuntosNecesarios (int nuevosPuntos) {
        this.puntosNecesarios = nuevosPuntos;
    }
    
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion (String unaDescripcion) {
        this.descripcion = unaDescripcion;
    }
}
