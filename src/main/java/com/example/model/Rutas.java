package com.example.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "rutas") // Nombre de la tabla en la base de datos
public class Rutas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ruta", nullable = false, updatable = false)
    private Integer idRuta;

    @Column(name = "nombre_ruta", length = 100, nullable = false)
    private String nombreRuta;

    @Column(name = "latitud_inicio", precision = 10, scale = 8)
    private BigDecimal latitudInicio;

    @Column(name = "longitud_inicio", precision = 11, scale = 8)
    private BigDecimal longitudInicio;

    @Column(name = "latitud_fin", precision = 10, scale = 8)
    private BigDecimal latitudFin;

    @Column(name = "longitud_fin", precision = 11, scale = 8)
    private BigDecimal longitudFin;

    // Constructores
    public Rutas() {
        // Constructor vacío requerido por JPA
    }

    public Rutas(String nombreRuta, BigDecimal latitudInicio, BigDecimal longitudInicio,
            BigDecimal latitudFin, BigDecimal longitudFin) {
        this.nombreRuta = nombreRuta;
        this.latitudInicio = latitudInicio;
        this.longitudInicio = longitudInicio;
        this.latitudFin = latitudFin;
        this.longitudFin = longitudFin;
    }

    // Getters y Setters
    public Integer getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Integer idRuta) {
        this.idRuta = idRuta;
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

    // Método toString (opcional, para depuración)
    @Override
    public String toString() {
        return "Rutas{"
                + "idRuta=" + idRuta
                + ", nombreRuta='" + nombreRuta + '\''
                + ", latitudInicio=" + latitudInicio
                + ", longitudInicio=" + longitudInicio
                + ", latitudFin=" + latitudFin
                + ", longitudFin=" + longitudFin
                + '}';
    }
}
