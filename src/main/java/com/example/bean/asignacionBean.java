package com.example.bean;

import com.example.bean.dto.AsignacionDTO;
import com.example.model.Conductor;
import com.example.model.Usuario;
import com.google.gson.Gson;
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

@ManagedBean(name = "asignacionBean")
@ViewScoped
public class AsignacionBean {

    private String nombreConductor;
    private String dpi;
    private String placaVehiculo;
    private String marcaVehiculo;
    private String nombreRuta;
    private Timestamp fechaAsignacion;
    private String gpsImei;
    private BigDecimal latitudInicio;
    private BigDecimal longitudInicio;
    private BigDecimal latitudFin;
    private BigDecimal longitudFin;
    private Integer idAsignacionSeleccionada;
    private List<AsignacionDTO> asignacion;
    private EntityManagerFactory emf;
    private int idAsignacion;

    @PostConstruct
    public void init() {
        emf = Persistence.createEntityManagerFactory("loginPU");
    }

    public void cargarAsignacion() {
        EntityManager em = emf.createEntityManager();
        try {
            this.setAsignacion(em.createQuery(
                    "SELECT new com.example.bean.dto.AsignacionDTO("
                    + "a.idAsignacion, "
                    + "c.nombre, c.dpi, "
                    + "v.placa, v.marca, v.gpsImei, "
                    + "r.nombreRuta, r.latitudInicio, r.longitudInicio, r.latitudFin, r.longitudFin, "
                    + "a.fechaAsignacion"
                    + ") "
                    + "FROM AsignacionConductorVehiculo a "
                    + "JOIN a.conductor c "
                    + "JOIN a.vehiculo v "
                    + "JOIN a.ruta r",
                    AsignacionDTO.class
            ).getResultList());

        } catch (Exception e) {
            this.setAsignacion(new ArrayList<>());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al cargar asignaciones: " + e.getMessage(), null));
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    private AsignacionDTO asignacion1; // debe existir como atributo en el bean
    Gson gson = new Gson();

    public void findDatosAsignacionById() {
        EntityManager em = emf.createEntityManager(); // o emf.createEntityManager();
        try {
            if (idAsignacionSeleccionada == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione una asignación primero", null));
                return;
            }

            this.asignacion1 = em.createQuery(
                    "SELECT new com.example.bean.dto.AsignacionDTO("
                    + "a.idAsignacion, "
                    + "c.nombre, c.dpi, "
                    + "v.placa, v.marca, v.gpsImei, "
                    + "r.nombreRuta, r.latitudInicio, r.longitudInicio, r.latitudFin, r.longitudFin, "
                    + "a.fechaAsignacion) "
                    + "FROM AsignacionConductorVehiculo a "
                    + "JOIN a.conductor c "
                    + "JOIN a.vehiculo v "
                    + "JOIN a.ruta r "
                    + "WHERE a.idAsignacion = :id",
                    AsignacionDTO.class)
                    .setParameter("id", idAsignacionSeleccionada)
                    .getSingleResult();

            System.out.println("Resultado de asignacion1" + gson.toJson(asignacion1));

            setLongitudFin(asignacion1.getLongitudFin());
            setLongitudInicio(asignacion1.getLongitudInicio());
            setLatitudInicio(asignacion1.getLatitudInicio());
            setLatitudFin(asignacion1.getLatitudFin());

        } catch (NoResultException e) {
            this.asignacion = null;
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "No se encontró asignación", null));
        } catch (Exception e) {
            this.asignacion1 = null;
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + e.getMessage(), null));
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * @return the nombreConductor
     */
    public String getNombreConductor() {
        return nombreConductor;
    }

    /**
     * @param nombreConductor the nombreConductor to set
     */
    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }

    /**
     * @return the dpi
     */
    public String getDpi() {
        return dpi;
    }

    /**
     * @param dpi the dpi to set
     */
    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    /**
     * @return the placaVehiculo
     */
    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    /**
     * @param placaVehiculo the placaVehiculo to set
     */
    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    /**
     * @return the marcaVehiculo
     */
    public String getMarcaVehiculo() {
        return marcaVehiculo;
    }

    /**
     * @param marcaVehiculo the marcaVehiculo to set
     */
    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

    /**
     * @return the nombreRuta
     */
    public String getNombreRuta() {
        return nombreRuta;
    }

    /**
     * @param nombreRuta the nombreRuta to set
     */
    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
    }

    /**
     * @return the fechaAsignacion
     */
    public Timestamp getFechaAsignacion() {
        return fechaAsignacion;
    }

    /**
     * @param fechaAsignacion the fechaAsignacion to set
     */
    public void setFechaAsignacion(Timestamp fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    /**
     * @return the gpsImei
     */
    public String getGpsImei() {
        return gpsImei;
    }

    /**
     * @param gpsImei the gpsImei to set
     */
    public void setGpsImei(String gpsImei) {
        this.gpsImei = gpsImei;
    }

    /**
     * @return the latitudInicio
     */
    public BigDecimal getLatitudInicio() {
        return latitudInicio;
    }

    /**
     * @param latitudInicio the latitudInicio to set
     */
    public void setLatitudInicio(BigDecimal latitudInicio) {
        this.latitudInicio = latitudInicio;
    }

    /**
     * @return the longitudInicio
     */
    public BigDecimal getLongitudInicio() {
        return longitudInicio;
    }

    /**
     * @param longitudInicio the longitudInicio to set
     */
    public void setLongitudInicio(BigDecimal longitudInicio) {
        this.longitudInicio = longitudInicio;
    }

    /**
     * @return the latitudFin
     */
    public BigDecimal getLatitudFin() {
        return latitudFin;
    }

    /**
     * @param latitudFin the latitudFin to set
     */
    public void setLatitudFin(BigDecimal latitudFin) {
        this.latitudFin = latitudFin;
    }

    /**
     * @return the longitudFin
     */
    public BigDecimal getLongitudFin() {
        return longitudFin;
    }

    /**
     * @param longitudFin the longitudFin to set
     */
    public void setLongitudFin(BigDecimal longitudFin) {
        this.longitudFin = longitudFin;
    }

    /**
     * @return the idAsignacionSeleccionada
     */
    public Integer getIdAsignacionSeleccionada() {
        return idAsignacionSeleccionada;
    }

    /**
     * @param idAsignacionSeleccionada the idAsignacionSeleccionada to set
     */
    public void setIdAsignacionSeleccionada(Integer idAsignacionSeleccionada) {
        this.idAsignacionSeleccionada = idAsignacionSeleccionada;
    }

    /**
     * @return the asignacion
     */
    public List<AsignacionDTO> getAsignacion() {
        return asignacion;
    }

    /**
     * @param asignacion the asignacion to set
     */
    public void setAsignacion(List<AsignacionDTO> asignacion) {
        this.asignacion = asignacion;
    }

    /**
     * @return the idAsignacion
     */
    public int getIdAsignacion() {
        return idAsignacion;
    }

    /**
     * @param idAsignacion the idAsignacion to set
     */
    public void setIdAsignacion(int idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public AsignacionDTO getAsignacion1() {
        return asignacion1;
    }

    public void setAsignacion1(AsignacionDTO asignacion1) {
        this.asignacion1 = asignacion1;
    }

}
