package com.example.rentacar_sql.objects;

public class Alquiler {

    private int idAlquiler;
    private int idCliente;
    private int idVehiculo;
    private String fechaIni;
    private String fechaFin;
    private String tiempoAlq;
    private String precio;

    public int getIdA() {
        return idAlquiler;
    }

    public void setIdA(int idA) {
        this.idAlquiler = idA;
    }

    public int getIdC() {
        return idCliente;
    }

    public void setIdC(int idC) {
        this.idCliente = idC;
    }

    public int getIdV() {
        return idVehiculo;
    }

    public void setIdV(int idV) {
        this.idVehiculo = idV;
    }

    public String getFecchaInicio() {
        return fechaIni;
    }

    public void setFecchaInicio(String fecchaInicio) {
        this.fechaIni = fecchaInicio;
    }

    public String getFecchaFin() {
        return fechaFin;
    }

    public void setFecchaFin(String fecchaFin) {
        this.fechaFin = fecchaFin;
    }

    public String getTiempoAlquiler() {
        return tiempoAlq;
    }

    public void setTiempoAlquiler(String tiempoAlquiler) {
        this.tiempoAlq = tiempoAlquiler;
    }

    public String getPrecioAlquiler() {
        return precio;
    }

    public void setPrecioAlquiler(String precioAlquiler) {
        this.precio = precioAlquiler;
    }

}
