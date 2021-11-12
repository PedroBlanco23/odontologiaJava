package dao;

import negocio.Turno;

import java.util.ArrayList;

public class TurnoDAO implements ITurnoDAO {
    public static final String PATH = "Turno.txt";
    @Override
    public void guardar(Turno turno) {
        Archivo archivo = new Archivo(PATH);
        ArrayList<Turno> turnos = this.listar();
        long max = 0;
        if(turno.getId()==0){
            for(Turno tur: turnos){
                if(tur.getId() > max) {
                    max = tur.getId();
                }
            }
            turno.setId(max+1);
        }
        else {
            int index = 0;
            int i = 0;
            for (Turno odo: turnos) {
                if(odo.getId() == turno.getId()){
                    index = i;
                }
                i++;

            }
            turnos.remove(index);
        }
        turnos.add(turno);
        archivo.guardar(turnos);



    }

    @Override
    public Turno buscarPorId(long id) {
        ArrayList<Turno> turnos = this.listar();
        Turno resultado = null;

        for(Turno odo : turnos) {
            if (odo.getId() == id)
                resultado = odo;
        }

        return resultado;
    }

    @Override
    public ArrayList<Turno> listar() {
        Archivo archivo = new Archivo(PATH);
        ArrayList lista = archivo.listar();
        ArrayList<Turno> turnos = new ArrayList<>();

        for(Object obj: lista)
            turnos.add((Turno) obj);

        return turnos;
    }

    @Override
    public Turno borrar(long id) {
        Turno borrado;
        Archivo archivo = new Archivo(PATH);
        ArrayList<Turno> turnos = this.listar();

        int index = 0;
        int i = 0;
        for (Turno odo : turnos) {
            if (odo.getId() == id)
                index = i;
            i++;
        }
        borrado = turnos.get(index);
        turnos.remove(index);
        archivo.guardar(turnos);
        return borrado;
    }
}
