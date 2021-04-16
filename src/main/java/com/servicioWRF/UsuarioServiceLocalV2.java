/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicioWRF;

import com.entidades.Usuario;
import com.excepciones.UsuarioNotFoundException;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Manuel
 */
@Local
public interface UsuarioServiceLocalV2 {
    public void alta(Usuario usrNuevo) throws UsuarioNotFoundException;
    public Collection<Usuario> getAllUsuarios();
    public void baja(Usuario usr) throws UsuarioNotFoundException;
}
