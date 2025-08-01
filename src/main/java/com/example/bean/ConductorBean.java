package com.example.bean;

import com.example.model.Conductor;
import com.example.model.Usuario;
import com.google.gson.Gson;
import java.sql.Timestamp;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

@ManagedBean(name = "conductorBean")
@RequestScoped
public class ConductorBean {

    private String nombre;
    private String dpi;
    private String licencia;
    private String telefono;

    private String estado;

    public double getLatitudInicio() {
        return 14.6349;
    }      // ejemplo: Guatemala

    public double getLongitudInicio() {
        return -90.5069;
    }

    public double getLatitudFin() {
        return 14.5592;
    }         // ejemplo: Villa Nueva

    public double getLongitudFin() {
        return -90.7356;
    }

    private Conductor conductor;

    private EntityManagerFactory emf;

    @PostConstruct
    public void init() {
        conductor = new Conductor();
        emf = Persistence.createEntityManagerFactory("loginPU");
    }

    public String autenticar() {
        Gson gson = new Gson();
        Usuario user = new Usuario();
        // user = findByUsername(login);
        // System.out.println("" + gson.toJson(user));
        return null;
    }

    public void guardar() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            conductor.setCreadoPor("admin"); // ejemplo fijo
            conductor.setModificadoPor("admin");
            conductor.setNombre(getNombre());

            conductor.setTelefono(getTelefono());
            conductor.setLicencia(getLicencia());
            conductor.setDpi(getDpi());
            conductor.setEstado(Conductor.Estado.valueOf(estado));
            conductor.setFechaCreacion(new Timestamp(System.currentTimeMillis()));

            em.persist(conductor);
            em.getTransaction().commit();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Conductor guardado exitosamente", null));

            conductor = new Conductor(); // limpiar formulario

        } catch (Exception e) {
            em.getTransaction().rollback();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar conductor: " + e.getMessage(), null));
        } finally {
            em.close();
        }
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * @return the licencia
     */
    public String getLicencia() {
        return licencia;
    }

    /**
     * @param licencia the licencia to set
     */
    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

}
