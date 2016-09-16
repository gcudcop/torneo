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
import torneo.logica.entidades.Torneo;
import torneo.logica.servicios.ServiciosTorneo;

/**
 *
 * @author USER
 */
@WebService(serviceName = "TorneoWebService")
public class TorneoWebService {

    @WebMethod(operationName = "obtenerTorneos")
    public ArrayList<Torneo> obtenerTorneos() {
        ArrayList<Torneo> lstTorneos = new ArrayList<Torneo>();
        try {
            lstTorneos = ServiciosTorneo.obtenerTorneos();
        } catch (Exception e) {
            System.out.println("public ArrayList<Torneo> obtenertorneos() dice: " + e.getMessage());
        }
        return lstTorneos;
    }
    
    @WebMethod(operationName = "insertarTorneo")
    public boolean insertarTorneo(Torneo torneo) throws Exception {
        boolean band=false;
        try {
            if (ServiciosTorneo.insertar(torneo)== true) {
                band=true;
                System.out.println("Correcto");
            } else {
                System.out.println("Error");
            }
        } catch (Exception e) {
            System.out.println("insertarTorneo() dice: " + e.getMessage());
        }
        return band;
    }
    
    @WebMethod(operationName = "actualizarTorneo")
    public boolean actualizarTorneo(Torneo torneo) throws Exception {
        boolean band = false;
        try {
            if (ServiciosTorneo.actualizar(torneo)== true) {
                System.out.println("Correcto");
            } else {
                System.out.println("Error");
            }
        } catch (Exception e) {
            System.out.println("actualizarTorneo() dice: " + e.getMessage());
        }
        return band;
    }
    
    @WebMethod(operationName = "buscar")
    public Torneo buscarTorneosPorId(@WebParam(name = "id") int id) throws Exception {
        Torneo torneo = new Torneo();
        try {
            torneo = ServiciosTorneo.obtenerTorneoPorId(id);
        } catch (Exception e) {
            throw new Exception("No Existe el Torneo con ese Id");
        }
        return torneo;
    }
    
    @WebMethod(operationName = "eliminarTorneo")
    public boolean eliminarTorneo(@WebParam(name = "id") int id) throws Exception {
        boolean band=false;
        Torneo torneo = new Torneo();
        try {
            torneo = ServiciosTorneo.obtenerTorneoPorId(id);
            if(ServiciosTorneo.eliminarTorneo((int)torneo.getIdTorneo())==true){
                band=true;
            }else{
                band=false;
            }
        } catch (Exception e) {
            throw new Exception("No Existe el Torneo con ese Id");
        }
        return band;
    }
}
