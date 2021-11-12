package dao;

import negocio.Usuario;

import java.util.ArrayList;

public class UsuarioDAO implements IUsuarioDAO {

    public static final String PATH = "Usuario.txt";
    @Override
    public void guardar(Usuario usuario) {
        Archivo archivo = new Archivo(PATH);
        ArrayList<Usuario> usuarios = this.listar();
        long max = 0;
        if(usuario.getId()==0){
            for(Usuario user: usuarios){
                if(user.getId() > max) {
                    max = user.getId();
                }
            }
            usuario.setId(max+1);
        }
        else {
            int index = 0;
            int i = 0;
            for (Usuario user: usuarios) {
                if(user.getId() == usuario.getId()){
                    index = i;
                }
                i++;

            }
            usuarios.remove(index);
        }
        usuarios.add(usuario);
        archivo.guardar(usuarios);


    }

    @Override
    public Usuario buscarPorId(long id) {
        ArrayList<Usuario> usuarios = this.listar();
        Usuario resultado = null;

        for(Usuario user : usuarios) {
            if (user.getId() == id)
                resultado = user;
        }

        return resultado;
    }

    @Override
    public ArrayList<Usuario> listar() {
        Archivo archivo = new Archivo(PATH);
        ArrayList lista = archivo.listar();
        ArrayList<Usuario> usuarios = new ArrayList<>();

        for(Object obj: lista)
            usuarios.add((Usuario) obj);

        return usuarios;
    }

    @Override
    public Usuario borrar(long id) {
        Usuario borrado;
        Archivo archivo = new Archivo(PATH);
        ArrayList<Usuario> usuarios = this.listar();

        int index = 0;
        int i = 0;
        for (Usuario user : usuarios) {
            if (user.getId() == id)
                index = i;
            i++;
        }
        borrado = usuarios.get(index);
        usuarios.remove(index);
        archivo.guardar(usuarios);
        return borrado;
    }
}