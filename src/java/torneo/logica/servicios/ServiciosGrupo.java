package torneo.logica.servicios;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import accesodatos.Parametro;
import java.sql.SQLException;
import java.util.ArrayList;
import torneo.logica.entidades.Grupo;

public class ServiciosGrupo {

    public static ArrayList<Grupo> llenarDatos(ConjuntoResultado rs) throws Exception {
        ArrayList<Grupo> lst = new ArrayList<Grupo>();
        Grupo grupo = null;
        try {
            while (rs.next()) {
                grupo = new Grupo(rs.getInt("pid_grupo"),
                        rs.getString("pnombre_grupo"),
                        ServiciosTorneo.obtenerTorneoPorId(rs.getInt("pid_torneo")));
                lst.add(grupo);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }

    public static ArrayList<Grupo> obtenerGrupos() throws Exception {
        ArrayList<Grupo> lst = new ArrayList<Grupo>();
        try {
            String sql = "select * from torneo.f_select_grupo()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarDatos(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

    public static boolean insertar(Grupo grupo) throws Exception {
        boolean band = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_insert_grupo(?,?)";
            lstP.add(new Parametro(1, grupo.getNombreGrupo()));
            lstP.add(new Parametro(2, grupo.getIdTorneo().getIdTorneo()));
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

    public static boolean actualizar(Grupo grupo) throws Exception {
        boolean band = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_actualizar_grupo(?,?,?)";
            lstP.add(new Parametro(1, grupo.getIdGrupo()));
            lstP.add(new Parametro(2, grupo.getNombreGrupo()));
            lstP.add(new Parametro(3, grupo.getIdTorneo().getIdTorneo()));
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

    public static Grupo obtenerGrupoPorId(int id) throws Exception {
        Grupo grupo = new Grupo();
        try {
            String sql = "select * from torneo.f_select_grupo_dado_id(?)";
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            lstP.add(new Parametro(1, id));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            grupo = llenarDatos(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return grupo;
    }

    public static boolean eliminarGrupo(int id) throws Exception {
        boolean band = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_eliminar_grupo(?)";
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

    public static ArrayList<Grupo> obtenerGruposPorNombre(String criterio) throws Exception {
        ArrayList<Grupo> lst = new ArrayList<Grupo>();
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from torneo.f_select_grupo_dado_nombre(?)";
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
