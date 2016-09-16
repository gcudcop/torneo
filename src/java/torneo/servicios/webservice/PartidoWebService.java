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
import torneo.logica.entidades.Partido;
import torneo.logica.servicios.ServiciosPartido;

/**
 *
 * @author Usuario
 */
@WebService(serviceName = "PartidoWebService")
public class PartidoWebService {

   @WebMethod(operationName = "obtenerPartidos")
    public ArrayList<Partido> obtenerPartidos() {
        ArrayList<Partido> lstPartidos = new ArrayList<Partido>();
        try {
            lstPartidos = ServiciosPartido.obtenerPartido();
        } catch (Exception e) {
            System.out.println("public obtenerArbitros() dice: " + e.getMessage());
        }
        return lstPartidos;
    }
}
