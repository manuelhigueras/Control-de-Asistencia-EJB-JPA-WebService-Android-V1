/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicioWRF;

import com.entidades.Empleado;
import com.excepciones.EmpleadoNotFoundException;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Manuel
 */
@Local
public interface EmpleadoServiceLocalV2 {
    public void altaEmpleado(Empleado empleado) throws EmpleadoNotFoundException;
    public Collection<Empleado> getAllEmpleados();    
    public void baja(Empleado emp, int id) throws EmpleadoNotFoundException;   
    public void modificar(Empleado emp);
}
