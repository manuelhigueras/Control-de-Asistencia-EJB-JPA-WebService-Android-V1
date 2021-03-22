/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web;

import com.entidades.Empleado;
import com.entidades.Usuario;
import com.excepciones.EmpleadoNotFoundException;
import com.excepciones.UsuarioNotFoundException;
import com.servicio.EmpleadoServiceLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Collection;
import javafx.scene.control.TableColumn;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Manuel
 */
@Named(value = "empleadoMBA")
@SessionScoped
public class EmpleadoMBA implements Serializable {
    
    private Collection<Empleado> colEmpleado;
    private Empleado empleado;
    private int idEmpleado;
    private String estado;
    private String administracion;
    
    @Inject
    private EmpleadoServiceLocal service;    
    
    public EmpleadoMBA() {
    }
    
    @PostConstruct
    public void iniciar(){
        this.colEmpleado = this.service.getAllEmpleados();  
    }

    public void number(int valor){
        System.out.println("el valor" + valor);
    }
    
    public String getEmpleadoActivo(Usuario user) throws EmpleadoNotFoundException{
        Usuario userAct = user;
        empleado = this.service.getEmpleadoIdSpecial(userAct);
        setEmpleado(empleado);
        return empleado.getNombre();
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }   
    
    public Collection<Empleado> getColEmpleado() {
        return colEmpleado;
    }

    public void setColEmpleado(Collection<Empleado> colEmpleado) {
        this.colEmpleado = colEmpleado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    public String estadoToString(Boolean condicion){
        if(condicion)
            estado = "Activa";
        else
            estado = "Inactiva";
        return estado;
    }
    
    public String administracionToString(Boolean condicion){
        if(condicion)
            administracion = "Si";
        else
            administracion = "No";
        return administracion;
    }

    public String baja(Empleado empleado) throws EmpleadoNotFoundException, UsuarioNotFoundException{
       try {
            System.out.println("Valor id:"+empleado.getIdEmpleado());
            int id = empleado.getIdEmpleado();
            this.service.baja(empleado, id);            
        } catch (EmpleadoNotFoundException ex) {
            throw new EmpleadoNotFoundException("No se pudo dar de baja");
        }
       return "vistaEmpleadoV2?faces-redirect=true";
    }    

    public void onRowEdit(RowEditEvent<Empleado> event) {
        FacesMessage msg = new FacesMessage("Empleado Edited", String.valueOf(event.getObject().getIdEmpleado()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Empleado> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getIdEmpleado()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(TableColumn.CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }    
    
}
