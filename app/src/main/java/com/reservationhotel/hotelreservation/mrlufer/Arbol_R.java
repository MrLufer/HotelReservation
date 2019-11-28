package com.reservationhotel.hotelreservation.mrlufer;

public class Arbol_R {

    private Nodo raiz;
    public Arbol_R( Hotel hotel ) {
        this.raiz = new Nodo( hotel );
    }

    public Arbol_R( Nodo raiz ) {
        this.raiz = raiz;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public void agregarNodo(Hotel hotel) {

        Nodo nuevo = new Nodo(hotel);
        if (esVacia()) {
            raiz = nuevo;

        } else {
            Nodo aux = raiz;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
        }

    }

    public boolean esVacia() {
        return raiz == null;
    }

}
