package com.reservationhotel.hotelreservation.mrlufer;

public class Nodo {


    Hotel hotel;
    Nodo padre;
    Region region;

    boolean esHoja;
    boolean esNodoInterno;


    private Nodo hoja1;
    private Nodo hoja2;
    private Nodo hoja3;
    private Nodo hoja4;

    /* Constructor */
    public Nodo(Hotel hotel) {
        this.hotel = hotel;
    }

    /* Setters y Getters */
    public void setValor(Hotel hotel) {
        this.hotel = hotel;
    }

    public Hotel getValor() {
        return hotel;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }



}
