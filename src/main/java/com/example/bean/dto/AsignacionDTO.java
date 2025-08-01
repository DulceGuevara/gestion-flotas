package com.example.bean.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class AsignacionDTO {

    private Integer idAsignacion;
    private String nombre; // Nombre del conductor
    private String dpi;
    private String placa;
    private String marca;
    private String gpsImei;
    private String nombreRuta;
    private BigDecimal latitudInicio;
    private BigDecimal longitudInicio;
    private BigDecimal latitudFin;
    private BigDecimal longitudFin;
    private Date fechaAsignacion;

    public AsignacionDTO(Integer idAsignacion, String nombre, String dpi, String placa, String marca, String gpsImei,
            String nombreRuta, BigDecimal latitudInicio, BigDecimal longitudInicio, BigDecimal latitudFin,
            BigDecimal longitudFin, Date fechaAsignacion) {
        this.idAsignacion = idAsignacion;
        this.nombre = nombre;
        this.dpi = dpi;
        this.placa = placa;
        this.marca = marca;
        this.gpsImei = gpsImei;
        this.nombreRuta = nombreRuta;
        this.latitudInicio = latitudInicio;
        this.longitudInicio = longitudInicio;
        this.latitudFin = latitudFin;
        this.longitudFin = longitudFin;
        this.fechaAsignacion = fechaAsignacion;
    }

    // Getters y setters
    public Integer getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(Integer idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getGpsImei() {
        return gpsImei;
    }

    public void setGpsImei(String gpsImei) {
        this.gpsImei = gpsImei;
    }

    public String getNombreRuta() {
        return nombreRuta;
    }

    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
    }

    public BigDecimal getLatitudInicio() {
        return latitudInicio;
    }

    public void setLatitudInicio(BigDecimal latitudInicio) {
        this.latitudInicio = latitudInicio;
    }

    public BigDecimal getLongitudInicio() {
        return longitudInicio;
    }

    public void setLongitudInicio(BigDecimal longitudInicio) {
        this.longitudInicio = longitudInicio;
    }

    public BigDecimal getLatitudFin() {
        return latitudFin;
    }

    public void setLatitudFin(BigDecimal latitudFin) {
        this.latitudFin = latitudFin;
    }

    public BigDecimal getLongitudFin() {
        return longitudFin;
    }

    public void setLongitudFin(BigDecimal longitudFin) {
        this.longitudFin = longitudFin;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Timestamp fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    // Método toString (opcional, para depuración)
    @Override
    public String toString() {
        return "AsignacionDTO{"
                + "idAsignacion=" + idAsignacion
                + ", nombre='" + nombre + '\''
                + ", dpi='" + dpi + '\''
                + ", placa='" + placa + '\''
                + ", marca='" + marca + '\''
                + ", gpsImei='" + gpsImei + '\''
                + ", nombreRuta='" + nombreRuta + '\''
                + ", latitudInicio=" + latitudInicio
                + ", longitudInicio=" + longitudInicio
                + ", latitudFin=" + latitudFin
                + ", longitudFin=" + longitudFin
                + ", fechaAsignacion=" + fechaAsignacion
                + '}';
    }
}
