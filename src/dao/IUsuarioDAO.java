package dao;

import negocio.Usuario;

import java.util.ArrayList;

public interface IUsuarioDAO {
    public void guardar(Usuario usuario);

    public Usuario buscarPorId(long id);

    public Usuario buscarPorPaciente(long id);

    public ArrayList<Usuario>listar();

    public Usuario borrar (long id);
}
