package negocio;

import java.io.Serializable;
import java.util.Date;

public class Turno implements Serializable {
    private long dia;
    private long mes;
    private long hora;
    private long id;
    private long idOdontologo;
    private long idPaciente;

    public Turno(long idOdontologo, long idPaciente, long hora,  long dia, long mes){
        this.idOdontologo = idOdontologo;
        this.idPaciente = idPaciente;
        this.hora = hora;
        this.dia = dia;
        this.mes = mes;
    }

    public void setFechaTurno(long hora, long dia, long mes) {
        this.hora = hora;
        this.dia = dia;
        this.mes = mes;
    }


    public long getidOdontologo() {
        return idOdontologo;
    }

    public void setidOdontologo(long idOdontologo) {
        this.idOdontologo = idOdontologo;
    }

    public long getidPaciente() {
        return idPaciente;
    }

    public void setidPaciente(long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDia(){
        return dia;
    }

    public long getHora(){
        return hora;
    }
    public long getMes(){
        return mes;
    }


}
