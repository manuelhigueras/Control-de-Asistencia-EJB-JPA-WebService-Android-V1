/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webRestFull;

import WebService.*;
import com.entidades.Empleado;
import com.servicio.EmpleadoServiceLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author user
 */
@Stateless
@Path("empleado")
public class EmpleadoRestFullWS{

    @EJB
    private EmpleadoServiceLocal service;
    
//    @GET
//    @Path("alta")
//    @Consumes({MediaType.APPLICATION_JSON})    
//    public void altaEmpleado(){
//        
//    }
//        
//    @GET
//    @Path("baja")
//    @Consumes({MediaType.APPLICATION_JSON})  
//    public void bajaEmpleado(){
//        
//    }
    
//    @PUT
//    @Path("modificar/{}")//ID DEL EMPLEADO
//    @Consumes({MediaType.APPLICATION_JSON})
//    public void editEmpleado(@PathParam("id") Integer id) {
//        
//    }    
    
//    @GET
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,})
//    public List<Empleado> findAll() {
//        List<Empleado> lista = (List<Empleado>) service.getAllEmpleados();
//        System.out.println("total registros " + lista.size());
//        return lista;
//    }    
    
    /*
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Empleado entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Empleado entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Empleado find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Empleado> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Empleado> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    */
}
