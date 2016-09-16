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
public class Equipo {

    private int idEquipo;
    private String nombre;
    private String procedencia;
    private Date fechaCreacion;
    private Grupo idGrupo;

    public Equipo(int idEquipo, String nombre, String procedencia, Date fechaCreacion, Grupo idGrupo) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.procedencia = procedencia;
        this.fechaCreacion = fechaCreacion;
        this.idGrupo = idGrupo;
    }

    public Equipo() {
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Grupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Grupo idGrupo) {
        this.idGrupo = idGrupo;
    }

}
