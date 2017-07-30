package com.aplicacionesinformaticas.fiuba.model;

import java.util.ArrayList;

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
    private int puntos = 0;
    /*
    private boolean diabetes;
    private boolean hipertension;
    private boolean hipotension;
    private boolean celiaco;
    */
    private String userNameLogin;
    private String password;
    private ArrayList<CondicionMedica> condicionesMedicas;
    private ArrayList<Orden> ordenes;
    private static User usuarioActual;
    private float puntosCobrados;

    public static User getUsuarioActual(){
        return usuarioActual;
    }

    public User(){
        this.puntosCobrados = 0;
        this.condicionesMedicas = new ArrayList<CondicionMedica>();
        this.ordenes = new ArrayList<Orden>();
    }

    public static void setUsuarioActual(User user){
        usuarioActual = user;
    }

    public void sumarPuntos(int puntosASumar) {
        this.puntos = this.puntos + puntosASumar;
    }
    
    //public int getPuntos() {
   //    return this.puntos;
   // }

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
        return tieneCondicionMedica(CondicionMedica.CONDICION.DIABETES);
    }

    public void setDiabetes(boolean diabetes) {
        if (diabetes) {
            agregarCondicionMedica(new CondicionMedica(CondicionMedica.CONDICION.DIABETES));
        } else {
            quitarCondicionMedica(CondicionMedica.CONDICION.DIABETES);
        }
    }

    public boolean isHipertension() {
        return tieneCondicionMedica(CondicionMedica.CONDICION.HIPERTENSION);
    }

    public void setHipertension(boolean hipertension) {
        if (hipertension) {
            agregarCondicionMedica(new CondicionMedica(CondicionMedica.CONDICION.HIPERTENSION));
        } else {
            quitarCondicionMedica(CondicionMedica.CONDICION.HIPERTENSION);
        }
    }

    public boolean isHipotension() {
        return tieneCondicionMedica(CondicionMedica.CONDICION.HIPOTENSION);
    }

    public void setHipotension(boolean hipotension) {
        if (hipotension) {
            agregarCondicionMedica(new CondicionMedica(CondicionMedica.CONDICION.HIPOTENSION));
        } else {
            quitarCondicionMedica(CondicionMedica.CONDICION.HIPOTENSION);
        }
    }

    public boolean isCeliaco() {
        return tieneCondicionMedica(CondicionMedica.CONDICION.CELIACO);

    }

    public void setCeliaco(boolean celiaco) {
        if (celiaco) {
            agregarCondicionMedica(new CondicionMedica(CondicionMedica.CONDICION.CELIACO));
        } else {
            quitarCondicionMedica(CondicionMedica.CONDICION.CELIACO);
        }
    }

    public void quitarCondicionMedica(CondicionMedica.CONDICION condicion){
        for (int i = condicionesMedicas.size(); i > 0; i--) {
            if (condicionesMedicas.get(i - 1).getTipo() == condicion){
                condicionesMedicas.remove(i - 1);
            }
        }
    }

    public boolean tieneCondicionMedica(CondicionMedica.CONDICION condicion){
        for (int i = 0; i < condicionesMedicas.size(); i++){
            if (condicionesMedicas.get(i).getTipo() == condicion){
                return true;
            }
        }
        return false;
    }

    public String getUserNameLogin() {
        return userNameLogin;
    }

    public void setUserNameLogin(String userNameLogin) {
        this.userNameLogin = userNameLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public ArrayList<CondicionMedica> getCondicionesMedicas() {
        return condicionesMedicas;
    }

    public void setCondicionesMedicas(ArrayList<CondicionMedica> condicionesMedicas) {
        this.condicionesMedicas = condicionesMedicas;
    }

    public void agregarCondicionMedica(CondicionMedica condicionMedica){
        if (!tieneCondicionMedica(condicionMedica.getTipo())) {
            this.condicionesMedicas.add(condicionMedica);
        }
    }

    public boolean esCompatible(Ingrediente ingrediente){
        boolean result = true;

        for (int i = 0; i < condicionesMedicas.size(); i++){
            CondicionMedica.CONDICION condicionUsuario = condicionesMedicas.get(i).getTipo();
            for (int j = 0; j < ingrediente.getCondicionesMedicasDondeEstaProhibido().size(); j++ ){
                CondicionMedica.CONDICION condicionIngrediente = ingrediente.getCondicionesMedicasDondeEstaProhibido().get(j).getTipo();
                if (condicionUsuario == condicionIngrediente){
                    return false;
                }
            }
        }

        return result;
    }

    public ArrayList<Orden> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(ArrayList<Orden> ordenes) {
        this.ordenes = ordenes;
    }

    public void agregarOrden(Orden orden){
        this.ordenes.add(orden);
    }

    public float getPuntos(){
        float puntos = 0;
        for (int i = 0; i < ordenes.size(); i++) {
            puntos += ordenes.get(i).getCuenta() / 10;
        }

        return puntos - getPuntosCobrados();
    }

    public float getPuntosCobrados(){
        return  puntosCobrados;
    }

    public void agregarPuntosCobrados(Float puntos){
        this.puntosCobrados += puntos;
    }
}
