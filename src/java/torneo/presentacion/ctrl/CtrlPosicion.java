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
import torneo.logica.entidades.Posicion;
import torneo.logica.servicios.ServicioPosicion;

/**
 *
 * @author RUBEN
 */
@ManagedBean
@ViewScoped
public class CtrlPosicion {

    private ArrayList<Posicion> lstPosicion;
    private Posicion objposicion;
    private Posicion selposicion;
    // private String criterioBusqueda;

    private void init() {
        this.objposicion = new Posicion();
        this.lstPosicion = new ArrayList<Posicion>();
        this.obtenerPosiciones();
    }

    public CtrlPosicion() {
        this.init();
    }

    public ArrayList<Posicion> getLstPosicion() {
        return lstPosicion;
    }

    public void setLstPosicion(ArrayList<Posicion> lstPosicion) {
        this.lstPosicion = lstPosicion;
    }

    public Posicion getObjposicion() {
        return objposicion;
    }

    public void setObjposicion(Posicion objposicion) {
        this.objposicion = objposicion;
    }

    public Posicion getSelposicion() {
        return selposicion;
    }

    public void setSelposicion(Posicion selposicion) {
        this.selposicion = selposicion;
    }

    //Seleccionar todo posiciones
    public void obtenerPosiciones() {
        try {
            lstPosicion = ServicioPosicion.obtenerPosiciones();
        } catch (Exception e) {
            System.out.println("public void obtenerPosiciones() dice: " + e.getMessage());
        }
    }

    //Insertar
    public void insertarPosicion() {
        try {
            if (ServicioPosicion.insertarPosicion(objposicion) == true) {
                this.init();
                this.objposicion = new Posicion();
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                DefaultRequestContext.getCurrentInstance().execute("wdlgNuevoPosicion.hide()");
            } else {
                FacesMessage mensajeError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "Datos no Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensajeError);
            }
        } catch (Exception e) {
            System.out.println("public void insertarPosicion() dice: " + e.getMessage());
        }
    }

    public void editarPosicion() {
        try {
            if (ServicioPosicion.actualizarPosicion(selposicion) == true) {
                this.init();
                this.selposicion = new Posicion();
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                DefaultRequestContext.getCurrentInstance().execute("wdlgEditarPosicion.hide()");
            } else {
                FacesMessage mensajeError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "Datos no Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensajeError);
            }
        } catch (Exception e) {
            System.out.println("public void insertarPocion() dice: " + e.getMessage());
        }
    }
    
    
        public void eliminarPosicion() {
        try {
            if (ServicioPosicion.eliminarPosicion((int) selposicion.getIdPosicion())) {
                this.init();
                DefaultRequestContext.getCurrentInstance().execute("wdlgEliminarPosicion.hide()");
                Util.addSuccessMessage("Información eliminada.");
                System.out.println("public void eliminarArbitro() dice: Información eliminada.");
            } else {
                Util.addErrorMessage("Error al eliminar la información.");
                System.out.println("public void eliminarArbitro() dice: Error al eliminar la información");
            }
        } catch (Exception e) {
            Util.addErrorMessage("public void eliminarArbitro() dice: " + e.getMessage());
            System.out.println("public void eliminarArbitro() dice: " + e.getMessage());
        }

    }

}
