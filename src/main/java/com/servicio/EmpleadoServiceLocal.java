/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicio;

import com.entidades.Empleado;
import com.entidades.Usuario;
import com.excepciones.EmpleadoNotFoundException;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface EmpleadoServiceLocal {
    public void altaEmpleado(Empleado empleado) throws EmpleadoNotFoundException;
    public Collection<Empleado> getAllEmpleados();
    public Empleado getEmpleadoIdSpecial(Usuario user);
    public Empleado getEmpleadoId(Integer idEmpleado) throws EmpleadoNotFoundException;
    public void baja(Empleado emp, int id) throws EmpleadoNotFoundException;   
    public Empleado getEmpleadoNombre(String nombre);
}
