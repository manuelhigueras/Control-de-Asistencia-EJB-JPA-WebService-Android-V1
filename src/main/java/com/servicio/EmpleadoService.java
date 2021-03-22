/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicio;

import com.entidades.Empleado;
import com.entidades.Usuario;
import com.excepciones.EmpleadoNotFoundException;
import com.excepciones.UsuarioNotFoundException;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author user
 */
@Stateless
public class EmpleadoService implements EmpleadoServiceLocal{

    @PersistenceContext(unitName = "Registro")
    private EntityManager em;
    
    @Inject
    private UsuarioServiceLocal userServer;
       
    @Override
    public void altaEmpleado(Empleado empleadoNuevo) throws EmpleadoNotFoundException {
        try{
            if(em.contains(empleadoNuevo) != true)
                em.persist(empleadoNuevo);
            else
                throw new EmpleadoNotFoundException("Existe ese empleado");
        }
        catch(javax.persistence.NoResultException ex){
            throw new EmpleadoNotFoundException("No se pudo crear la tarea");
        }        
    }

    @Override
    public Collection<Empleado> getAllEmpleados() {
        Query query = em.createNamedQuery("Empleado.findAll");
        return query.getResultList();
    }

    @Override
    public Empleado getEmpleadoId(Integer idEmpleado) throws EmpleadoNotFoundException {
        Usuario user = userServer.getUsuarioActive();
        Query query = em.createNamedQuery("Empleado.findByIdUsuario");
        query.setParameter("usuario", user.getIdUsuario());
        
        Empleado empleado = (Empleado) query.getSingleResult();
        
        empleado.setSesion(Boolean.TRUE);
        em.merge(empleado);
        //Query query = em.createNamedQuery("Usuario.findUserSpecial");
        return empleado;
    }
    
    @Override
    public Empleado getEmpleadoIdSpecial(Usuario usuario){
        Query query = em.createNamedQuery("Empleado.findByIdUsuario");
        query.setParameter("usuario", usuario);
        Empleado empleado = (Empleado) query.getSingleResult();
        return empleado;
    }
    
    @Override
    public Empleado getEmpleadoNombre(String nombre){
        Query query = em.createNamedQuery("Empleado.findByNombre");
        System.out.println("nombre"+nombre);
        query.setParameter("nombre", nombre);
        Empleado emp = (Empleado) query.getSingleResult();
        return emp;
    }

    @Override
    public void baja(Empleado empld, int id){
        Empleado empleado = empld;
        //System.out.println("puntero:"+empleado);
        if(empleado.getEstado()){
            empleado.setEstado(Boolean.FALSE);
            empleado.setSesion(Boolean.FALSE);
        }
        else{
            empleado.setEstado(Boolean.TRUE);
            empleado.setSesion(Boolean.TRUE);
        }    
        System.out.println("id:"+id);
        Usuario user = em.find(Usuario.class, id);
        if(user.getActivo())
            user.setActivo(Boolean.FALSE);
        else
            user.setActivo(Boolean.TRUE);
        empleado.setUsuario(user);
        em.merge(empleado);  
    }
    
}
