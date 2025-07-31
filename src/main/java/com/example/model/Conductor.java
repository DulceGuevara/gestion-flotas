package com.example.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "conductor")
public class Conductor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_conductor")
    private Integer codigoConductor;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "dpi")
    private String dpi;
    @Column(name = "licencia")
    private String licencia;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "placa_vehiculo")
    private String placaVehiculo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", columnDefinition = "ENUM('ACTIVO', 'INACTIVO')", nullable = false)
    private Conductor.EstadoUsuario estado = Conductor.EstadoUsuario.ACTIVO;
    //private String estado;

    @Column(name = "creado_por")
    private String creadoPor;
    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;
    @Column(name = "modificado_por")
    private String modificadoPor;
    @Column(name = "fecha_modificacion")
    private Timestamp fechaModificacion;

    // Getters y Setters
    public Integer getCodigoConductor() {
        return codigoConductor;
    }

    public void setCodigoConductor(Integer codigoConductor) {
        this.codigoConductor = codigoConductor;
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

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public Timestamp getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Timestamp fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public enum EstadoUsuario {
        ACTIVO,
        INACTIVO
    }

    /**
     * @return the estado
     */
    public Conductor.EstadoUsuario getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Conductor.EstadoUsuario estado) {
        this.estado = estado;
    }

}
