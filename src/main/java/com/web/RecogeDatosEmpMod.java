/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web;

import com.entidades.Empleado;
import com.entidades.Usuario;
import com.servicio.UsuarioServiceLocal;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Manuel
 */
@Named(value = "empMod")
@ViewScoped
public class RecogeDatosEmpMod implements Serializable {

    private Collection<Empleado> colEmpleado;
    private Collection<Usuario> colUsuario;
    private Empleado empleado;
    private int idEmpleado;
    
    @EJB
    private UsuarioServiceLocal usl;

    @Inject
    private EmpleadoMB emp;
    
    public RecogeDatosEmpMod() {
        this.empleado = new Empleado();
    }

    @PostConstruct
    public void iniciar(){
        this.colEmpleado = emp.getColEmpleado();
        this.colUsuario = usl.getAllUsuarios();
    }
    
    public Collection<Empleado> getColEmpleado() {
        return colEmpleado;
    }

    public void setColEmpleado(Collection<Empleado> colEmpleado) {
        this.colEmpleado = colEmpleado;
    }

    public Collection<Usuario> getColUsuario() {
        return colUsuario;
    }

    public void setColUsuario(Collection<Usuario> colUsuario) {
        this.colUsuario = colUsuario;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    public void saveEmpleado() {
        if (empleado.getNombre() == null) {
            this.empleado.setNombre(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
            this.colEmpleado.add(this.empleado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empleado Added"));
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empleado Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }    

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
        
}
