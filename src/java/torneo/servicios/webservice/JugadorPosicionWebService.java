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
import torneo.logica.entidades.JugadorPosicion;
import torneo.logica.servicios.ServicioJugadorPosicion;

/**
 *
 * @author USER
 */
@WebService(serviceName = "JugadorPosicionWebService")
public class JugadorPosicionWebService {

    @WebMethod(operationName = "Obtener_Jugador")
    public ArrayList<JugadorPosicion> obtenerJugadorPosicions() {
        ArrayList<JugadorPosicion> lstJugadorPosicions = new ArrayList<JugadorPosicion>();
        try {
            lstJugadorPosicions = ServicioJugadorPosicion.ObtenerJugadorPosicion();
        } catch (Exception e) {
            System.out.println("public ArrayList<JugadorPosicion> obtenerJugadorPosicions() dice: " + e.getMessage());
        }
        return lstJugadorPosicions;
    }

    @WebMethod(operationName = "Insertar")
    public boolean insertarJugadorPosicion(JugadorPosicion jugadorPosicion) throws Exception {
        boolean band=false;
        try {
            if (ServicioJugadorPosicion.insertar(jugadorPosicion)== true) {
                band=true;
                System.out.println("Correcto");
            } else {
                System.out.println("Error");
            }
        } catch (Exception e) {
            System.out.println("insertarJugadorPosicion() dice: " + e.getMessage());
        }
        return band;
    }

    @WebMethod(operationName = "actualizar")
    public boolean actualizarJugadorPosicion(JugadorPosicion jugadorPosicion) throws Exception {
        boolean band = false;
        try {
            if (ServicioJugadorPosicion.Actualizar(jugadorPosicion) == true) {
                System.out.println("Correcto");
            } else {
                System.out.println("Error");
            }
        } catch (Exception e) {
            System.out.println("actualizarJugadorPosicion() dice: " + e.getMessage());
        }
        return band;
    }

    @WebMethod(operationName = "Buscar")
    public JugadorPosicion buscarJugadorPosicionPorId(@WebParam(name = "id") int id) throws Exception {
        JugadorPosicion jugadorPosicion = new JugadorPosicion();
        try {
            jugadorPosicion = ServicioJugadorPosicion.ObtenerJugadorPosicionDadoCodigo(id);
        } catch (Exception e) {
            throw new Exception("No Existe el Jugador con ese Id");
        }
        return jugadorPosicion;
    }
    
    @WebMethod(operationName = "eliminar")
    public boolean eliminarJugadorPosicion(@WebParam(name = "id") int id) throws Exception {
        boolean band=false;
        JugadorPosicion jugadorPosicion = new JugadorPosicion();
        try {
            jugadorPosicion = ServicioJugadorPosicion.ObtenerJugadorPosicionDadoCodigo(id);
            if(ServicioJugadorPosicion.Eliminar((int)jugadorPosicion.getIdJugadorPosicion())==true){
                band=true;
            }else{
                band=false;
            }
        } catch (Exception e) {
            throw new Exception("No Existe el Jugador con ese Id");
        }
        return band;
    }
}
