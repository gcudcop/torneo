/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo.logica.entidades;

/**
 *
 * @author RUBEN
 */
public class Posicion {
    private int idPosicion;
    private String descripcion;

    public Posicion() {
    }

    public Posicion(int idPosicion, String descripcion) {
        this.idPosicion = idPosicion;
        this.descripcion = descripcion;
    }

    public int getIdPosicion() {
        return idPosicion;
    }

    public void setIdPosicion(int idPosicion) {
        this.idPosicion = idPosicion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
   
}
