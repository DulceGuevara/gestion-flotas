package com.example.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "asignacion_conductor_vehiculo",
        indexes = {
            @Index(name = "codigo_conductor", columnList = "codigo_conductor"),
            @Index(name = "id_vehiculo", columnList = "id_vehiculo"),
            @Index(name = "fk_asignacion_ruta", columnList = "id_ruta")
        }) // Incluye los índices definidos en el script
public class AsignacionConductorVehiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignacion", nullable = false)
    private Integer idAsignacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_conductor", nullable = false)
    private Conductor conductor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehiculo", nullable = false)
    private Vehiculo vehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ruta", nullable = true) // nullable = true porque DEFAULT NULL
    private Rutas ruta;

    @Column(name = "fecha_asignacion", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp fechaAsignacion;

    @Column(name = "fecha_finalizacion")
    private Timestamp fechaFinalizacion;

    // Constructores
    public AsignacionConductorVehiculo() {
        // Constructor vacío requerido por JPA
    }

    public AsignacionConductorVehiculo(Integer idAsignacion, Conductor conductor, Vehiculo vehiculo, Rutas ruta,
            Timestamp fechaAsignacion, Timestamp fechaFinalizacion) {
        this.idAsignacion = idAsignacion;
        this.conductor = conductor;
        this.vehiculo = vehiculo;
        this.ruta = ruta;
        this.fechaAsignacion = fechaAsignacion;
        this.fechaFinalizacion = fechaFinalizacion;
    }

    // Getters y Setters
    public Integer getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(Integer idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Rutas getRuta() {
        return ruta;
    }

    public void setRuta(Rutas ruta) {
        this.ruta = ruta;
    }

    public Timestamp getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Timestamp fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Timestamp getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Timestamp fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    // Método toString (opcional, para depuración)
    @Override
    public String toString() {
        return "AsignacionConductorVehiculo{"
                + "idAsignacion=" + idAsignacion
                + ", conductor=" + conductor
                + ", vehiculo=" + vehiculo
                + ", ruta=" + ruta
                + ", fechaAsignacion=" + fechaAsignacion
                + ", fechaFinalizacion=" + fechaFinalizacion
                + '}';
    }
}
