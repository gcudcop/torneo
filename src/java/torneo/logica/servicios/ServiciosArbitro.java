package torneo.logica.servicios;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import accesodatos.Parametro;
import java.sql.SQLException;
import java.util.ArrayList;
import torneo.logica.entidades.Arbitro;

public class ServiciosArbitro {

    public static ArrayList<Arbitro> llenarDatos(ConjuntoResultado rs) throws Exception {
        ArrayList<Arbitro> lst = new ArrayList<Arbitro>();
        Arbitro arbitro = null;
        try {
            while (rs.next()) {
                arbitro = new Arbitro(rs.getInt("pid_arbitro"),
                        rs.getString("pnombre"),
                        rs.getString("papellido"),
                        rs.getInt("pedad"));
                lst.add(arbitro);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }

    public static ArrayList<Arbitro> obtenerArbitros() throws Exception {
        ArrayList<Arbitro> lst = new ArrayList<Arbitro>();
        try {
            String sql = "select * from torneo.f_select_arbitro()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarDatos(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

    public static boolean insertar(Arbitro arbitro) throws Exception {
        boolean band = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_insert_arbitro(?,?,?)";
            lstP.add(new Parametro(1, arbitro.getNombre()));
            lstP.add(new Parametro(2, arbitro.getApellido()));
            lstP.add(new Parametro(3, arbitro.getEdad()));
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

    public static boolean actualizar(Arbitro arbitro) throws Exception {
        boolean band = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_actualizar_arbitro(?,?,?,?)";
            lstP.add(new Parametro(1, arbitro.getNombre()));
            lstP.add(new Parametro(2, arbitro.getApellido()));
            lstP.add(new Parametro(3, arbitro.getEdad()));
            lstP.add(new Parametro(4, arbitro.getIdArbitro()));
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

    public static Arbitro obtenerArbitroPorId(int id) throws Exception {
        Arbitro arbitro = new Arbitro();
        try {
            String sql = "select * from torneo.f_select_arbitro_dado_id(?)";
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            lstP.add(new Parametro(1, id));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            arbitro = llenarDatos(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return arbitro;
    }
    
    public static boolean eliminarArbitro(int id) throws Exception {
        boolean band = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_eliminar_arbitro(?)";
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
    
   public static ArrayList<Arbitro> obtenerArbitrosPorNombre(String criterio) throws Exception {
        ArrayList<Arbitro> lst = new ArrayList<Arbitro>();
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_select_arbitro_dado_apellido_nombre(?)";
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
