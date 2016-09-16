/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo.logica.entidades;

public class JugadorPosicion {
  private int idJugadorPosicion;
  private Jugador idJugador;
  private Posicion idPosicion;
  private int dorsal; 

    public JugadorPosicion() {
    }

    public JugadorPosicion(int idJugadorPosicion, Jugador idJugador, Posicion idPosicion, int dorsal) {
        this.idJugadorPosicion = idJugadorPosicion;
        this.idJugador = idJugador;
        this.idPosicion = idPosicion;
        this.dorsal = dorsal;
    }

    public int getIdJugadorPosicion() {
        return idJugadorPosicion;
    }

    public void setIdJugadorPosicion(int idJugadorPosicion) {
        this.idJugadorPosicion = idJugadorPosicion;
    }

    public Jugador getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Jugador idJugador) {
        this.idJugador = idJugador;
    }

    public Posicion getIdPosicion() {
        return idPosicion;
    }

    public void setIdPosicion(Posicion idPosicion) {
        this.idPosicion = idPosicion;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

}
