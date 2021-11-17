package mvc;

import service.PacienteService;
import service.UsuarioService;
import negocio.*;

import java.awt.*;
import java.util.*;

import javax.swing.*;

public class PanelManager {

    public static final Color COLOR_PRINCIPAL = new Color(142, 148, 242);
    public static final Color COLOR_SECUNDARIO = new Color(187, 173, 255);
    public static final Color COLOR_TERCIARIO = new Color(221, 189, 252);

    private JFrame jframe;
    private PanelAdministrador panelAdministrador;
    private PanelPaciente panelPaciente;
    private PanelLogin panelLogin;
    private PanelPacienteVerTurnos panelPacienteVerTurnos;


    public void armarManager(){
        jframe = new JFrame();


        panelPaciente = new PanelPaciente(this);


        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mostrarLogin();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }

    public void mostrarLogin(){
        panelLogin = new PanelLogin(this);
        panelLogin.armarPanelLogin();
        jframe.setBounds(250,250,400,120);
        mostrarEnPantalla(panelLogin);
    }
    public void mostrarAdministrador() {
        panelAdministrador = new PanelAdministrador(this);
        panelAdministrador.armarPanelAdmin();
        jframe.setBounds(250,250,700,160);
        mostrarEnPantalla(panelAdministrador);
    }

    public void mostrarAdministradorPacientes(){
        PanelAdministradorPaciente panelAdministradorPaciente = new PanelAdministradorPaciente(this);
        panelAdministradorPaciente.armarPanelAdminPac();
        jframe.setBounds(250,250,700,500);
        mostrarEnPantalla(panelAdministradorPaciente);
    }

    public void mostrarAgregar(){
        JPanel panelAgregar = new JPanel(new GridLayout(2,7));
        String[] labels = {"ID",
                "Nombre",
                "Apellido",
                "Domicilio",
                "DNI",
                "Fecha de alta",
                "Usuario",
                "Contraseña"};
        for(String f : labels) panelAgregar.add(new JLabel(f));


        JTextField nombreField = new JTextField();
        panelAgregar.add(nombreField);
        JTextField apellidoField = new JTextField();
        panelAgregar.add(apellidoField);
        JTextField domicilioField = new JTextField();
        panelAgregar.add(domicilioField);
        JTextField dniField = new JTextField();
        panelAgregar.add(dniField);
        JTextField fechaDeAltaField = new JTextField();
        panelAgregar.add(fechaDeAltaField);
        JTextField usuarioField = new JTextField();
        panelAgregar.add(usuarioField);
        JTextField contrasenaField = new JTextField();
        panelAgregar.add(contrasenaField);


        int result = JOptionPane.showConfirmDialog(null, panelAgregar, "Agregar",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION){
            //creacion de paciente a raiz de datos proporcionados *falta fecha*
            Paciente nuevoPaciente = new Paciente(nombreField.getText(),apellidoField.getText(),domicilioField.getText(),Integer.parseInt(dniField.getText()));
            //creacion de usuario a raiz de datos y del nuevo paciente
            Usuario nuevoUsuario = new Usuario(usuarioField.getText(),contrasenaField.getText(),nuevoPaciente.getId());
            PacienteService pacienteService = new PacienteService();//nuevo service paciente
            pacienteService.guardarPaciente(nuevoPaciente);
            UsuarioService usuarioService = new UsuarioService(); //nuevo service usuario
            usuarioService.guardarUsuario(nuevoUsuario);
        }
    }

    public void mostrarPaciente(Usuario user){
        panelPaciente = new PanelPaciente(this);
        panelPaciente.armarPanelPaciente(user);
        jframe.setBounds(250,250,700,160);
        mostrarEnPantalla(panelPaciente);
    }

    public void mostrarPacienteVerTurnos(Usuario user) {
        panelPacienteVerTurnos = new PanelPacienteVerTurnos(this);
        panelPacienteVerTurnos.armarPanelPacienteVerTurnos(user);
        jframe.setBounds(250,250,700,500);
        mostrarEnPantalla(panelPacienteVerTurnos);
    }


    public void mostrarEnPantalla(JPanel panel) {
        jframe.setLocationRelativeTo(null);
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
                        Usuario user = usuarios.get(index);
                        if(user.getContraseña().equals(contrasena)) {
                            if (user.tieneRol("Admin")) {
                                mostrarAdministrador();
                            }else
                                mostrarPaciente(user);
                        } else {
                            mostrarPopUp("Contraseña invalida.");
                        }

                    }

                }

            }
        }


    }
}
