/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicio;

import com.entidades.Empleado;
import com.excepciones.RegistroJornadaException;
import com.entidades.RegistroHoras;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author user
 */
@Stateless
public class RegistroJornadaServicio implements RegistroJornadaServiceLocal{

    //@PersistenceContext(unit = "RegistroJornadasPU")
    @PersistenceContext(unitName = "Registro")
    private EntityManager em;
    
    @Override
    public void iniciarJornada(Integer idEmpleado) throws RegistroJornadaException {
       
       /*
        1.Validar idEmpleado no sea null
        2.Ver si no esta ya la jornada iniciada para el empleado
            Jornada iniciada esque hay un registro de jornada para el empleado con fecha inicio la de hoy 
            y fecha fin null
        3. si hay jornada iniciada enviar excepcion
        4. sino insert registro jornada fecha de hoy hora inicio y hora hoy y null hora fin
        */
       
       if(idEmpleado == null){
           throw new RegistroJornadaException("Debe indicar el id de empleado");
       }
       Query query = em.createNamedQuery("Registrohoras.findJornadasNoFinalizadasPorEmpleado");
       query.setParameter("idEmpleado", idEmpleado);
       Date hoy = new Date();
       query.setParameter("fechaJornada", hoy, TemporalType.DATE);
       List<RegistroHoras> resultado = query.getResultList();
       if(resultado.size() > 0){
           throw new RegistroJornadaException("La jornada ya esta iniciada");
       }
       
       RegistroHoras rh = new RegistroHoras();
       rh.setIdEmpleado(idEmpleado);
       rh.setFecha(hoy);
       rh.setHoraInicio(hoy);
       rh.setHoraFinal(null);
       em.persist(rh);
    }
    
    public void iniciarJornadaEmpleadoV2(Integer idEmpleado) throws RegistroJornadaException {
       
       /*
        1.Validar idEmpleado no sea null
        2.Ver si no esta ya la jornada iniciada para el empleado
            Jornada iniciada esque hay un registro de jornada para el empleado con fecha inicio la de hoy 
            y fecha fin null
        3. si hay jornada iniciada enviar excepcion
        4. sino insert registro jornada fecha de hoy hora inicio y hora hoy y null hora fin
        */
       
       if(idEmpleado == null){
           throw new RegistroJornadaException("Debe indicar el id de empleado");
       }
       Query query = em.createNamedQuery("Registrohoras.findJornadasNoFinalizadasPorEmpleado");
       query.setParameter("idEmpleado", idEmpleado);
       Date hoy = new Date();
       query.setParameter("fechaJornada", hoy, TemporalType.DATE);
       List<RegistroHoras> resultado = query.getResultList();
       if(resultado.size() > 0){
           throw new RegistroJornadaException("La jornada ya esta iniciada");
       }
       
       RegistroHoras rh = new RegistroHoras();
       rh.setIdEmpleado(idEmpleado);
       rh.setFecha(hoy);
       rh.setHoraInicio(hoy);
       rh.setHoraFinal(null);
       em.persist(rh);
    }    

    @Override
    public Collection<RegistroHoras> getAllregistroHorasPorEmpleado(Integer idEmpleado) {
        Query query = em.createNamedQuery("Registrohoras.findByIdEmpleado");
        query.setParameter("idEmpleado", idEmpleado);
        System.out.println(".... horas empleado " + idEmpleado);       
        return query.getResultList();
    }
    
    @Override
    public Collection<RegistroHoras> getAllregistroHorasPorEmpleadoV2(Empleado emp) {
        Query query = em.createNamedQuery("Registrohoras.findByIdEmpleado");
        query.setParameter("idEmpleado", emp.getIdEmpleado());    
        System.out.println("tamano:"+query.getResultList());
        return query.getResultList();
    }

    @Override
    public void finalizarJornada(Integer idEmpleado) throws RegistroJornadaException {
       /*
        1.Validar idEmpleado no sea null
        2.Ver si no esta ya la jornada iniciada para el empleado
            Jornada iniciada esque hay un registro de jornada para el empleado con fecha inicio la de hoy 
            y fecha fin null
        3. si hay jornada iniciada enviar excepcion
        4. sino insert registro jornada fecha de hoy hora inicio y hora hoy y null hora fin
        */
       
       if(idEmpleado == null){
           throw new RegistroJornadaException("Debe indicar el id de empleado");
       }
       Query query = em.createNamedQuery("Registrohoras.findJornadasNoFinalizadasPorEmpleado");
       query.setParameter("idEmpleado", idEmpleado);
       Date hoy = new Date();
       query.setParameter("fechaJornada", hoy, TemporalType.DATE);
       Collection<RegistroHoras> resultado = query.getResultList();
       RegistroHoras rh = null;
       
       for(RegistroHoras valor: resultado){
           if(valor.getHoraFinal() == null)
               rh = valor;
       }
       
       rh.setHoraFinal(hoy);
       em.merge(rh);
    }

    @Override
    public Collection<RegistroHoras> getAllRegistroHoras() {
        Query query = em.createNamedQuery("Registrohoras.findAll");
        return query.getResultList();
    }
    
}
