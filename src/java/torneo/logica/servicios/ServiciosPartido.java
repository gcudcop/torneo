package torneo.logica.servicios;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import accesodatos.Parametro;
import java.sql.SQLException;
import java.util.ArrayList;
import torneo.logica.entidades.Partido;

public class ServiciosPartido {
    
    public static ArrayList<Partido> llenarDatos(ConjuntoResultado rs) throws Exception {
        ArrayList<Partido> lst = new ArrayList<Partido>();
        Partido partido= null;
        try {
            while (rs.next()) {
                partido = new Partido(
                        rs.getInt("pid_partido"),
                        ServiciosEquipo.obtenerEquipoPorId(rs.getInt("pid_equipo1")),
                        ServiciosEquipo.obtenerEquipoPorId(rs.getInt("pid_equipo2")),
                        ServiciosCancha.obtenerCanchaPorId(rs.getInt("pid_cancha")),
                        ServiciosArbitro.obtenerArbitroPorId(rs.getInt("pid_arbitro")),
                        rs.getDate("pfecha"),
                        rs.getTime("phora")                        
                );
                lst.add(partido);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }
    
    public static ArrayList<Partido> obtenerPartido() throws Exception {
        ArrayList<Partido> lst = new ArrayList<Partido>();
        try {
            String sql = "select * from torneo.f_select_partido()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarDatos(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }
    
    public static boolean insertar(Partido partido) throws Exception {
        boolean band = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_insert_partido(?,?,?,?,?,?)";
            lstP.add(new Parametro(1, partido.getIdEquipo1().getIdEquipo()));
            lstP.add(new Parametro(2, partido.getIdEquipo2().getIdEquipo()));
            lstP.add(new Parametro(3, partido.getIdCancha().getIdCancha()));
            lstP.add(new Parametro(4, partido.getIdArbitro().getIdArbitro()));
            lstP.add(new Parametro(5, partido.getFecha()));
            lstP.add(new Parametro(6, partido.getHora()));
            
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
    
    public static boolean editar(Partido partido) throws Exception {
        boolean band = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_actualizar_partido(?,?,?,?,?,?,?)";
            lstP.add(new Parametro(1, partido.getIdEquipo1().getIdEquipo()));
            lstP.add(new Parametro(2, partido.getIdEquipo2().getIdEquipo()));
            lstP.add(new Parametro(3, partido.getIdCancha().getIdCancha()));
            lstP.add(new Parametro(4, partido.getIdArbitro().getIdArbitro()));
            lstP.add(new Parametro(5, partido.getFecha()));
            lstP.add(new Parametro(6, partido.getHora()));
            lstP.add(new Parametro(7, partido.getIdPartido()));
            
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
    
    public static boolean eliminarPartido(int id) throws Exception {
        boolean band = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_eliminar_partido(?)";
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
