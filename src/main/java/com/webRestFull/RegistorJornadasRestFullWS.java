/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webRestFull;

import com.excepciones.RegistroJornadaException;
import com.servicio.RegistroJornadaServiceLocal;
import com.entidades.RegistroHoras;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author user
 */
@Stateless
@Path("jornadas")
public class RegistorJornadasRestFullWS {
     
    @EJB 
    private RegistroJornadaServiceLocal servicio;
    
    @GET
    @Path("empleado/{idEmpleado}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,})
    public List<RegistroHoras> find(@PathParam("idEmpleado") Integer id) {
        List<RegistroHoras> lista = (List<RegistroHoras>) servicio.getAllregistroHorasPorEmpleado(id);
        System.out.println("total registros " + lista.size());
        return lista;
    }
    
    @GET
    @Path("iniciarJornada/empleado/{idEmpleado}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,})
    public Respuesta iniciarJornada(@PathParam("idEmpleado") Integer id) throws RegistroJornadaException{
        Respuesta respuesta = new Respuesta();
        //String resultado = "{mensaje : 'ok'}";
        try{
            servicio.iniciarJornada(id);
            respuesta.setSinError(true);
            respuesta.setMensaje("Jornada Iniciada");
        }
        catch(RegistroJornadaException ex){
            respuesta.setMensaje(ex.getMessage());
            respuesta.setSinError(false);
            //resultado = "{" +ex.getMessage() + "}";
        }
        //return resultado;
        return respuesta;
    }
    
    @GET
    @Path("finalizarJornada/empleado/{idEmpleado}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,})
    public String finalizarJornada(@PathParam("idEmpleado") Integer id) throws RegistroJornadaException{
        String resultado = "{mensaje : 'ok'}";
        try{
            //servicio.finalizarJornada(id);
            servicio.finalizarJornada(id);
        }
        catch(RegistroJornadaException ex){
            resultado = "{" +ex.getMessage() + "}";
        }
        return resultado;
    }
    
    
}
