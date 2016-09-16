package torneo.logica.entidades;

public class Grupo {

    private int idGrupo;
    private String nombreGrupo;
    private Torneo idTorneo;

    /*
     constructores
     */
    public Grupo() {
    }

    public Grupo(int idGrupo, String nombreGrupo, Torneo idTorneo) {
        this.idGrupo = idGrupo;
        this.nombreGrupo = nombreGrupo;
        this.idTorneo = idTorneo;
    }

    public Torneo getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(Torneo idTorneo) {
        this.idTorneo = idTorneo;
    }

    /*
     getters y setters    
     */
    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

}
