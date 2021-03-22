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
import com.servicio.UsuarioServiceLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author user
 */
@Named(value = "bajaEmpleadoMB")
//@RequestScoped
@ViewScoped
public class BajaEmpleadoMB implements Serializable {

    private Usuario user;
    private Empleado empleado;
    
    @EJB
    private EmpleadoServiceLocal servicio;
    
    public BajaEmpleadoMB() {
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String baja(Empleado empleado) throws EmpleadoNotFoundException, UsuarioNotFoundException{
       try {
            System.out.println("Valor id:"+empleado.getIdEmpleado());
            int id = empleado.getIdEmpleado();
            this.servicio.baja(empleado, id);
            return "vistaEmpleado?faces-redirect=true";
        } catch (EmpleadoNotFoundException ex) {
            throw new EmpleadoNotFoundException("No se pudo dar de baja");
        }
    }    
    
}
