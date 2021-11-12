package service;

import dao.IPacienteDAO;
import dao.PacienteDAO;
import negocio.Paciente;

import java.util.ArrayList;

public class PacienteService {
    private IPacienteDAO pacienteDAO;

    public PacienteService(){
        pacienteDAO = new PacienteDAO();
    }

    public void guardarPaciente(Paciente paciente){

        pacienteDAO.guardar(paciente);
    }

    public Paciente recuperarPaciente(long id){
        return pacienteDAO.buscarPorId(id);
    }

    public void eliminarPaciente(long id){
        pacienteDAO.borrar(id);
    }

    public ArrayList<Paciente> listarPaciente() {
        return pacienteDAO.listar();
    }

}
