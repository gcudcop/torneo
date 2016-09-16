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
import torneo.logica.entidades.Arbitro;
import torneo.logica.entidades.Cancha;
import torneo.logica.entidades.Equipo;
import torneo.logica.entidades.Grupo;
import torneo.logica.entidades.Partido;
import torneo.logica.servicios.ServiciosArbitro;
import torneo.logica.servicios.ServiciosCancha;
import torneo.logica.servicios.ServiciosEquipo;
import torneo.logica.servicios.ServiciosGrupo;
import torneo.logica.servicios.ServiciosPartido;

@ManagedBean
@ViewScoped
public class CtrlPartido {

    private ArrayList<Partido> lstPartido;
    private Partido partido;
    private Partido partidoSel;
    private ArrayList<Equipo> lstEquipos;
    private ArrayList<Cancha> lstCancha;
    private ArrayList<Arbitro> lstArbitro;
    private ArrayList<Grupo> lstGrupos;
    private Date fecha;
    private Date hora;
    private int idEquipo1;
    private int idEquipo2;
    private int idCancha;
    private int idArbitro;
    private int idGrupo;

    public CtrlPartido() {
        this.init();
    }

    private void init() {
        this.partido=new Partido();
        this.partidoSel=new Partido();
        this.lstPartido = new ArrayList<Partido>();
        this.lstArbitro=new ArrayList<Arbitro>();
        this.obtenerPartidos();
        this.obtenerGrupos();
        this.obtenerCanchas();
        this.obtenerArbitros();
        this.obtenerEquipos();       
    }

    public void obtenerGrupos() {
        try {
            this.lstGrupos = ServiciosGrupo.obtenerGrupos();
        } catch (Exception e) {
        }
    }

    public void obtenerPartidos() {
        try {
            this.lstPartido = ServiciosPartido.obtenerPartido();
        } catch (Exception e) {
            System.out.println("obtener partidos dice: " + e.getMessage());
        }
    }

    public void obtenerEquiposDadoId() {
        try {
            //this.lstEquipos = ServiciosEquipo.obtenerEquipos();
            this.lstEquipos=ServiciosEquipo.obtenerEquiposDadoGrupo(idGrupo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void obtenerEquipos() {
        try {
            //this.lstEquipos = ServiciosEquipo.obtenerEquipos();
            this.lstEquipos=ServiciosEquipo.obtenerEquipos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void obtenerCanchas() {
        try {
            this.lstCancha = ServiciosCancha.obtenerCanchas();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void obtenerArbitros() {
        try {
            this.lstArbitro = ServiciosArbitro.obtenerArbitros();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void insertar() {
        try {
            partido.setFecha(Fechas.devolverFecha(fecha));
            partido.setHora(Fechas.devolverHora(hora));
            partido.setIdEquipo1(ServiciosEquipo.obtenerEquipoPorId(idEquipo1));
            partido.setIdEquipo2(ServiciosEquipo.obtenerEquipoPorId(idEquipo2));
            partido.setIdArbitro(ServiciosArbitro.obtenerArbitroPorId(idArbitro));
            partido.setIdCancha(ServiciosCancha.obtenerCanchaPorId(idCancha));
            if (ServiciosPartido.insertar(partido) == true) {
                this.init();
                this.partido = new Partido();
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                DefaultRequestContext.getCurrentInstance().execute("wdlgNuevoPartido.hide()");
            } else {
                FacesMessage mensajeError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "Datos no Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensajeError);
            }
        } catch (Exception e) {
            System.out.println("Insertar partido dice: "+e.getMessage());
        }
    }
    
    public void editar() {
        try {
            partidoSel.setFecha(Fechas.devolverFecha(fecha));
            partidoSel.setHora(Fechas.devolverHora(hora));
            partidoSel.setIdEquipo1(ServiciosEquipo.obtenerEquipoPorId(idEquipo1));
            partidoSel.setIdEquipo2(ServiciosEquipo.obtenerEquipoPorId(idEquipo2));
            partidoSel.setIdArbitro(ServiciosArbitro.obtenerArbitroPorId(idArbitro));
            partidoSel.setIdCancha(ServiciosCancha.obtenerCanchaPorId(idCancha));
            if (ServiciosPartido.editar(partidoSel) == true) {
                this.init();
                this.partidoSel = new Partido();
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                DefaultRequestContext.getCurrentInstance().execute("wdlgEditarPartido.hide()");
            } else {
                FacesMessage mensajeError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "Datos no Insertados");
                FacesContext.getCurrentInstance().addMessage(null, mensajeError);
            }
        } catch (Exception e) {
            System.out.println("Editar partido dice: "+e.getMessage());
        }
    }
    
    
    public void eliminarPartido() {
        try {
            if (ServiciosPartido.eliminarPartido((int) partidoSel.getIdPartido())) {
                this.init();
                DefaultRequestContext.getCurrentInstance().execute("wdlgEliminarPartido.hide()");
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
     ** getters y setters
     */
    public ArrayList<Partido> getLstPartido() {
        return lstPartido;
    }

    public void setLstPartido(ArrayList<Partido> lstPartido) {
        this.lstPartido = lstPartido;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Partido getPartidoSel() {
        return partidoSel;
    }

    public void setPartidoSel(Partido partidoSel) {
        this.partidoSel = partidoSel;
    }

    public ArrayList<Equipo> getLstEquipos() {
        return lstEquipos;
    }

    public void setLstEquipos(ArrayList<Equipo> lstEquipos) {
        this.lstEquipos = lstEquipos;
    }

    public ArrayList<Cancha> getLstCancha() {
        return lstCancha;
    }

    public void setLstCancha(ArrayList<Cancha> lstCancha) {
        this.lstCancha = lstCancha;
    }

    public ArrayList<Arbitro> getLstArbitro() {
        return lstArbitro;
    }

    public void setLstArbitro(ArrayList<Arbitro> lstArbitro) {
        this.lstArbitro = lstArbitro;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public int getIdEquipo1() {
        return idEquipo1;
    }

    public void setIdEquipo1(int idEquipo1) {
        this.idEquipo1 = idEquipo1;
    }

    public int getIdEquipo2() {
        return idEquipo2;
    }

    public void setIdEquipo2(int idEquipo2) {
        this.idEquipo2 = idEquipo2;
    }

    public int getIdCancha() {
        return idCancha;
    }

    public void setIdCancha(int idCancha) {
        this.idCancha = idCancha;
    }

    public int getIdArbitro() {
        return idArbitro;
    }

    public void setIdArbitro(int idArbitro) {
        this.idArbitro = idArbitro;
    }

    public ArrayList<Grupo> getLstGrupos() {
        return lstGrupos;
    }

    public void setLstGrupos(ArrayList<Grupo> lstGrupos) {
        this.lstGrupos = lstGrupos;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

}
