/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web;

import com.entidades.Empleado;
import com.entidades.RegistroHoras;
import com.excepciones.RegistroJornadaException;
import com.servicio.EmpleadoServiceLocal;
import com.servicio.RegistroJornadaServiceLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author Manuel
 */
@Named(value = "registroJornadaMB")
@SessionScoped
public class RegistroJornadaMB implements Serializable {

    private Collection<RegistroHoras> lista;
    private Collection<RegistroHoras> lista2;
    private Boolean condicionIn = false;
    private Boolean condicionFin = true;
    private String name;
    
    @Inject
    private RegistroJornadaServiceLocal service;
    
    @Inject
    private EmpleadoServiceLocal serviceEmp;
    
    public RegistroJornadaMB() {
       
    }
    
    @PostConstruct
    public void inicio(){
        this.lista = service.getAllRegistroHoras();//service.getAllregistroHorasPorEmpleado();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public Collection<RegistroHoras> getLista() {
        return lista;
    }

    public Collection<RegistroHoras> listaEspecifica(String name){
        this.name = getNombreRegistro(name);
        Empleado emp = this.serviceEmp.getEmpleadoNombre(name);
        this.lista2 = this.service.getAllregistroHorasPorEmpleadoV2(emp);
        return lista2;
    }
    
    public String getNombreRegistro(String nombre){
        setName(nombre);
        return name;
    }
    
    public void setLista(Collection<RegistroHoras> lista) {
        this.lista = lista;
    }
    
    public String procesoJornadaInicial(String nombre) throws RegistroJornadaException{
        Empleado emp = this.serviceEmp.getEmpleadoNombre(nombre);
        setName(emp.getNombre());
        jornadaInicial(emp.getIdEmpleado());
        return "vistaRegistroJornada?faces-redirect=true";
    }
    
    public String procesoJornadaFinal(String nombre) throws RegistroJornadaException{
        Empleado emp = this.serviceEmp.getEmpleadoNombre(nombre);
        setName(emp.getNombre());
        jornadaFinal(emp.getIdEmpleado());
        return "vistaRegistroJornada?faces-redirect=true";        
    }
    
    public String getFecha(Date fecha){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaComoCadena = sdf.format(fecha);
        return fechaComoCadena;
    }
    
    public String getHora(Date hora){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String horaString;
        if(hora != null){
            horaString = sdf.format(hora);
            return horaString;
        }
        else
            return null;
    }
    
    public void jornadaInicial(Integer idEmpleado) throws RegistroJornadaException{
        try {
            this.service.iniciarJornada(idEmpleado);
            this.condicionIn = true;
            this.condicionFin = false;
        } catch (RegistroJornadaException ex) {
            throw new RegistroJornadaException("Error - no se pudo fichar");
        }
    }
    
    public void jornadaFinal(Integer idEmpleado) throws RegistroJornadaException{
        try {
            this.service.finalizarJornada(idEmpleado);
            this.condicionIn = false;
            this.condicionFin = true;
        } catch (RegistroJornadaException ex) {
            throw new RegistroJornadaException("Error - no se pudo fichar " + ex.getMessage());
        }
    }    
    
    public Boolean getCondicionIn() {
        return condicionIn;
    }

    public void setCondicionIn(Boolean condicion) {
        this.condicionIn = condicion;
    }

    public Boolean getCondicionFin() {
        return condicionFin;
    }

    public void setCondicionFin(Boolean condicionFin) {
        this.condicionFin = condicionFin;
    }
    
}
