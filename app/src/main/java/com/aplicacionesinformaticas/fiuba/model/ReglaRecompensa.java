package com.aplicacionesinformaticas.fiuba.model;

/*Clase que representa las condicones en las que puede se canjeada un recompensa en base a los puntos 
acumulados por el usuario
 */

import java.lang.String;
import com.aplicacionesinformaticas.fiuba.model.Recompensa;
import com.aplicacionesinformaticas.fiuba.model.User;



public class ReglaRecompensa {
    private Recompensa recompensa;
    private User usuario;
    private String estado[];

    public ReglaRecompensa (Recompensa unaRecompensa,User unUsuario) {
        this.recompensa = unaRecompensa;
        this.usuario = unUsuario;
        this.estado[0] = "Disponible";
        this.estado[1] = "No Disponible";
        this.estado[2] = "Ya utilizado";
    }

    public String analizarEstadoRecompensa() {
        if (this.usuario.getPuntos() >= this.recompensa.getPuntosNecesarios()) {
            return this.estado[0];
        }
        else {
               if (this.usuario.getPuntos() < this.recompensa.getPuntosNecesarios()) {
                   return this.estado[1];
               }
               else {
                  if (this.fueUsada()){
                      return this.estado[2];
                  } else {
                      return "invalid state";
                  }
               }
        }

    }
    public boolean fueUsada() {
       // if(this.usuario.getId()) //Aun no esta en la clase usuario)
        //TODO : DESD LA BASE DE DATOS CARGAR LAS RECOMENSAS YA USADAS
         return true;       
    }
     public void actualizarEstado(String nuevoEstado) {
        //Nuevo esado en la base de datos 
     }
}
