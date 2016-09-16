/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo.logica.servicios;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import accesodatos.Parametro;
import java.sql.SQLException;
import java.util.ArrayList;
import torneo.logica.entidades.Equipo;

/**
 *
 * @author Usuario
 */
public class ServiciosEquipo {

    public static ArrayList<Equipo> llenarDatos(ConjuntoResultado rs) throws Exception {
        ArrayList<Equipo> lst = new ArrayList<Equipo>();
        Equipo equipo = null;
        try {
            while (rs.next()) {
                equipo = new Equipo(rs.getInt("pid_equipo"),
                        rs.getString("pnombre_equipo"),
                        rs.getString("pprocedencia"),
                        rs.getDate("pfecha_creacion"),
                        ServiciosGrupo.obtenerGrupoPorId(rs.getInt("pid_grupo")));
                lst.add(equipo);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }
    
    public static ArrayList<Equipo> obtenerEquipos() throws Exception {
        ArrayList<Equipo> lst = new ArrayList<Equipo>();
        try {
            String sql = "select * from torneo.f_select_equipo()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarDatos(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }
    
    public static boolean insertar(Equipo equipo) throws Exception {
        boolean band = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_insert_equipo(?,?,?,?)";
            lstP.add(new Parametro(1, equipo.getNombre()));
            lstP.add(new Parametro(2, equipo.getProcedencia()));
            lstP.add(new Parametro(3, equipo.getFechaCreacion()));
            lstP.add(new Parametro(4, equipo.getIdGrupo().getIdGrupo()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            while (rs.next()) {
                if (rs.getString(0).equals("true"));
                band = true;
            }
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return band;
    }
    
    public static boolean actualizar(Equipo equipo) throws Exception {
        boolean band = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_actualizar_equipo(?,?,?,?,?)";
            lstP.add(new Parametro(1, equipo.getNombre()));
            lstP.add(new Parametro(2, equipo.getProcedencia()));
            lstP.add(new Parametro(3, equipo.getFechaCreacion()));
            lstP.add(new Parametro(4, equipo.getIdGrupo().getIdGrupo()));
            lstP.add(new Parametro(5, equipo.getIdEquipo()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            while (rs.next()) {
                if (rs.getString(0).equals("true"));
                band = true;
            }
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return band;
    }
    
     public static boolean eliminarEquipo(int id) throws Exception {
        boolean band = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_eliminar_equipo(?)";
            lstP.add(new Parametro(1, id));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            while (rs.next()) {
                if (rs.getString(0).equals("true"));
                band = true;
            }
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return band;
    }
     
     
     public static Equipo obtenerEquipoPorId(int id) throws Exception {
        Equipo equipo = new Equipo();
        try {
            String sql = "select * from torneo.f_select_equipo_dado_id(?)";
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            lstP.add(new Parametro(1, id));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            equipo = llenarDatos(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return equipo;
    }
     
     public static ArrayList<Equipo> obtenerEquiposDadoGrupo(int criterio) throws Exception {
        ArrayList<Equipo> lst = new ArrayList<Equipo>();
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_select_equipo_dado_id_grupo(?)";
            lstP.add(new Parametro(1, criterio));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            lst = llenarDatos(rs);
            rs = null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lst;
    }
}
