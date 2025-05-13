package com.mycompany.transportenavex.Controllers;

import com.mycompany.transportenavex.Models.ListaDoblementeEnlazada;
import com.mycompany.transportenavex.Models.Pasajero;

public class AccionesController {

    public static String agregarPasajero(ListaDoblementeEnlazada lista, Pasajero pasajero) {
        
        String dpi = pasajero.getDpi().trim();
        pasajero.setDpi(dpi);

        if (!validacionDpi(pasajero.getDpi())) {
            return "DPI INVALIDO";
        }

        if (lista.buscaPasajero(pasajero.getDpi()) != null) {
            return "YA EXISTE ESTE PASAJERO";
        }
        if (lista.asientosOcupados(pasajero.getNumeroAsiento())) {
            return "ASIENTO OCUPADO";
        }
        if (lista.cantidadPasajeros() >= 10) {
            return "LA AVIONETA YA ESTA LLENA";
        }

        return lista.agregar(pasajero);
        
    }

        //Validacion DPI a 13 caracteres
    private static boolean validacionDpi(String dpi) {
        return dpi != null && dpi.matches("\\d{13}");
    }


    public static boolean eliminarPasajero(ListaDoblementeEnlazada lista, String dpi) {
        return lista.eliminar(dpi);
    }

    public static Pasajero buscarPasajero(ListaDoblementeEnlazada lista, String dpi) {
        return lista.buscaPasajero(dpi);
    }

    public static boolean modificarPasajero(ListaDoblementeEnlazada lista, String dpi, String nuevoNombre, int nuevoAsiento) {
        return lista.modificarPasajero(dpi, nuevoNombre, nuevoAsiento);
    }

    public static void recorrerAdelante(ListaDoblementeEnlazada lista) {
        lista.recorrerAdelante();
    }

    public static void recorrerAtras(ListaDoblementeEnlazada lista) {
        lista.recorrerAtras();
    }
}
