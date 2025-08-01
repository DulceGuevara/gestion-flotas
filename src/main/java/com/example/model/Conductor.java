package com.example.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "conductor",
        indexes = {}) // Comentario de la tabla
public class Conductor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_conductor", nullable = false, updatable = false, columnDefinition = "int(11) COMMENT 'Código único del conductor'")
    private Integer codigoConductor;

    @Column(name = "nombre", length = 100, nullable = false, columnDefinition = "varchar(100) COMMENT 'Nombre completo del conductor'")
    private String nombre;

    @Column(name = "dpi", length = 20, nullable = false, columnDefinition = "varchar(20) COMMENT 'Documento de Identificación'")
    private String dpi;

    @Column(name = "licencia", length = 20, nullable = false, columnDefinition = "varchar(20) COMMENT 'Número de licencia de conducir'")
    private String licencia;

    @Column(name = "telefono", length = 15, columnDefinition = "varchar(15) COMMENT 'Teléfono del conductor'")
    private String telefono;

    @Column(name = "estado", columnDefinition = "enum('ACTIVO','INACTIVO') DEFAULT 'ACTIVO' COMMENT 'Estado actual del conductor'")
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(name = "creado_por", length = 50, columnDefinition = "varchar(50) COMMENT 'Usuario que creó el registro'")
    private String creadoPor;

    @Column(name = "fecha_creacion", nullable = false, updatable = false, columnDefinition = "timestamp NOT NULL DEFAULT current_timestamp() COMMENT 'Fecha de creación'")
    private Timestamp fechaCreacion;

    @Column(name = "modificado_por", length = 50, columnDefinition = "varchar(50) COMMENT 'Usuario que modificó el registro'")
    private String modificadoPor;

    @Column(name = "fecha_modificacion", columnDefinition = "timestamp NULL DEFAULT NULL ON UPDATE current_timestamp() COMMENT 'Fecha de última modificación'")
    private Timestamp fechaModificacion;

    // Enumeración para el campo estado
    public enum Estado {
        ACTIVO,
        INACTIVO
    }

    // Constructores
    public Conductor() {
        // Constructor vacío requerido por JPA
    }

    public Conductor(Integer codigoConductor, String nombre, String dpi, String licencia, String telefono,
            Estado estado, String creadoPor, Timestamp fechaCreacion, String modificadoPor,
            Timestamp fechaModificacion) {
        this.codigoConductor = codigoConductor;
        this.nombre = nombre;
        this.dpi = dpi;
        this.licencia = licencia;
        this.telefono = telefono;
        this.estado = estado;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.modificadoPor = modificadoPor;
        this.fechaModificacion = fechaModificacion;
    }

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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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

    // Método toString (opcional, para depuración)
    @Override
    public String toString() {
        return "Conductor{"
                + "codigoConductor=" + codigoConductor
                + ", nombre='" + nombre + '\''
                + ", dpi='" + dpi + '\''
                + ", licencia='" + licencia + '\''
                + ", telefono='" + telefono + '\''
                + ", estado=" + estado
                + ", creadoPor='" + creadoPor + '\''
                + ", fechaCreacion=" + fechaCreacion
                + ", modificadoPor='" + modificadoPor + '\''
                + ", fechaModificacion=" + fechaModificacion
                + '}';
    }
}
