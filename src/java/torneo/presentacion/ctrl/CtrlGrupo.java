package torneo.presentacion.ctrl;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;
import torneo.logica.entidades.Grupo;
import torneo.logica.entidades.Torneo;
import torneo.logica.servicios.ServiciosGrupo;
import torneo.logica.servicios.ServiciosTorneo;

@ManagedBean
@ViewScoped
public class CtrlGrupo {

    private ArrayList<Grupo> lstGrupos;
    private Grupo objGrupo;
    private Grupo selGrupo;
    private String criterioBusqueda;
    private int idTorneo;
    private ArrayList<Torneo> lstTorneos;

    private void init() {

        this.objGrupo = new Grupo();
        this.lstGrupos = new ArrayList<Grupo>();
        this.criterioBusqueda = new String();
        this.obtenerGrupos();
        this.obtenerTorneos();
    }

    public CtrlGrupo() {
        this.init();
    }

    public void obtenerGrupos() {
        try {
            lstGrupos = ServiciosGrupo.obtenerGrupos();
        } catch (Exception e) {
            System.out.println("public void obtenerGrupos() dice: " + e.getMessage());
        }
    }
    
    public void obtenerTorneos(){
        try {
            this.lstTorneos=ServiciosTorneo.obtenerTorneos();
        } catch (Exception e) {
        }
    }
            

    public void obtenerGruposPorNombre() {
        try {
            lstGrupos = ServiciosGrupo.obtenerGruposPorNombre(criterioBusqueda);
        } catch (Exception e) {
            System.out.println("public void obtenerGrupos() dice: " + e.getMessage());
        }
    }

   
    public void insertar() {
        try {
            objGrupo.setIdTorneo(ServiciosTorneo.obtenerTorneoPorId(idTorneo));
            if (ServiciosGrupo.insertar(objGrupo) == true) {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                DefaultRequestContext.getCurrentInstance().execute("wdlgNuevoGrupo.hide()");
                this.objGrupo = new Grupo();
            } else {
                FacesMessage mensajeError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "Datos no Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensajeError);
            }
        } catch (Exception e) {
            System.out.println("public void insertar() dice:" + e.getMessage());
        }
    }

    public void editarGrupo() {
        try {
            objGrupo.setIdTorneo(ServiciosTorneo.obtenerTorneoPorId(idTorneo));
            if (ServiciosGrupo.actualizar(selGrupo) == true) {
                this.init();
                this.selGrupo = new Grupo();
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                DefaultRequestContext.getCurrentInstance().execute("wdlgEditarGrupo.hide()");
            } else {
                FacesMessage mensajeError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "Datos no Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensajeError);
            }
        } catch (Exception e) {
            System.out.println("public void editarGrupo() dice: " + e.getMessage());
        }
    }

    public void eliminarGrupo() {
        try {
            if (ServiciosGrupo.eliminarGrupo((int) selGrupo.getIdGrupo())) {
                this.init();
                DefaultRequestContext.getCurrentInstance().execute("wdlgEliminarGrupo.hide()");
                Util.addSuccessMessage("Información eliminada.");
                System.out.println("public void eliminarGrupo() dice: Información eliminada.");
            } else {
                Util.addErrorMessage("Error al eliminar la información.");
                System.out.println("public void eliminarGrupo() dice: Error al eliminar la información");
            }
        } catch (Exception e) {
            Util.addErrorMessage("public void eliminarGrupo() dice: " + e.getMessage());
            System.out.println("public void eliminarGrupo() dice: " + e.getMessage());
        }

    }

    /*
     getters y setters
     */
    public ArrayList<Grupo> getLstGrupos() {
        return lstGrupos;
    }

    public void setLstGrupos(ArrayList<Grupo> lstGrupos) {
        this.lstGrupos = lstGrupos;
    }

    public Grupo getObjGrupo() {
        return objGrupo;
    }

    public void setObjGrupo(Grupo objGrupo) {
        this.objGrupo = objGrupo;
    }

    public Grupo getSelGrupo() {
        return selGrupo;
    }

    public void setSelGrupo(Grupo selGrupo) {
        this.selGrupo = selGrupo;
    }

    public String getCriterioBusqueda() {
        return criterioBusqueda;
    }

    public void setCriterioBusqueda(String criterioBusqueda) {
        this.criterioBusqueda = criterioBusqueda;
    }

    public int getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(int idTorneo) {
        this.idTorneo = idTorneo;
    }

    public ArrayList<Torneo> getLstTorneos() {
        return lstTorneos;
    }

    public void setLstTorneos(ArrayList<Torneo> lstTorneos) {
        this.lstTorneos = lstTorneos;
    }

}
