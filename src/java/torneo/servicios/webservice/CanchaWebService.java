
package torneo.servicios.webservice;

import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import torneo.logica.entidades.Cancha;
import torneo.logica.servicios.ServiciosCancha;

/**
 *
 * @author USER
 */
@WebService(serviceName = "CanchaWebService")
public class CanchaWebService {

    @WebMethod(operationName = "obtenerCanchas")
    public ArrayList<Cancha> obtenerCanchas() {
        ArrayList<Cancha> lstCanchas = new ArrayList<Cancha>();
        try {
            lstCanchas = ServiciosCancha.obtenerCanchas();
        } catch (Exception e) {
            System.out.println("public ArrayList<Cancha> obtenerCanchas() dice: " + e.getMessage());
        }
        return lstCanchas;
    }

    @WebMethod(operationName = "insertarCancha")
    public boolean insertarCancha(Cancha cancha) throws Exception {
        boolean band=false;
        try {
            if (ServiciosCancha.insertar(cancha) == true) {
                band=true;
                System.out.println("Correcto");
            } else {
                System.out.println("Error");
            }
        } catch (Exception e) {
            System.out.println("insertarCancha() dice: " + e.getMessage());
        }
        return band;
    }

    @WebMethod(operationName = "actualizarCancha")
    public boolean actualizarCancha(Cancha cancha) throws Exception {
        boolean band = false;
        try {
            if (ServiciosCancha.actualizar(cancha)== true) {
                System.out.println("Correcto");
            } else {
                System.out.println("Error");
            }
        } catch (Exception e) {
            System.out.println("actualizarCancha() dice: " + e.getMessage());
        }
        return band;
    }

    @WebMethod(operationName = "buscarCanchaPorId")
    public Cancha buscarCanchaPorId(@WebParam(name = "id") int id) throws Exception {
        Cancha cancha = new Cancha();
        try {
            cancha = ServiciosCancha.obtenerCanchaPorId(id);
        } catch (Exception e) {
            throw new Exception("No Existe la Cancha con ese Id");
        }
        return cancha;
    }
    
    @WebMethod(operationName = "eliminarCancha")
    public boolean eliminarCancha(@WebParam(name = "id") int id) throws Exception {
        boolean band=false;
        Cancha cancha = new Cancha();
        try {
            cancha = ServiciosCancha.obtenerCanchaPorId(id);
            if(ServiciosCancha.eliminarCancha((int)cancha.getIdCancha())==true){
                band=true;
            }else{
                band=false;
            }
        } catch (Exception e) {
            throw new Exception("No Existe la Cancha con ese Id");
        }
        return band;
    }
}
