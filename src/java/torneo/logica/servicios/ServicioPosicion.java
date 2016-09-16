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
import torneo.logica.entidades.Posicion;

/**
 *
 * @author RUBEN
 */
public class ServicioPosicion {

    public static ArrayList<Posicion> llenarDatosPosicion(ConjuntoResultado rs) throws Exception {
        ArrayList<Posicion> lst = new ArrayList<Posicion>();
        Posicion posicion = null;
        try {
            while (rs.next()) {
                posicion = new Posicion(rs.getInt("pid_posicion"),
                        rs.getString("pdescripcion"));
                lst.add(posicion);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }

    public static ArrayList<Posicion> obtenerPosiciones() throws Exception {
        ArrayList<Posicion> lst = new ArrayList<Posicion>();
        try {
            String sql = "select * from torneo.f_select_posicion()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarDatosPosicion(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

    public static boolean insertarPosicion(Posicion posicion) throws Exception {
        boolean band = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_insert_posicion(?)";
            lstP.add(new Parametro(1, posicion.getDescripcion()));
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

    public static boolean actualizarPosicion(Posicion posicion) throws Exception {
        boolean band = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_actualizar_posicion(?,?)";
            lstP.add(new Parametro(1, posicion.getDescripcion()));
            lstP.add(new Parametro(2, posicion.getIdPosicion()));
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

    public static boolean eliminarPosicion(int id) throws Exception {
        boolean band = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_eliminar_posicion(?)";
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

    public static Posicion obtenerPosicionPorId(int id_posiciones) throws Exception {
        Posicion posicion = new Posicion();
        try {
            String sql = "select * from torneo.f_select_posicion_dado_id(?)";
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            lstP.add(new Parametro(1, id_posiciones));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            posicion = llenarDatosPosicion(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return posicion;
    }
    
    
}
