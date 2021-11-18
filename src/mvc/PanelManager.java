package mvc;

import service.PacienteService;
import service.UsuarioService;
import negocio.*;

import java.awt.*;
import java.util.*;

import javax.swing.*;

public class PanelManager {

    public static final Color COLOR_PRINCIPAL = new Color(221, 213, 208);
    public static final Color COLOR_SECUNDARIO = new Color(207, 192, 189);
    public static final Color COLOR_TERCIARIO = new Color(184, 184, 170);

    private JFrame jframe;
    private PanelAdministrador panelAdministrador;
    private PanelPaciente panelPaciente;
    private PanelLogin panelLogin;
    private PanelPacienteVerTurnos panelPacienteVerTurnos;
    private PanelPacienteAgregarTurno panelPacienteAgregarTurno;


    public void armarManager() {
        jframe = new JFrame();


        panelPaciente = new PanelPaciente(this);


        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mostrarLogin();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }

    public void mostrarLogin() {
        panelLogin = new PanelLogin(this);
        panelLogin.armarPanelLogin();
        jframe.setBounds(250, 250, 400, 120);
        mostrarEnPantalla(panelLogin);
    }

    public void mostrarAdministrador() {
        panelAdministrador = new PanelAdministrador(this);
        panelAdministrador.armarPanelAdmin();
        jframe.setBounds(250, 250, 700, 160);
        mostrarEnPantalla(panelAdministrador);
    }

    public void mostrarAdministradorPacientes() {
        PanelAdministradorPaciente panelAdministradorPaciente = new PanelAdministradorPaciente(this);
        panelAdministradorPaciente.armarPanelAdminPaciente();
        jframe.setBounds(250, 250, 700, 500);
        mostrarEnPantalla(panelAdministradorPaciente);
    }

    public void mostrarAgregar() {
        PanelAdministradorPacienteAgregar panelAdministradorPacienteAgregar = new PanelAdministradorPacienteAgregar(this);
        panelAdministradorPacienteAgregar.armarPanelAdministradorPacienteAgregar();
    }

    public void mostrarEditar(JTable tabla) {
        PanelAdministradorPacienteEditar panelAdministradorPacienteEditar = new PanelAdministradorPacienteEditar(this);
        panelAdministradorPacienteEditar.armarPanelAdministradorPacienteEditar(tabla);
    }

    public void mostrarBorrar(JTable tabla) {
        PanelAdministradorPacienteBorrar panelAdministradorPacienteBorrar = new PanelAdministradorPacienteBorrar(this);
        panelAdministradorPacienteBorrar.armarPanelAdministracionPacienteBorrar(tabla);
    }

    public void mostrarPaciente(Paciente paciente) {
        if (paciente == null) {
            this.mostrarPopUp("El usuario no tiene un paciente asociado.");
            this.mostrarLogin();
        } else {
            panelPaciente = new PanelPaciente(this);
            panelPaciente.armarPanelPaciente(paciente);
            jframe.setBounds(250, 250, 700, 160);
            mostrarEnPantalla(panelPaciente);
        }
    }

    public void mostrarPacienteVerTurnos( Paciente paciente) {
        panelPacienteVerTurnos = new PanelPacienteVerTurnos(this);
        panelPacienteVerTurnos.armarPanelPacienteVerTurnos(paciente);
        jframe.setBounds(250, 250, 700, 500);
        mostrarEnPantalla(panelPacienteVerTurnos);
    }

    public void mostrarPacienteAgregarTurno(Paciente paciente) {
        panelPacienteAgregarTurno = new PanelPacienteAgregarTurno(this);
        panelPacienteAgregarTurno.armarPanelPacienteAgregarTurno(paciente);

    }


    public void mostrarEnPantalla(JPanel panel) {
        jframe.setLocationRelativeTo(null);
        jframe.getContentPane().removeAll();
        jframe.getContentPane().add(panel);
        jframe.getContentPane().validate();
        jframe.getContentPane().repaint();
    }

    public void mostrarPopUp(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void iniciarSesion(String usuario, String contrasena) {
        if (usuario.strip().equals("") && contrasena.strip().equals("")) {
            this.mostrarPopUp("Los campos están vacios.");
        } else {
            if (usuario.strip().equals("")) {
                this.mostrarPopUp("El campo usuario está vacío.");
            } else {
                if (contrasena.strip().equals("")) {
                    this.mostrarPopUp("El campo contraseñá está vacio.");
                } else {
                    UsuarioService usuarioService = new UsuarioService();
                    ArrayList<Usuario> usuarios = usuarioService.listarUsuario();

                    int index = 0;
                    while (index < usuarios.size() && !usuarios.get(index).getUsuario().equals(usuario)) {
                        index++;
                    }

                    if (index >= usuarios.size()) {
                        mostrarPopUp("No existe el usuario.");
                    } else {
                        Usuario user = usuarios.get(index);
                        if (user.getContraseña().equals(contrasena)) {
                            if (user.tieneRol("Admin")) {
                                mostrarAdministrador();
                            } else {
                                PacienteService pacienteService = new PacienteService();
                                Paciente paciente = pacienteService.recuperarPaciente(user.getIdPaciente());

                                mostrarPaciente(paciente);
                            }
                        } else {
                            mostrarPopUp("Contraseña invalida.");

                        }
                    }
                }
            }
        }
    }
}