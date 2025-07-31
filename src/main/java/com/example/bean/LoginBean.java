package com.example.bean;

import com.example.model.Usuario;
import com.google.gson.Gson;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean {

    private String login;
    private String password;

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
        Gson gson = new Gson();
        Usuario user = new Usuario();
        user = findByUsername(login);
        System.out.println("" + gson.toJson(user));
        return null;
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

}
