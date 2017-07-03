package com.aplicacionesinformaticas.fiuba.model;

/*Calcula el precio todal del plato en base a los ingredientes que contiene 
 */
import com.aplicacionesinformaticas.fiuba.model.Ingrediente;
import java.lang.String;
import java.util.ArrayList;

public class Plato {
    private int PRECIO_BASE = 20;

    private float precioBase;
    private float precioTotal;
    private String nombre;
    private int cantidadEnOrden;
    private ArrayList <Ingrediente> ingredientesDelPlato;


   public Plato(float precioBase){
       this.precioBase = precioBase;
       this.precioTotal = 0;
       this.cantidadEnOrden = 0;
       this.ingredientesDelPlato = new ArrayList<Ingrediente>();
   }

    public Plato(){
        this.precioBase = PRECIO_BASE;
        this.precioTotal = 0;
        this.ingredientesDelPlato = new ArrayList<Ingrediente>();
    }

    public Plato(String nombre){

        this.precioBase = PRECIO_BASE;
        this.precioTotal = 0;
        this.setNombre(nombre);
        this.ingredientesDelPlato = new ArrayList<Ingrediente>();
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
        this.precioTotal = 0;
        for (int i = 0 ;i < this.ingredientesDelPlato.size();i++) {
            this.precioTotal = this.precioTotal + this.ingredientesDelPlato.get(i).getPrecio();
        }
        
        this.precioTotal = this.precioTotal + this.precioBase;
        return this.precioTotal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadEnOrden() {
        return cantidadEnOrden;
    }

    public void setCantidadEnOrden(int cantidadEnOrden) {
        this.cantidadEnOrden = cantidadEnOrden;
    }

    public void setIngredientesDelPlato(ArrayList<Ingrediente> ingredientesDelPlato) {
        this.ingredientesDelPlato.addAll(ingredientesDelPlato);
    }

    public Plato duplicar(){
        Plato plato = new Plato();
        plato.setPrecioBase(this.getPrecioBase());
        plato.setNombre(this.getNombre());
        plato.setIngredientesDelPlato(this.getIngredientesDelPlato());

        return plato;
    }
}
