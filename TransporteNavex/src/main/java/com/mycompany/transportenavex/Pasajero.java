package com.mycompany.transportenavex;

public class Pasajero {
    

    private String dpi;
    private String nombre;
    private int numeroAsiento;

    public Pasajero(String dpi, String nombre, int numeroAsiento) {
        this.dpi = dpi;
        this.nombre = nombre;
        this.numeroAsiento = numeroAsiento;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(int numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pasajero{");
        sb.append("dpi=").append(dpi);
        sb.append(", nombre=").append(nombre);
        sb.append(", numeroAsiento=").append(numeroAsiento);
        sb.append('}');
        return sb.toString();
    }

}
