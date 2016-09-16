/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo.logica.entidades;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Partido {

    private int idPartido;
    private Equipo idEquipo1;
    private Equipo idEquipo2;
    private Cancha idCancha;
    private Arbitro idArbitro;
    private Date fecha;
    private Time hora;

    public Partido(int idPartido, Equipo idEquipo1, Equipo idEquipo2, Cancha idCancha, Arbitro idArbitro, Date fecha, Time hora) {
        this.idPartido = idPartido;
        this.idEquipo1 = idEquipo1;
        this.idEquipo2 = idEquipo2;
        this.idCancha = idCancha;
        this.idArbitro = idArbitro;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Partido() {
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public Equipo getIdEquipo1() {
        return idEquipo1;
    }

    public void setIdEquipo1(Equipo idEquipo1) {
        this.idEquipo1 = idEquipo1;
    }

    public Equipo getIdEquipo2() {
        return idEquipo2;
    }

    public void setIdEquipo2(Equipo idEquipo2) {
        this.idEquipo2 = idEquipo2;
    }

    public Cancha getIdCancha() {
        return idCancha;
    }

    public void setIdCancha(Cancha idCancha) {
        this.idCancha = idCancha;
    }

    public Arbitro getIdArbitro() {
        return idArbitro;
    }

    public void setIdArbitro(Arbitro idArbitro) {
        this.idArbitro = idArbitro;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
