/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo.logica.entidades;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Torneo {

    private int idTorneo;
    private String descripcion;
    private int anio;
    private Date fechaInicio;
    private Date fechaFin;
    private String auspiciante;

    public Torneo() {
    }

    public Torneo(int idTorneo, String descripcion, int anio, Date fechaInicio, Date fechaFin, String auspiciante) {
        this.idTorneo = idTorneo;
        this.descripcion = descripcion;
        this.anio = anio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.auspiciante = auspiciante;
    }

    public int getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(int idTorneo) {
        this.idTorneo = idTorneo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getAuspiciante() {
        return auspiciante;
    }

    public void setAuspiciante(String auspiciante) {
        this.auspiciante = auspiciante;
    }

}
