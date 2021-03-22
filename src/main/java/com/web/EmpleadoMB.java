/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web;

import com.entidades.Empleado;
import com.entidades.Usuario;
import com.excepciones.EmpleadoNotFoundException;
import com.servicio.EmpleadoServiceLocal;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author user
 */
@Named(value = "empleadoMB")
@ApplicationScoped
public class EmpleadoMB implements Serializable{

    private int idEmpleado;
    private String nombreAsignado;
    private String apellidoAsignado;
    private Boolean estadoAsignado;
    private Boolean esAdmin;
    private Usuario usuarioRegistrado;
    private Collection<Empleado> colEmpleado;
    private Empleado empleadoAsignado;
    private String tratEstado;
    private String tratAdmin;
    
    @EJB
    private EmpleadoServiceLocal servicio;
    
    @Inject
    private LoginMB usuarioMB;
    
    
    public EmpleadoMB() {
        this.usuarioRegistrado = new Usuario();
        this.empleadoAsignado = new Empleado();
    }
    
    //ESTADO - ISADMIN
    
    @PostConstruct
    public void iniciar(){
        System.out.println(".... iniciar EmpleadoMB");
        this.colEmpleado = this.servicio.getAllEmpleados();
    }
    
    public void alta() throws EmpleadoNotFoundException {
        Usuario usr = usuarioMB.datosSession();
        this.servicio.altaEmpleado(new Empleado(nombreAsignado, apellidoAsignado, estadoAsignado, esAdmin, usr));
    }
    
    public void addMessage() {
        String summary = this.estadoAsignado ? "Ya esta activo" : "Ya esta inactivo";
        System.out.println("valor:"+estadoAsignado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }
    
    public void addMessageAdmin() {
        String summary = this.esAdmin ? "Ya es administrador" : "No es administrador";
        System.out.println("valor:"+this.esAdmin);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }

    public String getTratEstado() {
        return tratEstado;
    }

    public void setTratEstado(String tratEstado) {
        this.tratEstado = tratEstado;
    }

    public String getTratAdmin() {
        return tratAdmin;
    }

    public void setTratAdmin(String tratAdmin) {
        this.tratAdmin = tratAdmin;
    }
    
    public String getNombreAsignado() {
        return nombreAsignado;
    }

    public void setNombreAsignado(String nombreAsignado) {
        this.nombreAsignado = nombreAsignado;
    }

    public String getApellidoAsignado() {
        return apellidoAsignado;
    }

    public void setApellidoAsignado(String apellidoAsignado) {
        this.apellidoAsignado = apellidoAsignado;
    }

    public Boolean getEstadoAsignado() {
        return estadoAsignado;
    }

    public void setEstadoAsignado(Boolean estadoAsignado) {
        this.estadoAsignado = estadoAsignado;
    }

    public Boolean getEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(Boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    public Usuario getUsuarioRegistrado() {
        return usuarioRegistrado;
    }

    public void setUsuarioRegistrado(Usuario usuarioRegistrado) {
        this.usuarioRegistrado = usuarioRegistrado;
    }

    public Collection<Empleado> getColEmpleado() {
        return colEmpleado;
    }

    public void setColEmpleado(Collection<Empleado> colEmpleado) {
        this.colEmpleado = colEmpleado;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Empleado getEmpleadoAsignado() {
        return empleadoAsignado;
    }

    public void setEmpleadoAsignado(Empleado empleadoAsignado) {
        this.empleadoAsignado = empleadoAsignado;
    }
       
}
