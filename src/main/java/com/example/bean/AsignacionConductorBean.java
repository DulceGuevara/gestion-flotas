package com.example.bean;

import com.example.bean.dto.AsignacionDTO;
import com.example.model.AsignacionConductorVehiculo;
import com.example.model.Conductor;
import com.example.model.Rutas;
import com.example.model.Usuario;
import com.example.model.Vehiculo;
import com.google.gson.Gson;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

@ManagedBean(name = "asignacionConductorBean")
@ViewScoped
public class AsignacionConductorBean implements Serializable {

    private Conductor conductorSeleccionado;
    private Vehiculo vehiculoSeleccionado;
    private Rutas rutaSeleccionada;

    private List<Conductor> conductores;
    private List<Vehiculo> vehiculos;
    private List<Rutas> rutas;

    private EntityManagerFactory emf;

    @PostConstruct
    public void init() {
        emf = Persistence.createEntityManagerFactory("loginPU");
        conductores = obtenerConductoresDesdeBD();
        vehiculos = obtenerVehiculosDesdeBD();
        rutas = obtenerRutasDesdeBD();
    }

    public void asignar() {
        AsignacionConductorVehiculo asignacion = new AsignacionConductorVehiculo();
        asignacion.setConductor(conductorSeleccionado);
        asignacion.setVehiculo(vehiculoSeleccionado);
        asignacion.setRuta(rutaSeleccionada);
        asignacion.setFechaAsignacion(new Timestamp(System.currentTimeMillis()));

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(asignacion);
            em.getTransaction().commit();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Asignación guardada correctamente"));
        } catch (Exception e) {
            em.getTransaction().rollback();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar: " + e.getMessage(), null));
            System.out.println("Error" + e);
        } finally {
            em.close();
        }
    }

    private List<Conductor> obtenerConductoresDesdeBD() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Conductor c", Conductor.class).getResultList();
        } finally {
            em.close();
        }
    }

    private List<Vehiculo> obtenerVehiculosDesdeBD() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT v FROM Vehiculo v", Vehiculo.class).getResultList();
        } finally {
            em.close();
        }
    }

    private List<Rutas> obtenerRutasDesdeBD() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT r FROM Rutas r", Rutas.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Conductor getConductorSeleccionado() {
        return conductorSeleccionado;
    }

    public void setConductorSeleccionado(Conductor conductorSeleccionado) {
        this.conductorSeleccionado = conductorSeleccionado;
    }

    public Vehiculo getVehiculoSeleccionado() {
        return vehiculoSeleccionado;
    }

    public void setVehiculoSeleccionado(Vehiculo vehiculoSeleccionado) {
        this.vehiculoSeleccionado = vehiculoSeleccionado;
    }

    public Rutas getRutaSeleccionada() {
        return rutaSeleccionada;
    }

    public void setRutaSeleccionada(Rutas rutaSeleccionada) {
        this.rutaSeleccionada = rutaSeleccionada;
    }

    public List<Conductor> getConductores() {
        return conductores;
    }

    public void setConductores(List<Conductor> conductores) {
        this.conductores = conductores;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public List<Rutas> getRutas() {
        return rutas;
    }

    public void setRutas(List<Rutas> rutas) {
        this.rutas = rutas;
    }

}
