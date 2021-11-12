package dao;

import negocio.Paciente;

import java.util.ArrayList;

public interface IPacienteDAO {
    public void guardar(Paciente paciente);

    public Paciente buscarPorId(long id);

    public ArrayList<Paciente>listar();

    public Paciente borrar (long id);
}
