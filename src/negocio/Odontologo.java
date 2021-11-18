package negocio;

import java.io.Serializable;

public class Odontologo extends Persona implements Serializable {
    private long id;
    private int matricula;

    public Odontologo(String nombre, String apellido, int matricula){
        super(nombre,apellido);
        this.matricula = matricula;
    }



    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String toString() {
        return this.getNombre()+ " " + this.getApellido();
    }
}
