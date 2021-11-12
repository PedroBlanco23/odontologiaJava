package dao;

import java.io.*;
import java.util.ArrayList;

public class Archivo {
    private String path;

    public Archivo(String path){
        this.path = path;
    }

    public void guardar(ArrayList lista)
    {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(lista);
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Object> listar(){
        ArrayList lista = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            Object objeto = objectInputStream.readObject();
            if(objeto != null) {
                lista = (ArrayList) objeto;
            }else
                lista = new ArrayList();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            lista = new ArrayList();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lista;

    }
}
