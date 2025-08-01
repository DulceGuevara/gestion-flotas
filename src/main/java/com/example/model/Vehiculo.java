package com.example.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "vehiculo",
        indexes = {
            @Index(name = "placa", columnList = "placa", unique = true) // Índice único para placa
        }) // Incluye el índice único definido en el script
public class Vehiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo", nullable = false, updatable = false)
    private Integer idVehiculo;

    @Column(name = "placa", length = 20, nullable = false, columnDefinition = "varchar(20) COMMENT 'Placa del vehículo'")
    private String placa;

    @Column(name = "marca", length = 50, nullable = false, columnDefinition = "varchar(50) COMMENT 'Marca del vehículo'")
    private String marca;

    @Column(name = "modelo", length = 50, nullable = false, columnDefinition = "varchar(50) COMMENT 'Modelo del vehículo'")
    private String modelo;

    @Column(name = "tipo", length = 50, nullable = false, columnDefinition = "varchar(50) COMMENT 'Tipo de vehículo (camión, sedán, etc.)'")
    private String tipo;

    @Column(name = "color", length = 30, columnDefinition = "varchar(30) COMMENT 'Color del vehículo'")
    private String color;

    @Column(name = "anio", columnDefinition = "int(11) COMMENT 'Año del modelo'")
    private Integer anio;

    @Column(name = "capacidad_carga_kg", columnDefinition = "int(11) COMMENT 'Capacidad de carga en kilogramos'")
    private Integer capacidadCargaKg;

    @Column(name = "gps_imei", length = 30, columnDefinition = "varchar(30) COMMENT 'Identificador del GPS (si aplica)'")
    private String gpsImei;

    @Column(name = "estado", columnDefinition = "enum('ACTIVO','INACTIVO','MANTENIMIENTO') DEFAULT 'ACTIVO' COMMENT 'Estado operativo'")
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(name = "creado_por", length = 50, columnDefinition = "varchar(50)")
    private String creadoPor;

    @Column(name = "fecha_creacion", nullable = false, updatable = false, columnDefinition = "timestamp NOT NULL DEFAULT current_timestamp()")
    private Timestamp fechaCreacion;

    @Column(name = "modificado_por", length = 50, columnDefinition = "varchar(50)")
    private String modificadoPor;

    @Column(name = "fecha_modificacion", nullable = false, columnDefinition = "timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()")
    private Timestamp fechaModificacion;

    // Enumeración para el campo estado
    public enum Estado {
        ACTIVO,
        INACTIVO,
        MANTENIMIENTO
    }

    // Constructores
    public Vehiculo() {
        // Constructor vacío requerido por JPA
    }

    public Vehiculo(Integer idVehiculo, String placa, String marca, String modelo, String tipo, String color,
            Integer anio, Integer capacidadCargaKg, String gpsImei, Estado estado, String creadoPor,
            Timestamp fechaCreacion, String modificadoPor, Timestamp fechaModificacion) {
        this.idVehiculo = idVehiculo;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
        this.color = color;
        this.anio = anio;
        this.capacidadCargaKg = capacidadCargaKg;
        this.gpsImei = gpsImei;
        this.estado = estado;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.modificadoPor = modificadoPor;
        this.fechaModificacion = fechaModificacion;
    }

    // Getters y Setters
    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getCapacidadCargaKg() {
        return capacidadCargaKg;
    }

    public void setCapacidadCargaKg(Integer capacidadCargaKg) {
        this.capacidadCargaKg = capacidadCargaKg;
    }

    public String getGpsImei() {
        return gpsImei;
    }

    public void setGpsImei(String gpsImei) {
        this.gpsImei = gpsImei;
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
        return "Vehiculo{"
                + "idVehiculo=" + idVehiculo
                + ", placa='" + placa + '\''
                + ", marca='" + marca + '\''
                + ", modelo='" + modelo + '\''
                + ", tipo='" + tipo + '\''
                + ", color='" + color + '\''
                + ", anio=" + anio
                + ", capacidadCargaKg=" + capacidadCargaKg
                + ", gpsImei='" + gpsImei + '\''
                + ", estado=" + estado
                + ", creadoPor='" + creadoPor + '\''
                + ", fechaCreacion=" + fechaCreacion
                + ", modificadoPor='" + modificadoPor + '\''
                + ", fechaModificacion=" + fechaModificacion
                + '}';
    }
}
