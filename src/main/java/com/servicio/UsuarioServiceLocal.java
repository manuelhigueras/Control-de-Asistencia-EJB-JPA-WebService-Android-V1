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
import javax.ejb.Local;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@Local
public interface UsuarioServiceLocal {
    
    public Usuario getUsuario(String email)  throws UsuarioNotFoundException;
    public Usuario getUsuarioActive();
    public void getUsuarioId(int id);
    public void alta(Usuario usrNuevo) throws UsuarioNotFoundException;
    public Collection<Usuario> getAllUsuarios();
    public void baja(Usuario usr) throws UsuarioNotFoundException;
    public void login(String email, String clave, HttpSession sesion) throws LoginException;
    public void activarSesion(String email);
    public void logout(HttpSession sesion);      
    public Usuario sesionActiva(String email) throws LoginException;
    public Usuario getUsuarioIdSpecial(String email);
    
}