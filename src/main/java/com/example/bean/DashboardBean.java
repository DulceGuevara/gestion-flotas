// package gt.gob.sat.gestionflotas.view;
package com.example.bean;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ManagedBean(name = "dashboardBean")
@RequestScoped
public class DashboardBean implements Serializable {

    private transient EntityManagerFactory emf;

    private Integer conductoresEnRuta = 0;
    private Integer conductoresActivos = 0;
    private Integer viajesFinalizados = 0;

    @PostConstruct
    public void init() {
        // Usa el mismo PU que tu otro bean
        emf = Persistence.createEntityManagerFactory("loginPU");
        cargarKPIs();
    }

    @PreDestroy
    public void destroy() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    /** Refresca las 3 KPIs desde la BD (llámalo también desde p:poll si quieres auto-update). */
    public void cargarKPIs() {
        EntityManager em = emf.createEntityManager();
        try {
            // === 1) Conductores en ruta ===
            // Definición típica: asignación con ruta y sin fecha de fin (aún en curso).
            Long enRuta;
            try {
                enRuta = em.createQuery(
                    "SELECT COUNT(DISTINCT a.conductor) " +
                    "FROM AsignacionConductorVehiculo a " +
                    "WHERE a.ruta IS NOT NULL AND a.fechaFinalizacion IS NULL", Long.class)
                    .getSingleResult();
            } catch (Exception exSinFechaFin) {
                // Fallback si no existe fechaFin: cuenta asignaciones con ruta (ajusta a tu regla de negocio)
                enRuta = em.createQuery(
                    "SELECT COUNT(DISTINCT a.conductor) " +
                    "FROM AsignacionConductorVehiculo a " +
                    "WHERE a.ruta IS NOT NULL", Long.class)
                    .getSingleResult();
            }

            // === 2) Conductores activos ===
            Long activos;
            try {
                activos = em.createQuery(
                    "SELECT COUNT(c) FROM Conductor c WHERE c.estado = :est", Long.class)
                    .setParameter("est", "ACTIVO")
                    .getSingleResult();
            } catch (Exception exSinEstado) {
                // Fallback si no existe 'estado'
                activos = em.createQuery("SELECT COUNT(c) FROM Conductor c", Long.class)
                            .getSingleResult();
            }

            // === 3) Viajes finalizados (últimas 24 horas) ===
            Long finalizados;
            LocalDateTime desde = LocalDateTime.now().minusHours(24);
            Timestamp tsDesde = Timestamp.valueOf(desde);
            try {
                finalizados = em.createQuery(
                    "SELECT COUNT(a) FROM AsignacionConductorVehiculo a " +
                    "WHERE a.estado = :est AND a.fechaFinalizacion >= :desde", Long.class)
                    .setParameter("est", "FINALIZADO")
                    .setParameter("desde", tsDesde)
                    .getSingleResult();
            } catch (Exception exGenerico) {
                // Fallback si no tienes 'estado': cuenta por fecha de fin
                finalizados = em.createQuery(
                    "SELECT COUNT(a) FROM AsignacionConductorVehiculo a " +
                    "WHERE a.fechaFinalizacion >= :desde", Long.class)
                    .setParameter("desde", tsDesde)
                    .getSingleResult();
            }

            // Pasar a Integer para el front
            conductoresEnRuta  = enRuta.intValue();
            conductoresActivos = activos.intValue();
            viajesFinalizados  = finalizados.intValue();

        } finally {
            em.close();
        }
    }

    // --- Navegación al hacer clic en las KPIs (si las hiciste clicables) ---
    public String irConductoresEnRuta()  { return "rutasEnCurso.xhtml?faces-redirect=true"; }
    public String irConductoresActivos() { return "conductores.xhtml?faces-redirect=true&amp;activos=1"; }
    public String irViajesFinalizados()  { return "viajes.xhtml?faces-redirect=true&amp;estado=FINALIZADO"; }

    // --- Getters/Setters ---
    public Integer getConductoresEnRuta()     { return conductoresEnRuta; }
    public void setConductoresEnRuta(Integer v){ this.conductoresEnRuta = v; }

    public Integer getConductoresActivos()    { return conductoresActivos; }
    public void setConductoresActivos(Integer v){ this.conductoresActivos = v; }

    public Integer getViajesFinalizados()     { return viajesFinalizados; }
    public void setViajesFinalizados(Integer v){ this.viajesFinalizados = v; }
}