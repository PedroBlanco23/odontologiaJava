package mvc;

import service.UsuarioService;
import negocio.*;
import java.util.*;

import javax.swing.*;

public class PanelManager {
    private JFrame jframe;
    private PanelAdministrador panelAdministrador;
    private PanelPaciente panelPaciente;
    private PanelLogin panelLogin;


    public void armarManager(){
        jframe = new JFrame();

        panelLogin = new PanelLogin(this);
        panelLogin.armarPanelLogin();


        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mostrarLogin();
        jframe.setVisible(true);
    }

    public void mostrarLogin(){
        jframe.setBounds(200,200,400,150);
        mostrarEnPantalla(panelLogin);
    }

    public void mostrarEnPantalla(JPanel panel) {
        jframe.getContentPane().removeAll();
        jframe.getContentPane().add(panel);
        jframe.getContentPane().validate();
        jframe.getContentPane().repaint();
    }
    public void mostrarPopUp (String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void iniciarSesion(String usuario, String contrasena) {
        if (usuario.strip().equals("") && contrasena.strip().equals("")) {
            this.mostrarPopUp("Los campos están vacios.");
        } else {
            if(usuario.strip().equals("")){
                this.mostrarPopUp("El campo usuario está vacío.");
            } else {
                if(contrasena.strip().equals("")) {
                    this.mostrarPopUp("El campo contraseñá está vacio.");
                }
                else {
                    UsuarioService usuarioService = new UsuarioService();
                    ArrayList<Usuario> usuarios = usuarioService.listarUsuario();

                    int index = 0;
                    while(index < usuarios.size() && !usuarios.get(index).getUsuario().equals(usuario)){
                        index++;
                    }if(index >= usuarios.size()){
                        mostrarPopUp("No existe el usuario.");
                    } else {
                       String contrasenaUsuario = usuarios.get(index).getContraseña();
                        if(contrasenaUsuario.equals(contrasena)) {

                        } else {
                            mostrarPopUp("Contraseña invalida.");
                        }

                    }

                }

            }
        }


    }
}
