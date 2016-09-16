/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo.presentacion.ctrl;

import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.DefaultRequestContext;
import recursos.Fechas;
import recursos.Util;
import torneo.logica.entidades.Equipo;
import torneo.logica.entidades.Jugador;
import torneo.logica.servicios.ServiciosEquipo;
import torneo.logica.servicios.ServiciosJugador;

/**
 *
 * @author RUBEN
 */
@ManagedBean
@ViewScoped
public class CtrlJugador {

    private ArrayList<Jugador> lstjudor;
    private Jugador objjugador;
    private Jugador selJugador;
    private Date fecha;

    private ArrayList<Equipo> lstequipo;
    private Equipo selequipo;
    private int idequipo;

    public CtrlJugador() {
        this.init();
    }

    private void init() {
        this.lstjudor = new ArrayList<Jugador>();
        this.objjugador = new Jugador();
        this.selJugador = new Jugador();
        this.lstequipo = new ArrayList<Equipo>();
        obtenerJugadores();
        obtenerEquipos();
    }

    public ArrayList<Jugador> getLstjudor() {
        return lstjudor;
    }

    public void setLstjudor(ArrayList<Jugador> lstjudor) {
        this.lstjudor = lstjudor;
    }

    public Jugador getObjjugador() {
        return objjugador;
    }

    public void setObjjugador(Jugador objjugador) {
        this.objjugador = objjugador;
    }

    public Jugador getSelJugador() {
        return selJugador;
    }

    public void setSelJugador(Jugador selJugador) {
        this.selJugador = selJugador;
    }

    public ArrayList<Equipo> getLstequipo() {
        return lstequipo;
    }

    public void setLstequipo(ArrayList<Equipo> lstequipo) {
        this.lstequipo = lstequipo;
    }

    public Equipo getSelequipo() {
        return selequipo;
    }

    public void setSelequipo(Equipo selequipo) {
        this.selequipo = selequipo;
    }

    public int getIdequipo() {
        return idequipo;
    }

    public void setIdequipo(int idequipo) {
        this.idequipo = idequipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void obtenerJugadores() {
        try {
            lstjudor = ServiciosJugador.obtenerJugadores();
        } catch (Exception e) {
            System.out.println("public void obtenerPosiciones() dice: " + e.getMessage());
        }
    }

    public void obtenerEquipos() {
        try {
            lstequipo = ServiciosEquipo.obtenerEquipos();
        } catch (Exception e) {
            System.out.println("public void obtenerPosiciones() dice: " + e.getMessage());
        }
    }

    //Insertar
    public void insertarJugador() {
        try {
            objjugador.setFechaNacimiento(Fechas.devolverFecha(fecha));
            this.objjugador.setIdEquipo(ServiciosEquipo.obtenerEquipoPorId(idequipo));
            if (ServiciosJugador.insertar(objjugador) == true) {
                this.init();
                this.objjugador = new Jugador();
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                DefaultRequestContext.getCurrentInstance().execute("wdlgNuevoJugador.hide()");
            } else {
                FacesMessage mensajeError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "Datos no Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensajeError);
            }
        } catch (Exception e) {
            System.out.println("public void insertarJugador() dice: " + e.getMessage());
        }
    }

    public void editarJugador() {
        try {
            this.selJugador.setFechaNacimiento(Fechas.devolverFecha(fecha));
            this.selJugador.setIdEquipo(ServiciosEquipo.obtenerEquipoPorId(idequipo));
            if (ServiciosJugador.actualizar(selJugador) == true) {
                this.init();
                this.selJugador = new Jugador();
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos Editados");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                DefaultRequestContext.getCurrentInstance().execute("wdlgEditarJugador.hide()");
            } else {
                FacesMessage mensajeError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "Datos no Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensajeError);
            }
        } catch (Exception e) {
            System.out.println("public void editarJugador() dice: " + e.getMessage());
        }
    }

    public void eliminarJugador() {
        try {
            if (ServiciosJugador.eliminarJugador((int) selJugador.getIdJugador())) {
                this.init();
                DefaultRequestContext.getCurrentInstance().execute("wdlgEliminarJugador.hide()");
                Util.addSuccessMessage("Información eliminada.");
                System.out.println("public void eliminarArbitro() dice: Información eliminada.");
            } else {
                Util.addErrorMessage("Error al eliminar la información.");
                System.out.println("public void eliminarJugador() dice: Error al eliminar la información");
            }
        } catch (Exception e) {
            Util.addErrorMessage("public void eliminarJugador() dice: " + e.getMessage());
            System.out.println("public void eliminarJugador() dice: " + e.getMessage());
        }
    }

}
