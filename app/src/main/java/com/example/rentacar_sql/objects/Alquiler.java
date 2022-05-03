package com.example.rentacar_sql.objects;

public class Alquiler {

    private int idAlquiler;
    private int idCliente;
    private int idVehiculo;
    private String fechaIni;
    private String fechaFin;
    private String tiempoAlq;
    private String precioAlquiler;

    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idA) {
        this.idAlquiler = idA;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idC) {
        this.idCliente = idC;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idV) {
        this.idVehiculo = idV;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fecchaInicio) {
        this.fechaIni = fecchaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fecchaFin) {
        this.fechaFin = fecchaFin;
    }

    public String getTiempoAlquiler() {
        return tiempoAlq;
    }

    public void setTiempoAlquiler(String tiempoAlquiler) {
        this.tiempoAlq = tiempoAlquiler;
    }

    public String getPrecioAlquiler() {
        return precioAlquiler;
    }

    public void setPrecioAlquiler(String precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }

}
