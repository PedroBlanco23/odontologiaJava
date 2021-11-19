package negocio;
import java.io.Serializable;
import java.util.Date;
public class Paciente extends Persona implements Serializable {

    private long id;
    private String domicilio;
    private int dni;
    private int mesAlta;
    private int diaAlta;

    public Paciente(long id, String nombre, String apellido, String domicilio, int dni, int mesAlta, int diaAlta) {
        super(nombre, apellido);
        this.id = id;
        this.domicilio = domicilio;
        this.dni = dni;
        this.mesAlta=mesAlta;
        this.diaAlta = diaAlta;
    }


    public Paciente(String nombre, String apellido, String domicilio, int dni, int mesAlta, int diaAlta){
        super(nombre,apellido);
        this.domicilio = domicilio;
        this.dni = dni;
        this.mesAlta=mesAlta;
        this.diaAlta = diaAlta;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getMesAlta() {
        return mesAlta;
    }

    public void setMesAlta(int mesAlta) {
        this.mesAlta = mesAlta;
    }

    public int getDiaAlta() {
        return diaAlta;
    }

    public void setDiaAlta(int diaAlta) {
        this.diaAlta = diaAlta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getNombre()+ " " + this.getApellido();
    }
}
