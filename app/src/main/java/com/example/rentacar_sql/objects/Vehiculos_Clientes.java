package com.example.rentacar_sql.objects;

public class Vehiculos_Clientes {

    private int idVehiculo;
    private String placa;
    private String modelo;
    private String fechaIni;
    private String fechaFin;
    private String tiempoAlq;
    private String precio;


    public int getIdV() {
        return idVehiculo;
    }

    public void setIdV(int idV) {
        this.idVehiculo = idV;
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

    public void setFechFin(String fecchaFin) { this.fechaFin = fechaFin;}

    public String getTiempoAlq() {
        return tiempoAlq;
    }

    public void setTiempoAlq(String tiempoAlq) {
        this.tiempoAlq = tiempoAlq;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

}
