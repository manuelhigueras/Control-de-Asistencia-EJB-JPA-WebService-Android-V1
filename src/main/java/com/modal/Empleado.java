/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modal;

import java.util.Date;

/**
 *
 * @author user
 */
public class Empleado {
    //1ra Perspectiva - Modo Administrador - Permite crear cualquier usuario
    //2da Perspectiva - Modo Empleado - Solo permite ver la vista
    //Detalles(Nombre, Apellido, CtaActiva, dia de alta, HorarioEntrada, HorarioSalida)
    private String nombre;
    private String apellido;
    //Estado de cuenta - Baja False - Alta True
    private String estadoEmpleado;
    //private Date horarioEntrada, horarioSalida;
    private String esAdmin;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEstadoEmpleado() {
        return estadoEmpleado;
    }

    public void setEstadoEmpleado(Boolean estadoEstado) {
        this.estadoEmpleado = estadoEmpleado;
    }

//    public Date getHorarioEntrada() {
//        return horarioEntrada;
//    }
//
//    public void setHorarioEntrada(Date horarioEntrada) {
//        this.horarioEntrada = horarioEntrada;
//    }
//
//    public Date getHorarioSalida() {
//        return horarioSalida;
//    }
//
//    public void setHorarioSalida(Date horarioSalida) {
//        this.horarioSalida = horarioSalida;
//    }

    public String esAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(String admin) {
        this.esAdmin = admin;
    }
    
}
