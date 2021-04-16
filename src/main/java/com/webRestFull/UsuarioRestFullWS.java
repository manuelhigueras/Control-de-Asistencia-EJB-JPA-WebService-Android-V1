/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webRestFull;

import WebService.*;
import com.entidades.Usuario;
import com.servicioWRF.UsuarioServiceLocalV2;
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
@Path("usuario")
public class UsuarioRestFullWS{

    @EJB
    private UsuarioServiceLocalV2 service;
    
    @POST
    @Path("create")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Respuesta crearUsuario(Usuario usr){
        Respuesta resp = new Respuesta();
        try{
            resp.setMensaje("Creacion exitosa");
            this.service.alta(usr);
            resp.setSinError(Boolean.TRUE);
        }catch(Exception ex){
            resp.setMensaje("Error");
            resp.setSinError(Boolean.FALSE);
        }
        return resp;
    }

    @POST
    @Path("baja")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})    
    public Respuesta bajaUsuario(Usuario usr){
        Respuesta resp = new Respuesta();
        try{
            resp.setMensaje("Creacion exitosa");
            this.service.baja(usr);
            resp.setSinError(Boolean.TRUE);
        }catch(Exception ex){
            resp.setMensaje("Error");
            resp.setSinError(Boolean.FALSE);
        }
        return resp;        
    }
    
//    @GET
//    @Path("lista")
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
//    public List<Usuario> allUser(){
//        List<Usuario> lista = (List<Usuario>) this.service.getAllUsuarios();
//        return lista;
//    }
    
/*
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Usuario entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Usuario entity) {
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
    public Usuario find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
