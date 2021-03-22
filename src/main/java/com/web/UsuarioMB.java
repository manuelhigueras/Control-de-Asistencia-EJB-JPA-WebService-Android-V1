/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web;

import com.entidades.Usuario;
import com.excepciones.LoginException;
import com.excepciones.UsuarioNotFoundException;
import com.servicio.UsuarioServiceLocal;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@Named(value = "usuarioMB")
@RequestScoped
public class UsuarioMB implements Serializable{

    private String emailAsignado;
    private String passwordAsignado;
    private Usuario usuarioEncontrado;
    private Collection<Usuario> lista;
    
    @EJB
    private UsuarioServiceLocal usuarioService;
    
    @PostConstruct
    public void iniciar(){
        this.lista = this.usuarioService.getAllUsuarios();
    }
    
    public UsuarioMB() {
    }
    
    public String ventanaReg(){
        return "registerUsuario?faces-redirect=true";
    }
    
    public String crearUsuario(){
        //System.out.println("creando usuario " + this.usuarioEncontrado);
        //this.usuarioEncontrado.setIdUsuario(1);
        
        FacesContext fc = FacesContext.getCurrentInstance();  
        Usuario user = new Usuario(this.emailAsignado, this.passwordAsignado, Boolean.TRUE);
        try {
            this.usuarioService.alta(user);
        }
        catch(UsuarioNotFoundException ex){
            fc.addMessage(null, new FacesMessage("No se pudo modificar. " + ex.getMessage()));  
        }
        catch(Exception e){
            fc.addMessage(null, new FacesMessage("Error no controlado. " + e.getMessage())); 
            e.printStackTrace();
        }
        return "login?faces-redirect=true";
    }    

    public String getEmailAsignado() {
        return emailAsignado;
    }

    public void setEmailAsignado(String text) {
        this.emailAsignado = text;
    }

    public String getPasswordAsignado() {
        return passwordAsignado;
    }

    public void setPasswordAsignado(String passwordAsignado) {
        this.passwordAsignado = passwordAsignado;
    }

    public Usuario getUsuarioEncontrado() {
        return usuarioEncontrado;
    }

    public void setUsuarioEncontrado(Usuario usuarioEncontrado) {
        this.usuarioEncontrado = usuarioEncontrado;
    }
    
}
