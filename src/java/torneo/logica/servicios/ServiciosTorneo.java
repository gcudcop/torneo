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
import torneo.logica.entidades.Torneo;

/**
 *
 * @author Usuario
 */
public class ServiciosTorneo {

    public static ArrayList<Torneo> llenarDatos(ConjuntoResultado rs) throws Exception {
        ArrayList<Torneo> lst = new ArrayList<Torneo>();
        Torneo torneo = null;
        try {
            while (rs.next()) {
                torneo = new Torneo(rs.getInt("pid_torneo"),
                        rs.getString("pdescripcion"),
                        rs.getInt("panio"),
                        rs.getDate("pfecha_inicio"),
                        rs.getDate("pfecha_fin"),
                        rs.getString("pauspiciante"));
                lst.add(torneo);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }
    
    public static ArrayList<Torneo> obtenerTorneos() throws Exception {
        ArrayList<Torneo> lst = new ArrayList<Torneo>();
        try {
            String sql = "select * from torneo.f_select_torneo()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarDatos(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }
    
    public static boolean insertar(Torneo torneo) throws Exception {
        boolean band = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_insert_torneo(?,?,?,?,?)";
            lstP.add(new Parametro(1, torneo.getDescripcion()));
            lstP.add(new Parametro(2, torneo.getAnio()));
            lstP.add(new Parametro(3, torneo.getFechaInicio()));
            lstP.add(new Parametro(4, torneo.getFechaFin()));
            lstP.add(new Parametro(5, torneo.getAuspiciante()));            
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
    
    public static boolean actualizar(Torneo torneo) throws Exception {
        boolean band = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_actualizar_torneo(?,?,?,?,?,?)";
            lstP.add(new Parametro(1, torneo.getDescripcion()));
            lstP.add(new Parametro(2, torneo.getAnio()));
            lstP.add(new Parametro(3, torneo.getFechaInicio()));
            lstP.add(new Parametro(4, torneo.getFechaFin()));
            lstP.add(new Parametro(5, torneo.getAuspiciante()));            
            lstP.add(new Parametro(6, torneo.getIdTorneo())); 
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

     public static Torneo obtenerTorneoPorId(int id) throws Exception {
        Torneo torneo = new Torneo();
        try {
            String sql = "select * from torneo.f_select_torneo_dado_id(?)";
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            lstP.add(new Parametro(1, id));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            torneo = llenarDatos(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return torneo;
    }
    
     
      public static boolean eliminarTorneo(int id) throws Exception {
        boolean band = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_eliminar_torneo(?)";
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
}
