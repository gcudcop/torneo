package torneo.presentacion.ctrl;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;
import torneo.logica.entidades.Cancha;
import torneo.logica.servicios.ServiciosCancha;

@ManagedBean
@ViewScoped
public class CtrlCancha {

    private ArrayList<Cancha> lstCanchas;
    private Cancha objCancha;
    private Cancha selCancha;
    private String criterioBusqueda;

    private void init() {
        this.objCancha = new Cancha();
        this.lstCanchas = new ArrayList<Cancha>();
        this.criterioBusqueda = new String();
        this.obtenerCanchas();
    }

    public CtrlCancha() {
        this.init();
    }

    public void obtenerCanchas() {
        try {
            lstCanchas = ServiciosCancha.obtenerCanchas();
        } catch (Exception e) {
            System.out.println("public void obtenerCanchas() dice: " + e.getMessage());
        }
    }

    public void obtenerCanchasPorNombre() {
        try {
            lstCanchas = ServiciosCancha.obtenerCanchasPorNombre(criterioBusqueda);
        } catch (Exception e) {
            System.out.println("public void obtenerCanchas() dice: " + e.getMessage());
        }
    }

    public void insertar() {
        try {
            if (ServiciosCancha.insertar(objCancha) == true) {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                DefaultRequestContext.getCurrentInstance().execute("wdlgNuevoCancha.hide()");
                this.objCancha = new Cancha();
            } else {
                FacesMessage mensajeError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "Datos no Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensajeError);
            }
        } catch (Exception e) {
        }
    }

    public void editarCancha() {
        try {
            if (ServiciosCancha.actualizar(selCancha) == true) {
                this.init();
                this.selCancha = new Cancha();
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                DefaultRequestContext.getCurrentInstance().execute("wdlgEditarCancha.hide()");
            } else {
                FacesMessage mensajeError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "Datos no Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensajeError);
            }
        } catch (Exception e) {
            System.out.println("public void insertarCancha() dice: " + e.getMessage());
        }
    }

    public void eliminarArbitro() {
        try {
            if (ServiciosCancha.eliminarCancha((int) selCancha.getIdCancha())) {
                this.init();
                DefaultRequestContext.getCurrentInstance().execute("wdlgEliminarCancha.hide()");
                Util.addSuccessMessage("Información eliminada.");
                System.out.println("public void eliminarCancha() dice: Información eliminada.");
            } else {
                Util.addErrorMessage("Error al eliminar la información.");
                System.out.println("public void eliminarCancha() dice: Error al eliminar la información");
            }
        } catch (Exception e) {
            Util.addErrorMessage("public void eliminarCancha() dice: " + e.getMessage());
            System.out.println("public void eliminarCancha() dice: " + e.getMessage());
        }
    }

    /*
     getters y setters
     */
    public ArrayList<Cancha> getLstCanchas() {
        return lstCanchas;
    }

    public void setLstCanchas(ArrayList<Cancha> lstCanchas) {
        this.lstCanchas = lstCanchas;
    }

    public Cancha getObjCancha() {
        return objCancha;
    }

    public void setObjCancha(Cancha objCancha) {
        this.objCancha = objCancha;
    }

    public Cancha getSelCancha() {
        return selCancha;
    }

    public void setSelCancha(Cancha selCancha) {
        this.selCancha = selCancha;
    }

    public String getCriterioBusqueda() {
        return criterioBusqueda;
    }

    public void setCriterioBusqueda(String criterioBusqueda) {
        this.criterioBusqueda = criterioBusqueda;
    }

}
