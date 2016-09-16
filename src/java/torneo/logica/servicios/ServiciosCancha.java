
package torneo.logica.servicios;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import accesodatos.Parametro;
import java.sql.SQLException;
import java.util.ArrayList;
import torneo.logica.entidades.Cancha;

public class ServiciosCancha {
    
public static ArrayList<Cancha> llenarDatos(ConjuntoResultado rs) throws Exception {
        ArrayList<Cancha> lst = new ArrayList<Cancha>();
        Cancha cancha = null;
        try {
            while (rs.next()) {
                cancha = new Cancha(rs.getInt("pid_cancha"),
                        rs.getString("pdescripcion"),
                        rs.getString("pdireccion"));
                lst.add(cancha);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }

    public static ArrayList<Cancha> obtenerCanchas() throws Exception {
        ArrayList<Cancha> lst = new ArrayList<Cancha>();
        try {
            String sql = "select * from torneo.f_select_cancha()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarDatos(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

    public static boolean insertar(Cancha cancha) throws Exception {
        boolean band = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_insert_cancha(?,?)";
            lstP.add(new Parametro(1, cancha.getDescripcion()));
            lstP.add(new Parametro(2, cancha.getDescripcion()));
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

    public static boolean actualizar(Cancha cancha) throws Exception {
        boolean band = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_actualizar_cancha(?,?,?)";
            lstP.add(new Parametro(1, cancha.getDescripcion()));
            lstP.add(new Parametro(2, cancha.getDireccion()));
            lstP.add(new Parametro(3, cancha.getIdCancha()));
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

    public static Cancha obtenerCanchaPorId(int id) throws Exception {
        Cancha cancha = new Cancha();
        try {
            String sql = "select * from torneo.f_select_cancha_dado_id(?)";
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            lstP.add(new Parametro(1, id));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            cancha = llenarDatos(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return cancha;
    }

public static boolean eliminarCancha(int id) throws Exception {
        boolean band = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_eliminar_cancha(?)";
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
    
   public static ArrayList<Cancha> obtenerCanchasPorNombre(String criterio) throws Exception {
        ArrayList<Cancha> lst = new ArrayList<Cancha>();
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_select_cancha_dado_descripcion(?)";
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

