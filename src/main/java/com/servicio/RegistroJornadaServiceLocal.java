/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicio;

import com.entidades.Empleado;
import com.excepciones.RegistroJornadaException;
import com.entidades.RegistroHoras;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface RegistroJornadaServiceLocal {
    
    public Collection<RegistroHoras> getAllRegistroHoras();
    
    public void iniciarJornada(Integer idEmpleado) throws RegistroJornadaException;
    public void finalizarJornada(Integer idEmpleado) throws RegistroJornadaException;
    public Collection<RegistroHoras> getAllregistroHorasPorEmpleado(Integer idEmpleado);
    public Collection<RegistroHoras> getAllregistroHorasPorEmpleadoV2(Empleado emp);
}
