package negocio;
import java.io.Serializable;
import java.util.Date;
public class Paciente extends Persona implements Serializable {

    private long id;
    private String domicilio;
    private int dni;
    private Date fechaAlta;

    public Paciente(long id, String nombre, String apellido, String domicilio, int dni) {
        super(nombre, apellido);
        this.id = id;
        this.domicilio = domicilio;
        this.dni = dni;
    }

    public Paciente(String nombre, String apellido, String domicilio, int dni){
        super(nombre,apellido);
        this.domicilio = domicilio;
        this.dni = dni;
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

    public Date getFechaAlta() {
        return this.fechaAlta;
    }

    public void setFechaAlta(int año, int mes, int dia) {
        fechaAlta = new Date();
        fechaAlta.setYear(año);
        fechaAlta.setMonth(mes);
        fechaAlta.setDate(dia);
    }

    @Override
    public String toString() {
        return "Paciente{ id:"+ id + "nombre='" + super.getNombre() + '\'' + ", apellido='" + super.getApellido() + '\'' + ", domicilio='" + domicilio + '\'' + ", dni=" + dni + ", fechaAlta=" + fechaAlta + '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
