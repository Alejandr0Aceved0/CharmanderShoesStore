package com.example.tiendadezapatos.ui.sucursales.model;

public class SucursalModel {

    private long latitude;
    private long longitude;
    String horaApertura;
    String horaCierre;
    String nombre;
    String telefono;
    int imgBanner;

    public SucursalModel(int imgBanner) {
        this.imgBanner = imgBanner;
    }

    public SucursalModel(long latitude, long longitude, String horaApertura, String horaCierre, String nombre, String telefono, int imgBanner) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.nombre = nombre;
        this.telefono = telefono;
        this.imgBanner = imgBanner;
    }


    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public String getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(String horaApertura) {
        this.horaApertura = horaApertura;
    }

    public String getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(String horaCierre) {
        this.horaCierre = horaCierre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getImgBanner() {
        return imgBanner;
    }

    public void setImgBanner(int imgBanner) {
        this.imgBanner = imgBanner;
    }

    @Override
    public String toString() {
        return "SucursalModel{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", horaApertura='" + horaApertura + '\'' +
                ", horaCierre='" + horaCierre + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", imgBanner=" + imgBanner +
                '}';
    }
}
