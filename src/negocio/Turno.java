package negocio;

import java.io.Serializable;
import java.util.Date;

public class Turno implements Serializable {
    private Date fechaTurno;
    private long id;
    private Odontologo odontologo;
    private Paciente paciente;

    public Turno(Odontologo odontologo, Paciente paciente, Date fechaTurno){
        this.odontologo = odontologo;
        this.paciente = paciente;
        this.fechaTurno = fechaTurno;
    }
    public Date getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(Date fecha) {
        this.fechaTurno = fecha;
    }


    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
