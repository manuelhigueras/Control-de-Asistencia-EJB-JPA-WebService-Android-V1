/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excepciones;

import javax.ejb.ApplicationException;

/**
 *
 * @author user
 */
@ApplicationException(rollback = true)
public class RegistroJornadaException extends Exception{

    public RegistroJornadaException(String string) {
        super(string);
    }
    
}
