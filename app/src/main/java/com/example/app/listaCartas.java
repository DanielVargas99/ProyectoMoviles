package com.example.app;

public class listaCartas {

    public String nombre;
    public String edad;
    public String posicion;
    public String altura;
    public String nroEquipos;
    public String link;

    public listaCartas(String nombre, String edad, String posicion, String altura, String nroEquipos, String link) {
        this.nombre = nombre;
        this.edad = edad;
        this.posicion = posicion;
        this.altura = altura;
        this.nroEquipos = nroEquipos;
        this.link = link;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getNroEquipos() {
        return nroEquipos;
    }

    public void setNroEquipos(String nroEquipos) {
        this.nroEquipos = nroEquipos;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
