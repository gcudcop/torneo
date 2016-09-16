package torneo.presentacion.ctrl;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;
import torneo.logica.entidades.Arbitro;
import torneo.logica.servicios.ServiciosArbitro;

@ManagedBean
@ViewScoped
public class CtrlArbitro {

    private ArrayList<Arbitro> lstArbitros;
    private Arbitro objArbitro;
    private Arbitro selArbitro;
    private String criterioBusqueda;

    private void init() {
        this.objArbitro = new Arbitro();
        this.lstArbitros = new ArrayList<Arbitro>();
        this.criterioBusqueda=new String();
        this.obtenerArbitros();
    }

    public CtrlArbitro() {
        this.init();

    }

    public void obtenerArbitros() {
        try {
            lstArbitros = ServiciosArbitro.obtenerArbitros();
        } catch (Exception e) {
            System.out.println("public void obtenerArbitros() dice: " + e.getMessage());
        }
    }
    
     public void obtenerArbitrosPorNombre() {
        try {
            lstArbitros = ServiciosArbitro.obtenerArbitrosPorNombre(criterioBusqueda);
        } catch (Exception e) {
            System.out.println("public void obtenerArbitros() dice: " + e.getMessage());
        }
    }
    

    public void insertar() {
        try {
            if (ServiciosArbitro.insertar(objArbitro) == true) {
                this.init();
                this.objArbitro = new Arbitro();
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                DefaultRequestContext.getCurrentInstance().execute("wdlgNuevoArbitro.hide()");
            } else {
                FacesMessage mensajeError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "Datos no Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensajeError);
            }
        } catch (Exception e) {
            System.out.println("public void insertarArbitro() dice: " + e.getMessage());
        }
    }

    public void editarArbitro() {
        try {
            if (ServiciosArbitro.actualizar(selArbitro) == true) {
                this.init();
                this.selArbitro = new Arbitro();
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                DefaultRequestContext.getCurrentInstance().execute("wdlgEditarArbitro.hide()");
            } else {
                FacesMessage mensajeError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "Datos no Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensajeError);
            }
        } catch (Exception e) {
            System.out.println("public void insertarArbitro() dice: " + e.getMessage());
        }
    }

    public void eliminarArbitro() {
        try {
            if (ServiciosArbitro.eliminarArbitro((int) selArbitro.getIdArbitro())) {
                this.init();
                DefaultRequestContext.getCurrentInstance().execute("wdlgEliminarArbitro.hide()");
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

    /*
     getters y setters
     */
    public ArrayList<Arbitro> getLstArbitros() {
        return lstArbitros;
    }

    public void setLstArbitros(ArrayList<Arbitro> lstArbitros) {
        this.lstArbitros = lstArbitros;
    }

    public Arbitro getObjArbitro() {
        return objArbitro;
    }

    public void setObjArbitro(Arbitro objArbitro) {
        this.objArbitro = objArbitro;
    }

    public Arbitro getSelArbitro() {
        return selArbitro;
    }

    public void setSelArbitro(Arbitro selArbitro) {
        this.selArbitro = selArbitro;
    }

    public String getCriterioBusqueda() {
        return criterioBusqueda;
    }

    public void setCriterioBusqueda(String criterioBusqueda) {
        this.criterioBusqueda = criterioBusqueda;
    }
    
    

}
