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
import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@Named(value = "loginMB")
@SessionScoped
public class LoginMB implements Serializable{

    private String emailAsignado;
    private String passwordAsignado;
    private Usuario usuarioEncontrado;  
    private Collection<Usuario> lista;
    
    @EJB
    private UsuarioServiceLocal usuarioService;    
    
    @PostConstruct
    public void iniciar(){
        System.out.println(".... iniciar UsuarioMB");
        this.lista = this.usuarioService.getAllUsuarios();
    }
    
    public LoginMB() {
        this.usuarioEncontrado = new Usuario();        
    }
    
    public String login() throws UsuarioNotFoundException{
        System.out.println("email:"+this.emailAsignado);
        System.out.println("password:"+this.passwordAsignado);
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) ctx.getExternalContext().getSession(true);
        try {
            this.usuarioService.activarSesion(emailAsignado);
            this.usuarioService.login(this.emailAsignado, this.passwordAsignado, sesion);
            ctx.addMessage(null,new FacesMessage("Welcome " + this.emailAsignado));            
            return "index";
        } catch (LoginException ex) {
            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
            this.emailAsignado = "";
            this.passwordAsignado = "";

            //mensaje de error
            FacesMessage msg = new FacesMessage(ex.getMessage());
            //ctx.addMessage("formLogin:pwd", msg);
            ctx.addMessage(null, msg);
            return "login";
        }
    }
    
    public Usuario datosSession(){
        Usuario user = null;
        for(Usuario usr: lista){
            if(usr.getSesion() == true){
                user = usr;
                break;
            }
        }
        return user;
    }
    
    public Usuario getUsuarioId(){
        Usuario usuario = this.usuarioService.getUsuarioIdSpecial(emailAsignado);
        return usuario;
    }
    
    public String logout() throws LoginException{
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) ctx.getExternalContext().getSession(true);
        Usuario usr = datosSession();
        this.usuarioService.activarSesion(emailAsignado);
        //this.usuarioService.desactivarSesion(usr);
        this.usuarioService.logout(sesion);
        return "login?faces-redirect=true";
    }

    public String getEmailAsignado() {
        return emailAsignado;
    }

    public void setEmailAsignado(String emailAsignado) {
        this.emailAsignado = emailAsignado;
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
