package torneo.logica.entidades;

public class Cancha {

    private int idCancha;
    private String descripcion;
    private String direccion;
    
    /*
     constructores
     */

    public Cancha() {
    }

    public Cancha(int idCancha, String descripcion, String direccion) {
        this.idCancha = idCancha;
        this.descripcion = descripcion;
        this.direccion = direccion;
    }
    
    /*
     getters y setters    
     */

    public int getIdCancha() {
        return idCancha;
    }

    public void setIdCancha(int idCancha) {
        this.idCancha = idCancha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    

}
