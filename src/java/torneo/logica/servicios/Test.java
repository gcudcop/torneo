/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo.logica.servicios;

import java.util.ArrayList;
import torneo.logica.entidades.Jugador;

/**
 *
 * @author Usuario
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Jugador> jugadores;
        
        try {
            jugadores=ServiciosJugador.obtenerJugadores();
            for(int i=0; i<jugadores.size();i++){
                System.out.println("nombre: "+jugadores.get(i).getNombres());
            }
        } catch (Exception e) {
        }
    }
    
}
