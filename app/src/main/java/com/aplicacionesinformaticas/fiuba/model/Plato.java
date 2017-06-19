package com.aplicacionesinformaticas.fiuba.model;

/*Calcula el precio todal del plato en base a los ingredientes que contiene 
 */
import com.aplicacionesinformaticas.fiuba.model.Ingrediente;
import java.lang.String;
import java.util.ArrayList;

public class Plato {
    private float precioBase;
    private float precioTotal;
    private ArrayList <Ingrediente> ingredientesDelPlato;

   public Plato(float precioBase){
       this.precioBase = precioBase;
       this.precioTotal = 0;
   }
    public float getPrecioBase() {
        return this.precioBase;
    }
    
    public void setPrecioBase(float nuevoPrecioBase) {
        this.precioBase = nuevoPrecioBase;
    }

    public void agregarIngrediente(Ingrediente nuevoIngrediente) {
        this.ingredientesDelPlato.add(nuevoIngrediente);
    }

    public ArrayList<Ingrediente> getIngredientesDelPlato() {
        return  this.ingredientesDelPlato;
    }

    public float getPrecioTotal() {
        for (int i = 0 ;i < this.ingredientesDelPlato.size();i++) {
            this.precioTotal = this.precioTotal + this.ingredientesDelPlato.get(i).getPrecio();
        }
        
        this.precioTotal = this.precioTotal + this.precioBase;
        return this.precioTotal;
    }
    
    
}
