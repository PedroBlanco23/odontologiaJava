package service;

import dao.IOdontologoDAO;
import dao.OdontologoDAO;
import negocio.Odontologo;

import java.util.ArrayList;

public class OdontologoService {
    private IOdontologoDAO odontologoDAO;

    public OdontologoService(){
        odontologoDAO = new OdontologoDAO();
    }

    public void guardarOdontologo(Odontologo odontologo){
        
        odontologoDAO.guardar(odontologo);
    }

    public Odontologo recuperarOdontologo(long id){
        return odontologoDAO.buscarPorId(id);
    }

    public void eliminarOdontologo(long id){
        odontologoDAO.borrar(id);
    }

    public ArrayList<Odontologo> listarOdontologo() {
        return odontologoDAO.listar();
    }

}
