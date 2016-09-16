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
import torneo.logica.entidades.Torneo;
import torneo.logica.servicios.ServiciosTorneo;

/**
 *
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class CtrlTorneo {

    private ArrayList<Torneo> lstTorneos;
    private Torneo torneo;
    private Torneo torneoSel;
    private Date fechaInicio;
    private Date fechaFin;
    
    public CtrlTorneo(){
        this.init();
        
    }

    private void init() {
        this.torneo = new Torneo();
        this.torneoSel = new Torneo();
        this.lstTorneos = new ArrayList<Torneo>();
        this.fechaInicio = new Date();
        this.fechaFin = new Date();
        this.obtenerTorneos();
    }
    
    public void obtenerTorneos(){
        try {
            lstTorneos=ServiciosTorneo.obtenerTorneos();
            System.out.println(lstTorneos.get(0).getDescripcion());
        } catch (Exception e) {
            System.out.println("error obtener torneos dice: "+e.getMessage());
        }
    }

    public void insertar() {
        try {
            torneo.setFechaInicio(Fechas.devolverFecha(fechaInicio));
            torneo.setFechaFin(Fechas.devolverFecha(fechaFin));
            if (ServiciosTorneo.insertar(torneo) == true) {
                this.init();
                this.torneo = new Torneo();
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                DefaultRequestContext.getCurrentInstance().execute("wdlgNuevoTorneo.hide()");
            } else {
                FacesMessage mensajeError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "Datos no Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensajeError);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public void editar() {
        try {
            torneoSel.setFechaInicio(Fechas.devolverFecha(fechaInicio));
            torneoSel.setFechaFin(Fechas.devolverFecha(fechaFin));
            if (ServiciosTorneo.actualizar(torneoSel) == true) {
                this.init();
                this.torneoSel = new Torneo();
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                DefaultRequestContext.getCurrentInstance().execute("wdlgEditarTorneo.hide()");
            } else {
                FacesMessage mensajeError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "Datos no Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensajeError);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void eliminarTorneo() {
        try {
            if (ServiciosTorneo.eliminarTorneo((int) torneoSel.getIdTorneo())) {
                this.init();
                DefaultRequestContext.getCurrentInstance().execute("wdlgEliminarTorneo.hide()");
                Util.addSuccessMessage("Información eliminada.");
                System.out.println("public void eliminarTorneo() dice: Información eliminada.");
            } else {
                Util.addErrorMessage("Error al eliminar la información.");
                System.out.println("public void eliminarTorneo() dice: Error al eliminar la información");
            }
        } catch (Exception e) {
            Util.addErrorMessage("public void eliminarTorneo() dice: " + e.getMessage());
            System.out.println("public void eliminarTorneo() dice: " + e.getMessage());
        }

    }
    
    /*
     ***getters y setters
     */
    public ArrayList<Torneo> getLstTorneos() {
        return lstTorneos;
    }

    public void setLstTorneos(ArrayList<Torneo> lstTorneos) {
        this.lstTorneos = lstTorneos;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public Torneo getTorneoSel() {
        return torneoSel;
    }

    public void setTorneoSel(Torneo torneoSel) {
        this.torneoSel = torneoSel;
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

}
