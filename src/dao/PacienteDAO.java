package dao;


import negocio.Paciente;

import java.util.ArrayList;

public class PacienteDAO implements IPacienteDAO {
    public static final String PATH = "Paciente.txt";

    @Override
    public void guardar(Paciente paciente) {
            Archivo archivo = new Archivo(PATH);
            ArrayList pacientes = archivo.listar();
            long max = 0;
            if(paciente.getId()==0){
                for(Object obj: pacientes){
                    if(((Paciente) obj).getId() > max) {
                        max = ((Paciente) obj).getId();
                    }
                }
                paciente.setId(max+1);
            }
            else {
                int index = 0;
                int i = 0;
                for (Object obj : pacientes) {

                    if (((Paciente)obj).getId() == paciente.getId()) {
                        index = i;
                    }
                    i++;

                }
                pacientes.remove(index);
            }
        pacientes.add(paciente);
        archivo.guardar(pacientes);
    }

    @Override
    public Paciente buscarPorId(long id) {
            ArrayList<Paciente> pacientes = this.listar();
            Paciente resultado = null;

            for(Paciente paci : pacientes) {
                if (paci.getId() == id)
                    resultado = paci;
            }

            return resultado;
    }

    @Override
    public ArrayList<Paciente> listar() {
            Archivo archivo = new Archivo(PATH);
            ArrayList lista = archivo.listar();
            ArrayList<Paciente> pacientes = new ArrayList<>();

            for (Object obj : lista)
                pacientes.add((Paciente) obj);

            return pacientes;
        }

    @Override
    public Paciente borrar(long id) {
        Paciente borrado;
        Archivo archivo = new Archivo(PATH);
        ArrayList<Paciente> pacientes = this.listar();

        int index = 0;
        int i = 0;
        for (Paciente odo : pacientes) {
            if (odo.getId() == id)
                index = i;
            i++;
        }
        borrado = pacientes.get(index);
        pacientes.remove(index);
        archivo.guardar(pacientes);
        return borrado;
    }
}
