package com.example.rentacar_sql.objects;

public class Vehiculos_Clientes {

    private int idVehiculo;
    private String placa;
    private String modelo;
    private String fechaIni;
    private String fechaFin;
    private String tiempoAlq;
    private String precioAlquiler;


    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() { return modelo; }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFechFin() {
        return fechaFin;
    }

    public void setFechFin(String fechaFin) { this.fechaFin = fechaFin;}

    public String getTiempoAlq() {
        return tiempoAlq;
    }

    public void setTiempoAlq(String tiempoAlq) {
        this.tiempoAlq = tiempoAlq;
    }

    public String getPrecioAlquiler() {
        return precioAlquiler;
    }

    public void setPrecioAlquiler(String precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }

}
