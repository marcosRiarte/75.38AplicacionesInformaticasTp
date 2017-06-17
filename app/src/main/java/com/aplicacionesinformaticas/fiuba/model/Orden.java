package com.aplicacionesinformaticas.fiuba.model;

/*Clase que representa la orden de un cliente, que esta compuesta por bebidas y platos.
 */
import com.aplicacionesinformaticas.fiuba.model.Plato
import com.aplicacionesinformaticas.fiuba.model.Bebida
import java.lang.String;
import java.util.ArrayList;

public class Orden {
    private User usuario;
    private ArrayList<Bebida> bebidas;
    private ArrayList<Plato> platos;

    public Orden (User unUsuario) {
        this.bebidas = new ArrayList<Bebida>();
        this.platos = new ArrayList<Plato>();
        this.usuario = unUsuario;
        
    }

    public void agregarPlato (Plato otroPlato) {
        this.platos.add(otroPlato);
    }

    public void agregarBebida(Bebida otraBebida) {
        this.bebidas.add(otraBebida);
    }

    public ArrayList<Bebida> getBebidas() {
        return  this.bebidas;

    }

    public ArrayList<Plato> getPlatos () {
        return this.platos;
    }

    public float getCuenta() {
        float cuenta = 0;
        for (int i = 0 ; i < this.bebidas.size();i++) {
           cuenta = cuenta + this.bebidas.get(i).precio; 
        }
        
        for (int j = 0 ;j < this.platos.size();j++) {
            cuenta = cuenta + this.platos.get(j).getPrecioTotal;
        }
        
    }

    public User getUsuario() {
        return usuario;
    }
    
}
