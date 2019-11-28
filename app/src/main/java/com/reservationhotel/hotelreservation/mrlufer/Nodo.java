package com.reservationhotel.hotelreservation.mrlufer;

public class Nodo {


    Hotel hotel;
    Nodo padre;
    Region region;

    boolean esHoja;
    boolean esNodoInterno;
    boolean esElPadre;


    private Nodo hoja1;
    private Nodo hoja2;
    private Nodo hoja3;
    private Nodo hoja4;

    /* Constructor */
    public Nodo(Hotel hotel) {
        this.hotel = hotel;
    }

    public Nodo() {
        this.esElPadre=true;

    }

    public Nodo getHoja1() {
        return hoja1;
    }

    public Nodo getHoja2() {
        return hoja2;
    }

    public Nodo getHoja3() {
        return hoja3;
    }

    public Nodo getHoja4() {
        return hoja4;
    }

    /* Setters y Getters */
    public void setValor(Hotel hotel) {
        this.hotel = hotel;
    }

    public Hotel getValor() {
        return hotel;
    }

    public void setHoja1(Nodo hoja1) {
        this.hoja1 = hoja1;
    }

    public void setHoja2(Nodo hoja2) {
        this.hoja2 = hoja2;
    }

    public void setHoja3(Nodo hoja3) {
        this.hoja3 = hoja3;
    }

    public void setHoja4(Nodo hoja4) {
        this.hoja4 = hoja4;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Region getRegion() {
        return region;
    }
}
