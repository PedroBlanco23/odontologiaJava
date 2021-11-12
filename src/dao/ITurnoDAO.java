package dao;

import negocio.Turno;

import java.util.ArrayList;

public interface ITurnoDAO {
    public void guardar(Turno turno);

    public Turno buscarPorId(long id);

    public ArrayList<Turno>listar();

    public Turno borrar (long id);
}
