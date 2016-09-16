package torneo.logica.entidades;

public class Arbitro {

    private int idArbitro;
    private String nombre;
    private String apellido;
    private int edad;

    /*
     constructores
     */
    public Arbitro() {
    }

    public Arbitro(int idArbitro, String nombre, String apellido, int edad) {
        this.idArbitro = idArbitro;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    /*
     getters y setters    
     */
    public int getIdArbitro() {
        return idArbitro;
    }

    public void setIdArbitro(int idArbitro) {
        this.idArbitro = idArbitro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

}
