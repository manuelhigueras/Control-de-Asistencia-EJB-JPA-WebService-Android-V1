
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicioWRF;

import com.entidades.RegistroHoras;
import com.excepciones.RegistroJornadaException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author Manuel
 */
@Stateless
public class RegistroJornadaServicioV2 implements RegistroJornadaServicioLocalV2{

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
    public Collection<RegistroHoras> getAllregistroHorasPorEmpleado(Integer idEmpleado) {
        Query query = em.createNamedQuery("Registrohoras.findByIdEmpleado");
        query.setParameter("idEmpleado", idEmpleado);
        System.out.println(".... horas empleado " + idEmpleado);       
        return query.getResultList();
    }
    
}
