/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo.presentacion.ctrl;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;
import torneo.logica.entidades.Jugador;
import torneo.logica.entidades.JugadorPosicion;
import torneo.logica.entidades.Posicion;
import torneo.logica.servicios.ServicioJugadorPosicion;
import torneo.logica.servicios.ServicioPosicion;
import torneo.logica.servicios.ServiciosJugador;

/**
 *
 * @author RUBEN
 */
@ManagedBean
@ViewScoped
public class CtrlJugadorPosicion {

    private ArrayList<JugadorPosicion> lstjugadorposicion;
    private JugadorPosicion objjugardorposicion;
    private JugadorPosicion seljugadorposicion;
    private String criterioBusqueda;

    //Jugador
    private ArrayList<Jugador> lstjugador;
    private Jugador seljugador;
    private int idjugador;
    //Posicion
    private ArrayList<Posicion> lstPosicion;
    private Posicion selposicion;
    private String posicion;
    private int idposicion;

    public CtrlJugadorPosicion() {
        this.init();
    }

    private void init() {
        lstjugadorposicion = new ArrayList<JugadorPosicion>();
        objjugardorposicion = new JugadorPosicion();
        seljugadorposicion = new JugadorPosicion();
        this.criterioBusqueda = new String();

        lstjugador = new ArrayList<Jugador>();
        seljugador = new Jugador();

        lstPosicion = new ArrayList<Posicion>();
        selposicion = new Posicion();

        this.obtenerJugadoresPosicion();
        this.obtenerJugdores();
        this.obtenerPosiciones();
    }

    public ArrayList<JugadorPosicion> getLstjugadorposicion() {
        return lstjugadorposicion;
    }

    public void setLstjugadorposicion(ArrayList<JugadorPosicion> lstjugadorposicion) {
        this.lstjugadorposicion = lstjugadorposicion;
    }

    public JugadorPosicion getObjjugardorposicion() {
        return objjugardorposicion;
    }

    public void setObjjugardorposicion(JugadorPosicion objjugardorposicion) {
        this.objjugardorposicion = objjugardorposicion;
    }

    public JugadorPosicion getSeljugadorposicion() {
        return seljugadorposicion;
    }

    public void setSeljugadorposicion(JugadorPosicion seljugadorposicion) {
        this.seljugadorposicion = seljugadorposicion;
    }

    public String getCriterioBusqueda() {
        return criterioBusqueda;
    }

    public void setCriterioBusqueda(String criterioBusqueda) {
        this.criterioBusqueda = criterioBusqueda;
    }

    public ArrayList<Jugador> getLstjugador() {
        return lstjugador;
    }

    public void setLstjugador(ArrayList<Jugador> lstjugador) {
        this.lstjugador = lstjugador;
    }

    public Jugador getSeljugador() {
        return seljugador;
    }

    public void setSeljugador(Jugador seljugador) {
        this.seljugador = seljugador;
    }

    public ArrayList<Posicion> getLstPosicion() {
        return lstPosicion;
    }

    public void setLstPosicion(ArrayList<Posicion> lstPosicion) {
        this.lstPosicion = lstPosicion;
    }

    public Posicion getSelposicion() {
        return selposicion;
    }

    public void setSelposicion(Posicion selposicion) {
        this.selposicion = selposicion;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getIdposicion() {
        return idposicion;
    }

    public void setIdposicion(int idposicion) {
        this.idposicion = idposicion;
    }

    public int getIdjugador() {
        return idjugador;
    }

    public void setIdjugador(int idjugador) {
        this.idjugador = idjugador;
    }

    public void obtenerJugadoresPosicion() {
        try {
            lstjugadorposicion = ServicioJugadorPosicion.ObtenerJugadorPosicion();
        } catch (Exception e) {
            System.out.println("public void obtenerArbitros() dice: " + e.getMessage());
        }
    }

    public void obtenerJugdores() {
        try {
            lstjugador = ServiciosJugador.obtenerJugadores();
        } catch (Exception e) {
            System.out.println("public void obtenerArbitros() dice: " + e.getMessage());
        }
    }

    public void obtenerPosiciones() {
        try {
            lstPosicion = ServicioPosicion.obtenerPosiciones();
        } catch (Exception e) {
            System.out.println("public void obtenerArbitros() dice: " + e.getMessage());
        }
    }

    public void insertarJugadorPosicion() {
        try {

            this.objjugardorposicion.setIdJugador(ServiciosJugador.obtenerJugadorPorId(idjugador));
            this.objjugardorposicion.setIdPosicion(ServicioPosicion.obtenerPosicionPorId(idposicion));
            if (ServicioJugadorPosicion.insertar(objjugardorposicion)) {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                DefaultRequestContext.getCurrentInstance().execute("wdlgNuevoJugadorPosicion.hide()");
                this.obtenerJugadoresPosicion();
                this.objjugardorposicion = new JugadorPosicion();
            } else {
                FacesMessage mensajeError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "Datos no Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensajeError);
            }
        } catch (Exception e) {
            FacesMessage mensajeErrorIngreso = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, mensajeErrorIngreso);
        }

    }

    public void actualizarJugadorPosicion() {
        try {

            seljugadorposicion.setIdJugador(ServiciosJugador.obtenerJugadorPorId(seljugadorposicion.getIdJugador().getIdJugador()));
            seljugadorposicion.setIdPosicion(ServicioPosicion.obtenerPosicionPorId(seljugadorposicion.getIdPosicion().getIdPosicion()));

            if (ServicioJugadorPosicion.Actualizar(seljugadorposicion)) {
                FacesMessage mensajeActualizacion = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos Actualizados");
                FacesContext.getCurrentInstance().addMessage(null, mensajeActualizacion);
                DefaultRequestContext.getCurrentInstance().execute("wdlgPersonaTipoUsuarioEditar.hide()");
                this.obtenerJugadoresPosicion();
            } else {
                FacesMessage mensajeErrorActualizacion = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "Datos no Actualizados");
                FacesContext.getCurrentInstance().addMessage(null, mensajeErrorActualizacion);
            }
        } catch (Exception e) {
            FacesMessage mensajeErrorA = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, mensajeErrorA);
        }
    }

    public void eliminarJugadorPosicion() {
        try {
            objjugardorposicion= seljugadorposicion;
            ServicioJugadorPosicion.Eliminar(objjugardorposicion.getIdJugadorPosicion());
            obtenerJugadoresPosicion();
            Util.addSuccessMessage("Datos Eliminados");
        } catch (Exception e) {
            System.out.println("Error de inserción" + e.getMessage());
            Util.mostrarMensaje(e.getMessage());
        }
        objjugardorposicion = new JugadorPosicion();
        DefaultRequestContext.getCurrentInstance().execute("wdlgEliminarJugadorPosicion.hide()");
    }

}
