package com.google.android.gms.samples.vision.barcodereader.modelo;

/**
 * Created by Patricio on 27-10-2016.
 */

public class Producto{
    private int id;
    private String name;
    private String description;
    private String valor;
    private String supermercado;
    private boolean inCarrito;

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getNombre() {
        return name;
    }

    public void setNombre(String nombre) {
        this.name = nombre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setInCarrito(boolean carrito) { this.inCarrito = carrito; }

    public boolean isInCarrito () { return inCarrito; }

    public int getCode() { return id; }

    public void setSupermercado(String supermercado) { this.supermercado = supermercado; }

    public String getSupermercado() { return supermercado; }


}
