package com.aplicacionesinformaticas.fiuba.model;

/**
 * Created by Gregorio on 6/11/2017.
 */

public class User {
    public static final int GENDER_MALE = 1;
    public static final int GENDER_FEMALE = 2;

    private String nombre;
    private String apellido;
    private String nacimiento;
    private int genero;
    private int hijos;
    private boolean diabetes;
    private boolean hipertension;
    private boolean hipotension;
    private boolean celiaco;
    private static User usuarioActual;

    public static User getUsuarioActual(){
        return usuarioActual;
    }

    public static void setUsuarioActual(User user){
        usuarioActual = user;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public int getHijos() {
        return hijos;
    }

    public void setHijos(int hijos) {
        this.hijos = hijos;
    }

    public boolean isDiabetes() {
        return diabetes;
    }

    public void setDiabetes(boolean diabetes) {
        this.diabetes = diabetes;
    }

    public boolean isHipertension() {
        return hipertension;
    }

    public void setHipertension(boolean hipertension) {
        this.hipertension = hipertension;
    }

    public boolean isHipotension() {
        return hipotension;
    }

    public void setHipotension(boolean hipotension) {
        this.hipotension = hipotension;
    }

    public boolean isCeliaco() {
        return celiaco;
    }

    public void setCeliaco(boolean celiaco) {
        this.celiaco = celiaco;
    }
}
