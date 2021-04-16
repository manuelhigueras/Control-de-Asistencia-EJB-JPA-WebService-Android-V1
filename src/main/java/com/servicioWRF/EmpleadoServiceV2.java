/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicioWRF;

import com.entidades.Empleado;
import com.entidades.Usuario;
import com.excepciones.EmpleadoNotFoundException;
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
public class EmpleadoServiceV2 implements EmpleadoServiceLocalV2{

    @PersistenceContext(unitName = "Registro")
    private EntityManager em;    
    
    @Override
    public void altaEmpleado(Empleado empleado) throws EmpleadoNotFoundException {
        try{
            if(em.contains(empleado) != true)
                em.persist(empleado);
            else
                throw new EmpleadoNotFoundException("Existe ese empleado");
        }
        catch(javax.persistence.NoResultException ex){
            throw new EmpleadoNotFoundException("No se pudo crear la tarea");
        }   }

    @Override
    public Collection<Empleado> getAllEmpleados() {
        Query query = em.createNamedQuery("Empleado.findAll");
        return query.getResultList();
    }

    @Override
    public void baja(Empleado emp, int id) throws EmpleadoNotFoundException {
        Empleado empleado = emp;
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

    @Override
    public void modificar(Empleado empld) {
        Empleado empleado = empld;
        em.merge(empleado);
    }
    
}
