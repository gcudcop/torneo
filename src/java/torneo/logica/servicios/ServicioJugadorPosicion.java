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
import torneo.logica.entidades.JugadorPosicion;

public class ServicioJugadorPosicion {

    public static boolean insertar(JugadorPosicion jugadorposicion) throws Exception {
        boolean eje = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_insert_jugador_posicion(?,?,?)";
            lstP.add(new Parametro(1, jugadorposicion.getIdJugador().getIdJugador()));
            lstP.add(new Parametro(2, jugadorposicion.getIdPosicion().getIdPosicion()));
            lstP.add(new Parametro(3, jugadorposicion.getDorsal()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            while (rs.next()) {
                if (rs.getString(0).equals("true"));
                eje = true;
            }
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return eje;
    }

    public static boolean Actualizar(JugadorPosicion jugadorposicion) throws Exception {
        boolean eje = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_actualizar_jugador_posicion(?,?,?,?)";

            lstP.add(new Parametro(1, jugadorposicion.getIdJugador().getIdJugador()));
            lstP.add(new Parametro(2, jugadorposicion.getIdPosicion().getIdPosicion()));
            lstP.add(new Parametro(3, jugadorposicion.getDorsal()));
            lstP.add(new Parametro(4, jugadorposicion.getIdJugadorPosicion()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            while (rs.next()) {
                if (rs.getString(0).equals("true"));
                eje = true;
            }
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return eje;
    }

    public static boolean Eliminar(int codigo_jugador_posicion) throws Exception {
        boolean eje = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_eliminar_jugador_posicion(?)";
            lstP.add(new Parametro(1, codigo_jugador_posicion));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            while (rs.next()) {
                if (rs.getString(0).equals("true"));
                eje = true;
            }
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return eje;
    }

    public static ArrayList<JugadorPosicion> llenarJugadorPosicion(ConjuntoResultado rs) throws Exception {
        ArrayList<JugadorPosicion> lst = new ArrayList<JugadorPosicion>();
        JugadorPosicion jugadorposicion = null;
        try {
            while (rs.next()) {
                jugadorposicion = new JugadorPosicion(rs.getInt("pid_jugador_posicion"),
                        ServiciosJugador.obtenerJugadorPorId(rs.getInt("pid_jugador")),
                        ServicioPosicion.obtenerPosicionPorId(rs.getInt("pid_posicion")),
                        rs.getInt("pdorsal"));
                lst.add(jugadorposicion);
            }
        } catch (Exception e) {
            lst.clear();
            //  integracion.auditoria.log ublog = new integracion.auditoria.log();
            //    ublog.write("Modulo", "llenarModulos", e.getClass().getName(), e.getMessage());
            throw e;
        }
        return lst;
    }

    public static ArrayList<JugadorPosicion> ObtenerJugadorPosicion() throws Exception {
        ArrayList<JugadorPosicion> lst = new ArrayList<JugadorPosicion>();
        try {
            String sql = "select * from torneo.f_select_jugador_posicion()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarJugadorPosicion(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

        public static JugadorPosicion ObtenerJugadorPosicionDadoCodigo(int id_Jugador_posicion) throws Exception {
        JugadorPosicion lst;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_select_jugador_posicion_dado_id(?)";
            lstP.add(new Parametro(1, id_Jugador_posicion));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            lst = llenarJugadorPosicion(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }
    
}
