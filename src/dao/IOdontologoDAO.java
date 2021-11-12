package dao;

import negocio.Odontologo;
import java.util.*;


public interface IOdontologoDAO {

    public void guardar(Odontologo odontologo);

    public Odontologo buscarPorId(long id);

    public ArrayList<Odontologo> listar();

    public Odontologo borrar (long id);


}
