package service;

import dao.ITurnoDAO;
import dao.TurnoDAO;
import negocio.Turno;

import java.util.ArrayList;

public class TurnoService {
    private ITurnoDAO turnoDAO;

    public TurnoService(){
        turnoDAO = new TurnoDAO();
    }

    public void guardar(Turno turno){
        turnoDAO.guardar(turno);
    }

    public Turno recuperarTurno(long id){
        return turnoDAO.buscarPorId(id);
    }

    public void eliminarTurno(long id){
        turnoDAO.borrar(id);
    }

    public ArrayList<Turno> listarTurno() {
        return turnoDAO.listar();
    }

}
