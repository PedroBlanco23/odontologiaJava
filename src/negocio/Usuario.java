package negocio;
import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {
    private long id;
    private String usuario;
    private String contraseña;
    private long idPaciente;
    private ArrayList<String> roles;

    public Usuario(String usuario, String contraseña, long idPaciente){
        this.usuario= usuario;
        this.contraseña= contraseña;
        this.idPaciente= idPaciente;
        roles = new ArrayList<String>();
    }

    public Usuario(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        roles = new ArrayList<String>();
    }
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public long getIdPaciente() { return idPaciente; }

    public void setIdPaciente(long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRol(String rol){
        roles.add(rol);
    }

    public boolean tieneRol(String rol){
        boolean pertenece = false;
        for(String r : roles){
            if (r.equals(rol)){
                pertenece=true;
            }
        }
        return pertenece;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", idPaciente=" + idPaciente +
                ", roles=" + roles +
                '}';
    }
}
