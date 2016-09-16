/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo.servicios.webservice;

import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import torneo.logica.entidades.Arbitro;
import torneo.logica.servicios.ServiciosArbitro;

/**
 *
 * @author Geovanny
 */
@WebService(serviceName = "ArbitroWebService")
public class ArbitroWebService {

    @WebMethod(operationName = "obtenerArbitros")
    public ArrayList<Arbitro> obtenerArbitros() {
        ArrayList<Arbitro> lstArbitros = new ArrayList<Arbitro>();
        try {
            lstArbitros = ServiciosArbitro.obtenerArbitros();
        } catch (Exception e) {
            System.out.println("public ArrayList<Arbitro> obtenerArbitros() dice: " + e.getMessage());
        }
        return lstArbitros;
    }

    @WebMethod(operationName = "insertarArbitro")
    public boolean insertarArbitro(Arbitro arbitro) throws Exception {
        boolean band=false;
        try {
            if (ServiciosArbitro.insertar(arbitro) == true) {
                band=true;
                System.out.println("Correcto");
            } else {
                System.out.println("Error");
            }
        } catch (Exception e) {
            System.out.println("insertarArbitro() dice: " + e.getMessage());
        }
        return band;
    }

    @WebMethod(operationName = "actualizarArbitro")
    public boolean actualizarArbitro(Arbitro arbitro) throws Exception {
        boolean band = false;
        try {
            if (ServiciosArbitro.actualizar(arbitro) == true) {
                System.out.println("Correcto");
            } else {
                System.out.println("Error");
            }
        } catch (Exception e) {
            System.out.println("actualizarArbitro() dice: " + e.getMessage());
        }
        return band;
    }

    @WebMethod(operationName = "buscarPorId")
    public Arbitro buscarPorId(@WebParam(name = "id") int id) throws Exception {
        Arbitro arbitro = new Arbitro();
        try {
            arbitro = ServiciosArbitro.obtenerArbitroPorId(id);
        } catch (Exception e) {
            throw new Exception("No Existe el Arbitro con ese Id");
        }
        return arbitro;
    }
    
    @WebMethod(operationName = "eliminarArbitro")
    public boolean eliminarArbitro(@WebParam(name = "id") int id) throws Exception {
        boolean band=false;
        Arbitro arbitro = new Arbitro();
        try {
            arbitro = ServiciosArbitro.obtenerArbitroPorId(id);
            if(ServiciosArbitro.eliminarArbitro((int)arbitro.getIdArbitro())==true){
                band=true;
            }else{
                band=false;
            }
        } catch (Exception e) {
            throw new Exception("No Existe el Arbitro con ese Id");
        }
        return band;
    }
}
