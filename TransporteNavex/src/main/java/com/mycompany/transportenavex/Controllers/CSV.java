package com.mycompany.transportenavex.Controllers;

import java.io.*;
import java.util.List;

import com.mycompany.transportenavex.Models.ListaDoblementeEnlazada;
import com.mycompany.transportenavex.Models.Pasajero;
import com.mycompany.transportenavex.Controllers.AccionesController;

public class CSV {

     
    private static final String Folder = System.getProperty("user.dir") + "\\data\\";  //  crea carpeta sin no existe dentro del mismo proyecto llamado data donde se guardan los csv

    // carga los  pasajeros desde archivo CSV a la lista enlazada
    public static void cargarDesdeArchivoCSV(File archivo, ListaDoblementeEnlazada lista) throws IOException {
    int NumAsientos = lista.cantidadMaximaPasajeros();

    if (!archivo.exists()) {
        System.out.println("El archivo no existe. No se puede cargar.");
        return;
    }

    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
        String linea;
        int i = 0;
        while ((linea = br.readLine()) != null && i < NumAsientos) {
            i++;
            String[] partes = linea.split(",");
            if (partes.length != 3) {
                System.out.println("Línea mal formada: " + linea);
                continue;
            }

            String nombre = partes[0].trim();
            String dpi = partes[1].trim();
            int asiento;
            try {
                asiento = Integer.parseInt(partes[2].trim());
            } catch (NumberFormatException e) {
                System.out.println("Número de asiento inválido en línea: " + linea);
                continue;
            }

            Pasajero pasajero = new Pasajero(dpi, nombre, asiento);

            if (!validarDpi(pasajero.getDpi())) {
                System.out.println("DPI inválido: " + pasajero.getDpi());
                continue;
            }

            if (!"PASAJERO AGREGADO CORRECTAMENTE".equals(AccionesController.agregarPasajero(lista, pasajero))) {
                System.out.println("No se pudo agregar el pasajero: " + pasajero.getNombre());
            }
        }
    }
}


    // Guardar todos los pasajeros en un archivo CSV
   public static void guardarEnArchivoCSV(File archivo, ListaDoblementeEnlazada lista) throws IOException {
    // Crear la carpeta si no existe
    File carpeta = archivo.getParentFile();
    if (!carpeta.exists()) {
        carpeta.mkdirs();
    }

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
        List<Pasajero> pasajeros = lista.obtenerTodosPasjaeros();
        for (Pasajero p : pasajeros) {
            String linea = String.format("%s,%s,%d", p.getNombre(), p.getDpi(), p.getNumeroAsiento());
            bw.write(linea);
            bw.newLine();
        }
    }
}


    // Valida el DPI (13 dígitos)
    private static boolean validarDpi(String dpi) {
        return dpi != null && dpi.matches("\\d{13}");
    }

    // Método para obtener la ruta completa del archivo CSV
    private static String getRutaArchivo(int avionetaId) {
        return Folder + "avioneta" + avionetaId + ".csv";
    }
}
