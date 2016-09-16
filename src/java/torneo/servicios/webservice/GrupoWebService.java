package torneo.servicios.webservice;

import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import torneo.logica.entidades.Grupo;
import torneo.logica.servicios.ServiciosGrupo;

/**
 *
 * @author USER
 */
@WebService(serviceName = "GrupoWebService")
public class GrupoWebService {

    @WebMethod(operationName = "Obtener")
    public ArrayList<Grupo> obtenerGrupos() {
        ArrayList<Grupo> lstGrupos = new ArrayList<Grupo>();
        try {
            lstGrupos = ServiciosGrupo.obtenerGrupos();
        } catch (Exception e) {
            System.out.println("public ArrayList<Grupo> obtenerGrupos() dice: " + e.getMessage());
        }
        return lstGrupos;
    }

    @WebMethod(operationName = "Insertar")
    public boolean insertarGrupo(Grupo grupo) throws Exception {
        boolean band=false;
        try {
            if (ServiciosGrupo.insertar(grupo)== true) {
                band=true;
                System.out.println("Correcto");
            } else {
                System.out.println("Error");
            }
        } catch (Exception e) {
            System.out.println("insertarGrupo() dice: " + e.getMessage());
        }
        return band;
    }

    @WebMethod(operationName = "Actualizar")
    public boolean actualizarGrupo(Grupo grupo) throws Exception {
        boolean band = false;
        try {
            if (ServiciosGrupo.actualizar(grupo)== true) {
                System.out.println("Correcto");
            } else {
                System.out.println("Error");
            }
        } catch (Exception e) {
            System.out.println("actualizarGrupo() dice: " + e.getMessage());
        }
        return band;
    }

    @WebMethod(operationName = "Buscar")
    public Grupo buscarGrupoPorId(@WebParam(name = "id") int id) throws Exception {
        Grupo grupo = new Grupo();
        try {
            grupo = ServiciosGrupo.obtenerGrupoPorId(id);
        } catch (Exception e) {
            throw new Exception("No Existe el Grupo con ese Id");
        }
        return grupo;
    }
    
    @WebMethod(operationName = "eliminar")
    public boolean eliminarGrupo(@WebParam(name = "id") int id) throws Exception {
        boolean band=false;
        Grupo grupo = new Grupo();
        try {
            grupo = ServiciosGrupo.obtenerGrupoPorId(id);
            if(ServiciosGrupo.eliminarGrupo((int)grupo.getIdGrupo())==true){
                band=true;
            }else{
                band=false;
            }
        } catch (Exception e) {
            throw new Exception("No Existe el Grupo con ese Id");
        }
        return band;
    }
}
