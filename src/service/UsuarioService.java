package service;

import dao.IUsuarioDAO;
import dao.UsuarioDAO;
import negocio.Usuario;

import java.util.ArrayList;

public class UsuarioService {
    private IUsuarioDAO usuarioDAO;

    public UsuarioService(){
        usuarioDAO = new UsuarioDAO();
    }

    public void guardarUsuario(Usuario usuario){

        usuarioDAO.guardar(usuario);
    }

    public Usuario recuperarUsuario(long id){
        return usuarioDAO.buscarPorId(id);
    }

    public Usuario recuperarUsuarioPorPaciente(long id){return usuarioDAO.buscarPorPaciente(id);}

    public Usuario eliminarUsuario(long id){
        return usuarioDAO.borrar(id);
    }

    public ArrayList<Usuario> listarUsuario() {
        return usuarioDAO.listar();
    }

}
