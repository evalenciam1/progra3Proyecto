package com.mycompany.transportenavex.Models;

public class NodoPasajero {
    Pasajero pasajero;
    NodoPasajero anterior;
    NodoPasajero siguiente;

    public NodoPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
        anterior = siguiente = null;
    }


}
