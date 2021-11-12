package dao;

import negocio.Odontologo;

import java.util.ArrayList;

public class OdontologoDAO implements IOdontologoDAO {
    public static final String PATH = "Odontologo.txt";
    @Override
    public void guardar(Odontologo odontologo) {
        Archivo archivo = new Archivo(PATH);
        ArrayList<Odontologo> odontologos = this.listar();
        long max = 0;
        if(odontologo.getId()==0){
            for(Odontologo odo: odontologos){
                if(odo.getId() > max) {
                    max = odo.getId();
                }
            }
            odontologo.setId(max+1);
        }
        else {
            int index = 0;
            int i = 0;
            for (Odontologo odo: odontologos) {
                if(odo.getId() == odontologo.getId()){
                        index = i;
                }
                i++;

            }
            odontologos.remove(index);
        }
        odontologos.add(odontologo);
        archivo.guardar(odontologos);


    }

    @Override
    public Odontologo buscarPorId(long id) {
        ArrayList<Odontologo> odontologos = this.listar();
        Odontologo resultado = null;

        for(Odontologo odo : odontologos) {
            if (odo.getId() == id)
                resultado = odo;
        }

        return resultado;
    }

    @Override
    public ArrayList<Odontologo> listar() {
        Archivo archivo = new Archivo(PATH);
        ArrayList lista = archivo.listar();
        ArrayList<Odontologo> odontologos = new ArrayList<>();

        for(Object obj: lista)
            odontologos.add((Odontologo) obj);

        return odontologos;
    }

    @Override
    public Odontologo borrar(long id) {
        Odontologo borrado;
        Archivo archivo = new Archivo(PATH);
        ArrayList<Odontologo> odontologos = this.listar();

        int index = 0;
        int i = 0;
        for (Odontologo odo : odontologos) {
            if (odo.getId() == id)
                index = i;
            i++;
        }
        borrado = odontologos.get(index);
        odontologos.remove(index);
        archivo.guardar(odontologos);
        return borrado;
    }
}
