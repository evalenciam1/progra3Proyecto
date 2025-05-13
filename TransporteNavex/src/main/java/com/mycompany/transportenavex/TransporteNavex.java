/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.transportenavex;

import java.util.Scanner;

import com.mycompany.transportenavex.Frontend.VentanaPrincipal;
import com.mycompany.transportenavex.Models.ListaDoblementeEnlazada;
import com.mycompany.transportenavex.Models.Pasajero;

/**
 *
 * @author Esteban Valencia
 */
public class TransporteNavex {
    public static void main(String[] args) {
        ListaDoblementeEnlazada lista = new ListaDoblementeEnlazada();
        Scanner sc = new Scanner(System.in);
        int opcion;

        new VentanaPrincipal();

        
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Agregar pasajero");
            System.out.println("2. Eliminar pasajero");
            System.out.println("3. Buscar pasajero");
            System.out.println("4. Mostrar pasajeros (adelante)");
            System.out.println("5. Mostrar pasajeros (atrás)");
            System.out.println("6. Modificar pasajero");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1 -> {
                    System.out.print("DPI: ");
                    String dpi = sc.nextLine().trim();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Número de asiento: ");
                    int asiento = sc.nextInt();
                    sc.nextLine(); 
                    Pasajero nuevo = new Pasajero(dpi, nombre, asiento);
                    lista.agregar(nuevo);
                }

                case 2 -> {
                    System.out.print("DPI del pasajero a eliminar: ");
                    String dpiEliminar = sc.nextLine().trim();
                    boolean eliminado = lista.eliminar(dpiEliminar);
                    System.out.println(eliminado ? "Eliminado." : "No encontrado.");
                }

                case 3 -> {
                    System.out.print("DPI del pasajero a buscar: ");
                    String dpiBuscar = sc.nextLine().trim();
                    Pasajero encontrado = lista.buscaPasajero(dpiBuscar);
                    System.out.println(encontrado != null ? encontrado : "Pasajero no encontrado.");
                }

                case 4 -> {
                    System.out.println("Pasajeros (adelante):");
                    lista.recorrerAdelante();
                }

                case 5 -> {
                    System.out.println("Pasajeros (atrás):");
                    lista.recorrerAtras();
                }

                case 6 -> {
                    System.out.print("DPI del pasajero a modificar: ");
                    String dpiMod = sc.nextLine().trim();
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = sc.nextLine();
                    System.out.print("Nuevo asiento: ");
                    int nuevoAsiento = sc.nextInt();
                    sc.nextLine();
                    boolean modificado = lista.modificarPasajero(dpiMod, nuevoNombre, nuevoAsiento);
                    System.out.println(modificado ? "Pasajero modificado." : "No se pudo modificar.");
                }

                case 0 -> System.out.println("Saliendo...");

                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}

