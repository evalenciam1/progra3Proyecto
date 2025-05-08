package com.mycompany.transportenavex;

public class ListaDoblementeEnlazada {

    private NodoPasajero cabeza;
    private NodoPasajero cola;

    private final int cantidadMaximaPasajeros = 10;

    public int cantidadMaximaPasajeros() {
        return cantidadMaximaPasajeros;
    }

    public ListaDoblementeEnlazada() {
        cabeza = null;
        cola = null;
    }

    public boolean agregar(Pasajero pasajero) {

        String dpi = pasajero.getDpi().trim();
        pasajero.setDpi(dpi);

        if (!validacionDpi(pasajero.getDpi())) {
            System.out.println("Dpi invalido");
            return false;
        }

        if (buscaPasajero(pasajero.getDpi()) != null) {
            System.out.println("Ya existe este pasajero");
            return false;
        }
        if (asientosOcupados(pasajero.getNumeroAsiento())) {
            System.out.println("Asiento ya ocupado");
            return false;
        }
        if (cantidadPasajeros() >= cantidadMaximaPasajeros) {
            System.out.println("La avioneta ya esta llena");
            return false;
        }
        NodoPasajero nuevo = new NodoPasajero(pasajero);
        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
        return true;
    }

    public int cantidadPasajeros() {
        int contador = 0;
        NodoPasajero actual = cabeza;
        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }
        return contador;
    }

    public boolean eliminar(String dpi) {
        dpi = dpi.trim();

        NodoPasajero actual = cabeza;

        while (actual != null) {
            if (actual.pasajero.getDpi().equals(dpi)) {
                if (actual == cabeza) {
                    cabeza = actual.siguiente;
                    if (cabeza != null) {
                        cabeza.anterior = null;
                    } else {
                        cola = null;
                    }
                } else if (actual == cola) {
                    cola = actual.anterior;
                    if (cola != null) {
                        cola.siguiente = null;
                    } else {
                        cabeza = null;
                    }
                } else {
                    actual.anterior.siguiente = actual.siguiente;
                    actual.siguiente.anterior = actual.anterior;
                }
                return true;
            }
            actual = actual.siguiente;
        }

        return false;
    }

    public Pasajero buscaPasajero(String dpi) {
        dpi = dpi.trim();
        NodoPasajero actual = cabeza;
        while (actual != null) {
            if (actual.pasajero.getDpi().equals(dpi)) {
                return actual.pasajero;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public void recorrerAdelante() {
        NodoPasajero actual = cabeza;
        while (actual != null) {
            System.out.println(actual.pasajero);
            actual = actual.siguiente;
        }
    }

    public void recorrerAtras() {
        NodoPasajero actual = cola;
        while (actual != null) {
            System.out.println(actual.pasajero);
            actual = actual.anterior;
        }
    }

    public void Limpiar() {
        cabeza = null;
        cola = null;
    }

    public boolean modificarPasajero(String dpi, String nombre, int asiento) {
        dpi = dpi.trim();
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Nombre del pasajero inválido");
            return false;
        }
        Pasajero pasajero = buscaPasajero(dpi);
        if (pasajero != null) {
            if (pasajero.getNumeroAsiento() != asiento && asientosOcupados(asiento)) {
                System.out.println("El asiento ya está ocupado.");
                return false;
            }
            pasajero.setNombre(nombre);
            pasajero.setNumeroAsiento(asiento);
            return true;
        }
        return false;
    }

    private boolean asientosOcupados(int numeroAsiento) {
        NodoPasajero actual = cabeza;
        while (actual != null) {
            if (actual.pasajero.getNumeroAsiento() == numeroAsiento) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    private boolean validacionDpi(String dpi) {
        return dpi != null && dpi.matches("\\d{13}");
    }

}
