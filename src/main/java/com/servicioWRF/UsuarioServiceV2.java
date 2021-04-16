/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicioWRF;

import com.entidades.Usuario;
import com.excepciones.UsuarioNotFoundException;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Manuel
 */
@Stateless
public class UsuarioServiceV2 implements UsuarioServiceLocalV2{
    
    @PersistenceContext(unitName = "Registro")
    private EntityManager em;

    @Override
    public void alta(Usuario usrNuevo) throws UsuarioNotFoundException {
        try{
            if(em.contains(usrNuevo) == false)
                em.persist(usrNuevo);
            else
                throw new UsuarioNotFoundException("Existe ese usuario");
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
        usr.setActivo(Boolean.FALSE);
        em.merge(usr);
    }
        
}
