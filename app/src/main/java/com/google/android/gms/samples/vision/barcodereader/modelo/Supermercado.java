package com.google.android.gms.samples.vision.barcodereader.modelo;

/**
 * Created by Luchiin on 06-12-2016.
 */

public class Supermercado {
    private String N;
    private String name;
    private String lugar;
    private String region;

    public void setN(String id){
        this.N = id;
    }

    public String getN(){
        return N;
    }

    public String getNombre() {
        return name;
    }

    public void setNombre(String nombre) {
        this.name = nombre;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String valor) {
        this.region = valor;
    }

    public void setLugar(String lugar) { this.lugar = lugar; }

    public String getLugar() { return lugar; }

}
