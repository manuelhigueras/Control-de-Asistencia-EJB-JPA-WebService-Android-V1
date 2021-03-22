/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "REGISTROHORAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registrohoras.findAll", query = "SELECT r FROM RegistroHoras r"),
    @NamedQuery(name = "Registrohoras.findByIdRegistrohoras", query = "SELECT r FROM RegistroHoras r WHERE r.idRegistrohoras = :idRegistrohoras"),
    @NamedQuery(name = "Registrohoras.findByFecha", query = "SELECT r FROM RegistroHoras r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "Registrohoras.findByHoraInicio", query = "SELECT r FROM RegistroHoras r WHERE r.horaInicio = :horaInicio"),
    @NamedQuery(name = "Registrohoras.findByHoraFinal", query = "SELECT r FROM RegistroHoras r WHERE r.horaFinal = :horaFinal"),
    @NamedQuery(name = "Registrohoras.findJornadasNoFinalizadasPorEmpleado", 
            query = "SELECT r FROM RegistroHoras r WHERE r.idEmpleado = :idEmpleado " 
                    + "AND r.fecha = :fechaJornada AND r.horaFinal IS NULL"),
    @NamedQuery(name = "Registrohoras.findByIdEmpleado", query = "SELECT r FROM RegistroHoras r WHERE r.idEmpleado = :idEmpleado")
})

public class RegistroHoras implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_REGISTROHORAS")
    private Integer idRegistrohoras;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "HORA_INICIO")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Column(name = "HORA_FINAL")
    @Temporal(TemporalType.TIME)
    private Date horaFinal;
    
    @Column(name="ID_EMPLEADO")
    private Integer idEmpleado; 
    
    
    @JoinColumn(name = "ID_EMPLEADO", referencedColumnName = "ID_EMPLEADO", insertable = false, updatable =false)
    @ManyToOne(optional = false)
    private Empleado empleado;

    public RegistroHoras() {
    }

    public RegistroHoras(Integer idRegistrohoras) {
        this.idRegistrohoras = idRegistrohoras;
    }

    public Integer getIdRegistrohoras() {
        return idRegistrohoras;
    }

    public void setIdRegistrohoras(Integer idRegistrohoras) {
        this.idRegistrohoras = idRegistrohoras;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Date horaFinal) {
        this.horaFinal = horaFinal;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegistrohoras != null ? idRegistrohoras.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroHoras)) {
            return false;
        }
        RegistroHoras other = (RegistroHoras) object;
        if ((this.idRegistrohoras == null && other.idRegistrohoras != null) || (this.idRegistrohoras != null && !this.idRegistrohoras.equals(other.idRegistrohoras))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RegistroHoras{" + "idRegistrohoras=" + idRegistrohoras + ", fecha=" + fecha + ", horaInicio=" + horaInicio + ", horaFinal=" + horaFinal + ", idEmpleado=" + idEmpleado + ", empleado=" + empleado + '}';
    }

    
    
    
}
