/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicio;

import com.entidades.Usuario;
import com.excepciones.LoginException;
import com.excepciones.UsuarioNotFoundException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

@Stateless
public class UsuarioService implements UsuarioServiceLocal{

    @PersistenceContext(unitName = "Registro")
    private EntityManager em;
    
    @Override
    public void getUsuarioId(int id){
        try {
            Usuario usr = em.find(Usuario.class, id);
            baja(usr);
        } catch (UsuarioNotFoundException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Usuario getUsuarioIdSpecial(String email){
        Query query = em.createNamedQuery("Usuario.findByEmail");
        query.setParameter("email", email);
        Usuario usr = (Usuario) query.getSingleResult();
        return usr;
    }
    
    @Override
    public Usuario getUsuarioActive(){
        Query query = em.createNamedQuery("Usuario.findUserSpecial");
        Usuario usr = (Usuario) query.getSingleResult();
        return usr;        
    }
    
    @Override
    public Usuario getUsuario(String email) throws UsuarioNotFoundException {
        Query query = em.createNamedQuery("Usuario.findByEmail");
        query.setParameter("email", email);
        Usuario usr = (Usuario) query.getSingleResult();
        return usr;
    }

    @Override
    public void alta(Usuario usrNuevo) throws UsuarioNotFoundException {
        try{
            em.persist(usrNuevo);
        }
        catch(javax.persistence.NoResultException ex){
            throw new UsuarioNotFoundException("No se pudo crear la tarea");
        }
    }

    @Override
    public Collection<Usuario> getAllUsuarios() {
        Query query = em.createNamedQuery("Usuario.findAll");
        return query.getResultList();
    }

    @Override
    public void baja(Usuario usr) throws UsuarioNotFoundException {
        //this.getUsuario(usr.getIdUsuario());
        usr.setActivo(Boolean.FALSE);
        em.merge(usr);
    }

    @Override
    public void login(String email, String clave, HttpSession sesion) throws LoginException {
        //En bd ver si existe y coincide email y clave
        Collection<Usuario> usuarios = this.getAllUsuarios();
        Usuario usrEncontrado = null;
        for(Usuario u: usuarios){
           if(u.getEmail().equals(email)){
               usrEncontrado = u;
               activarSesion(email);
               break;
           }
        }
        //si existe añadir a sesion
        //sino lanzo exception
        if(usrEncontrado == null){
            throw new LoginException("El usuario no existe. Debe registrarse.");
        }
        else{
            if(usrEncontrado.getPassword().equals(clave)){
                sesion.setAttribute("usuario", usrEncontrado);
            }
            else{
                throw new LoginException("Clave no valida");
            }
        } 
    }
    
    @Override
    public void activarSesion(String email){
        try {
            Usuario usrBD = this.getUsuario(email);
            if(usrBD.getSesion() == false)
                usrBD.setSesion(Boolean.TRUE);
            else
                usrBD.setActivo(Boolean.FALSE);
            em.merge(usrBD);
        } catch (UsuarioNotFoundException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void logout(HttpSession sesion) {
        sesion.invalidate();
    }

    @Override
    public Usuario sesionActiva(String email) throws LoginException {
        Usuario u = em.find(Usuario.class, email);
        if(u == null){
            throw new LoginException("No existe el usuario solicitado");
        }
        return u;  
    }
    
}
