/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicioWRF;

import com.entidades.RegistroHoras;
import com.excepciones.RegistroJornadaException;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Manuel
 */
@Local
public interface RegistroJornadaServicioLocalV2 {
    public Collection<RegistroHoras> getAllregistroHorasPorEmpleado(Integer idEmpleado);
    public void iniciarJornada(Integer idEmpleado) throws RegistroJornadaException;
    public void finalizarJornada(Integer idEmpleado) throws RegistroJornadaException;
}
