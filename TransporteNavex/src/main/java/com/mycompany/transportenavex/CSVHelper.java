package com.mycompany.transportenavex;

import java.io.*;
import java.util.List;

import com.mycompany.transportenavex.Models.ListaDoblementeEnlazada;
import com.mycompany.transportenavex.Models.Pasajero;

public class CSVHelper {

    private static final int NumAsientos = 10;  
    private static final String Folder = System.getProperty("user.dir") + "\\data\\";  //  crea carpeta sin no existe dentro del mismo proyecto llamado data donde se guardan los csv

    // carga los  pasajeros desde archivo CSV a la lista enlazada
    public static void cargarDesdeCSV(int avionetaId, ListaDoblementeEnlazada lista) throws IOException {
        String ruta = getRutaArchivo(avionetaId);
        File archivo = new File(ruta);

        if (!archivo.exists()) {
            System.out.println("El archivo no existe. No se puede cargar.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
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

                // Crear el objeto Pasajero
                Pasajero pasajero = new Pasajero(dpi, nombre, asiento);

                // Validar el DPI y número de asiento directamente dentro de la clase ListaDoblementeEnlazada
                if (!validarDpi(pasajero.getDpi())) {
                    System.out.println("DPI inválido: " + pasajero.getDpi());
                    continue;
                }

                // Si el pasajero es válido, intentar agregarlo a la lista
                if (!lista.agregar(pasajero)) {
                    System.out.println("No se pudo agregar el pasajero: " + pasajero.getNombre());
                }
            }
        }
    }

    // Guardar todos los pasajeros en un archivo CSV
    public static void guardarEnCSV(int avionetaId, ListaDoblementeEnlazada lista) throws IOException {
        String ruta = getRutaArchivo(avionetaId);
        File archivo = new File(ruta);

        // Crear la carpeta "data" si no existe
        File carpeta = archivo.getParentFile();
        if (!carpeta.exists()) {
            carpeta.mkdirs();  // Crear la carpeta
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            // Obtener todos los pasajeros y escribir cada uno en el archivo
            List<Pasajero> pasajeros = lista.buscarPasajero();
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
