/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webRestFull;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@XmlRootElement
public class Respuesta implements Serializable{
    private Boolean sinError;
    private String mensaje;

    public Respuesta() {
    }

    public Respuesta(Boolean sinError, String mensaje) {
        this.sinError = sinError;
        this.mensaje = mensaje;
    }

    public Boolean getSinError() {
        return sinError;
    }

    public void setSinError(Boolean sinError) {
        this.sinError = sinError;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    
}
