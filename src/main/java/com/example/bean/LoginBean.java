package com.example.bean;

import com.example.model.Usuario;
import com.google.gson.Gson;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean {

    private String login;
    private String password;

    private Usuario usuarioAutenticado;

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String autenticar() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            Gson gson = new Gson();
            Usuario user = findByUsername(login);

            if (user == null) {
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Usuario o contraseña inválidos", null));
                return null; // permanece en login.xhtml
            }

            // OJO: aquí se compara texto plano. Si usas hash (recomendado), reemplaza por verificación del hash.
            if (!Objects.equals(user.getContrasena(), password)) {
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Usuario o contraseña inválidos", null));
                return null;
            }

            // OK: guardar en sesión y navegar
            this.usuarioAutenticado = user;
            ctx.getExternalContext().getSessionMap().put("usuario", user);

            System.out.println("LOGIN OK -> " + gson.toJson(user));

            // Navega a registroConductor.xhtml
            return "dashboard?faces-redirect=true";

        } catch (Exception e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
                    "Error al autenticar: " + e.getMessage(), null));
            return null;
        }
    }

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("loginPU");

    public Usuario findByUsername(String username) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.login  = :username", Usuario.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public String logout() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().invalidateSession();
        this.usuarioAutenticado = null;
        this.login = null;
        this.password = null;
        return "login?faces-redirect=true";
    }

}
