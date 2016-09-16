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
import torneo.logica.entidades.Jugador;

/**
 *
 * @author Usuario
 */
public class ServiciosJugador {

    public static ArrayList<Jugador> llenarDatos(ConjuntoResultado rs) throws Exception {
        ArrayList<Jugador> lst = new ArrayList<Jugador>();
        Jugador jugador = null;
        try {
            while (rs.next()) {
                jugador = new Jugador(rs.getInt("pid_jugador"),
                        rs.getString("pnombres"),
                        rs.getString("papellidos"),
                        rs.getDate("pfecha_nacimiento"),
                        ServiciosEquipo.obtenerEquipoPorId(rs.getInt("pid_equipo")));
                lst.add(jugador);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }
    
    public static Jugador obtenerJugadorPorId(int id) throws Exception {
        Jugador jugador = new Jugador();
        try {
            String sql = "select * from torneo.f_select_jugador_dado_id(?)";
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            lstP.add(new Parametro(1, id));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            jugador = llenarDatos(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return jugador;
    }
    
    public static boolean insertar(Jugador jugador) throws Exception {
        boolean band = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_insert_jugador(?,?,?,?)";
            lstP.add(new Parametro(1, jugador.getNombres()));
            lstP.add(new Parametro(2, jugador.getApellidos()));
            lstP.add(new Parametro(3, jugador.getFechaNacimiento()));
            lstP.add(new Parametro(4, jugador.getIdEquipo().getIdEquipo()));
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
    
    public static boolean actualizar(Jugador jugador) throws Exception {
        boolean band = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_actualizar_jugador(?,?,?,?,?)";
            lstP.add(new Parametro(1, jugador.getNombres()));
            lstP.add(new Parametro(2, jugador.getApellidos()));
            lstP.add(new Parametro(3, jugador.getFechaNacimiento()));
            lstP.add(new Parametro(4, jugador.getIdEquipo().getIdEquipo()));
            lstP.add(new Parametro(5, jugador.getIdJugador()));
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
    
     public static boolean eliminarJugador(int id) throws Exception {
        boolean band = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_eliminar_jugador(?)";
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
     
     public static ArrayList<Jugador> obtenerJugadores() throws Exception {
        ArrayList<Jugador> lst = new ArrayList<Jugador>();
        try {
            String sql = "select * from torneo.f_select_jugador()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarDatos(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }
}
