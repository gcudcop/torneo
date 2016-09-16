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
import torneo.logica.entidades.Grupo;
import torneo.logica.servicios.ServiciosEquipo;
import torneo.logica.servicios.ServiciosGrupo;

@ManagedBean
@ViewScoped

public class CtrlEquipo {

    private ArrayList<Equipo> lstEquipos;
    private Equipo objEquipo;
    private Equipo selEquipo;
    private String criterioBusqueda;
    private int idGrupo;
    private ArrayList<Grupo> lstGrupos;
    private Date fecha;

    private void init() {

        this.objEquipo = new Equipo();
        this.lstEquipos = new ArrayList<Equipo>();
        this.criterioBusqueda = new String();
        this.obtenerEquipos();
        this.obtenerGrupos();
    }

    public CtrlEquipo() {
        this.init();
    }

    public void obtenerEquipos() {
        try {
            this.lstEquipos = ServiciosEquipo.obtenerEquipos();
        } catch (Exception e) {
        }
    }

    public void obtenerGrupos() {
        try {
            lstGrupos = ServiciosGrupo.obtenerGrupos();
        } catch (Exception e) {
            System.out.println("public void obtenerGrupos() dice: " + e.getMessage());
        }
    }

    public void obtenerEquiposPorNombre() {
        try {
            lstEquipos = ServiciosEquipo.obtenerEquiposPorNombre(criterioBusqueda);
        } catch (Exception e) {
            System.out.println("public void obtenerGrupos() dice: " + e.getMessage());
        }
    }

    public void insertar() {
        try {
            objEquipo.setFechaCreacion(Fechas.devolverFecha(fecha));
            objEquipo.setIdGrupo(ServiciosGrupo.obtenerGrupoPorId(idGrupo));
            if (ServiciosEquipo.insertar(objEquipo) == true) {
                this.init();
                this.objEquipo = new Equipo();
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                DefaultRequestContext.getCurrentInstance().execute("wdlgNuevoEquipo.hide()");
            } else {
                FacesMessage mensajeError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "Datos no Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensajeError);
            }
        } catch (Exception e) {
            System.out.println("public void insertar() dice:" + e.getMessage());
        }
    }

    public void editarEquipo() {
        try {
            selEquipo.setFechaCreacion(Fechas.devolverFecha(fecha));
            selEquipo.setIdGrupo(ServiciosGrupo.obtenerGrupoPorId(idGrupo));
            if (ServiciosEquipo.actualizar(selEquipo) == true) {
                this.init();
                this.selEquipo = new Equipo();
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                DefaultRequestContext.getCurrentInstance().execute("wdlgEditarEquipo.hide()");
            } else {
                FacesMessage mensajeError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "Datos no Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensajeError);
            }
        } catch (Exception e) {
            System.out.println("public void editarEquipo() dice: " + e.getMessage());
        }
    }

    public void eliminarEquipo() {
        try {
            if (ServiciosEquipo.eliminarEquipo((int) selEquipo.getIdEquipo())) {
                this.init();
                DefaultRequestContext.getCurrentInstance().execute("wdlgEliminarEquipo.hide()");
                Util.addSuccessMessage("Información eliminada.");
                System.out.println("public void eliminarEquipo() dice: Información eliminada.");
            } else {
                Util.addErrorMessage("Error al eliminar la información.");
                System.out.println("public void eliminarEquipo() dice: Error al eliminar la información");
            }
        } catch (Exception e) {
            Util.addErrorMessage("public void eliminarEquipo() dice: " + e.getMessage());
            System.out.println("public void eliminarEquipo() dice: " + e.getMessage());
        }

    }

    /*
     getters y setters
     */
    public ArrayList<Equipo> getLstEquipos() {
        return lstEquipos;
    }

    public void setLstEquipos(ArrayList<Equipo> lstEquipos) {
        this.lstEquipos = lstEquipos;
    }

    public Equipo getObjEquipo() {
        return objEquipo;
    }

    public void setObjEquipo(Equipo objEquipo) {
        this.objEquipo = objEquipo;
    }

    public Equipo getSelEquipo() {
        return selEquipo;
    }

    public void setSelEquipo(Equipo selEquipo) {
        this.selEquipo = selEquipo;
    }

    public String getCriterioBusqueda() {
        return criterioBusqueda;
    }

    public void setCriterioBusqueda(String criterioBusqueda) {
        this.criterioBusqueda = criterioBusqueda;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public ArrayList<Grupo> getLstGrupos() {
        return lstGrupos;
    }

    public void setLstGrupos(ArrayList<Grupo> lstGrupos) {
        this.lstGrupos = lstGrupos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
