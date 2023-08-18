package com.example.tiendadezapatos.ui.productos.model;

public class ProductoModel {

    private String id;

    private String nombre;

    private String descripcion;

    private long precio;

    private Integer imagen;

    private String uriImg;

    private Integer cantidad;


    private Integer idDb;

    public ProductoModel() {
    }

    public ProductoModel(String idFirebase, String nombre, String descripcion, long precio, Integer cantidad, Integer idDb) {
        this.id = idFirebase;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.idDb = idDb;
    }

    public ProductoModel(String nombre, String descripcion, long precio, String uriImg) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.uriImg = uriImg;
    }

    public ProductoModel(String nombre, String descripcion, long precio, Integer imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
    }

    public ProductoModel(String id, String nombre, String descripcion, long precio, Integer imagen, String uriImg) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.uriImg = uriImg;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getIdDb() {
        return idDb;
    }

    public void setIdDb(Integer idDb) {
        this.idDb = idDb;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public Integer getImagen() {
        return imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }

    public String getUriImg() {
        return uriImg;
    }

    public void setUriImg(String uriImg) {
        this.uriImg = uriImg;
    }
}