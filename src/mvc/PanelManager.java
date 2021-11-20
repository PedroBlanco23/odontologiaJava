package mvc;

import service.PacienteService;
import service.UsuarioService;
import negocio.*;

import java.awt.*;
import java.util.*;

import javax.swing.*;

public class PanelManager {


    private JFrame jframe;
    private PanelAdministrador panelAdministrador;
    private PanelPaciente panelPaciente;
    private PanelLogin panelLogin;
    private PanelPacienteVerTurnos panelPacienteVerTurnos;
    private PanelPacienteAgregarTurno panelPacienteAgregarTurno;

    public final ArrayList<String> meses = new ArrayList<String>(Arrays.asList("Enero", "Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto"
            ,"Septiembre",  "Octubre","Noviembre","Diciembre"));

    public final int[] diasMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};


    public void armarManager() {
        jframe = new JFrame();
        UIManager UI=new UIManager();
        UI.put("OptionPane.background", Colores.COLOR_CINCO);
        UI.put("Panel.background", Colores.COLOR_CINCO);
        UI.put("OptionPane.messageFont", Fuentes.FUENTE_TRES);
        UI.put("OptionPane.buttonFont", Fuentes.FUENTE_TRES);


        panelPaciente = new PanelPaciente(this);


        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mostrarLogin();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }

    public void mostrarLogin() {
        panelLogin = new PanelLogin(this);
        panelLogin.armarPanelLogin();
        jframe.setBounds(250, 250, 400, 195);
        mostrarEnPantalla(panelLogin);
    }

    public void mostrarAdministrador() {
        panelAdministrador = new PanelAdministrador(this);
        panelAdministrador.armarPanelAdmin();
        jframe.setBounds(250, 250, 700, 240);
        mostrarEnPantalla(panelAdministrador);
    }

    public void mostrarAdministradorPacientes() {
        PanelAdministradorPaciente panelAdministradorPaciente = new PanelAdministradorPaciente(this);
        panelAdministradorPaciente.armarPanelAdminPaciente();
        jframe.setBounds(250, 250, 1200, 500);
        mostrarEnPantalla(panelAdministradorPaciente);
    }

    public void mostrarAdministradorOdontologos(){
        PanelAdministradorOdontologo panelAdministradorOdontologo = new PanelAdministradorOdontologo(this);
        panelAdministradorOdontologo.armarPanelAdminOdontologo();
        jframe.setBounds(250, 250, 700, 500);
        mostrarEnPantalla(panelAdministradorOdontologo);
    }

    public void mostrarAdministradorTurnos(){
        PanelAdministradorTurno panelAdministradorTurno = new PanelAdministradorTurno(this);
        panelAdministradorTurno.armarPanelAdminTurno();
        jframe.setBounds(250, 250, 850, 500);
        mostrarEnPantalla(panelAdministradorTurno);
    }

    public void mostrarAgregar(int x) {  //recibe x si, que le dice de que boton es
        switch (x) {
            case 1: 
                PanelAdministradorPacienteAgregar panelAdministradorPacienteAgregar = new PanelAdministradorPacienteAgregar(this);
                panelAdministradorPacienteAgregar.armarPanelAdministradorPacienteAgregar();
                break;
            case 2:
                PanelAdministradorOdontologoAgregar panelAdministradorOdontologoAgregar = new PanelAdministradorOdontologoAgregar(this);
                panelAdministradorOdontologoAgregar.armarPanelAdministradorOdontologoAgregar();
                break;
            case 3:
                PanelAdministradorTurnoAgregar panelAdministradorTurnoAgregar = new PanelAdministradorTurnoAgregar(this);
                panelAdministradorTurnoAgregar.armarPanelAdministradorTurnoAgregar();
                break;
            default: break;
        }
    }

    public void mostrarEditar(int x, JTable tabla) { //recibe x si, que le dice de que boton es
        switch (x) {
            case 1:
                PanelAdministradorPacienteEditar panelAdministradorPacienteEditar = new PanelAdministradorPacienteEditar(this);
                panelAdministradorPacienteEditar.armarPanelAdministradorPacienteEditar(tabla);
                break;
            case 2:
                PanelAdministradorOdontologoEditar panelAdministradorOdontologoEditar = new PanelAdministradorOdontologoEditar(this);
                panelAdministradorOdontologoEditar.armarPanelAdministradorOdontologoEditar(tabla);
                break;
            case 3:
                PanelAdministradorTurnoEditar panelAdministradorTurnoEditar = new PanelAdministradorTurnoEditar(this);
                panelAdministradorTurnoEditar.armarPanelAdministradorTurnoEditar(tabla);
                break;
            default: break;
        }
    }

    public void mostrarBorrar(int x, JTable tabla) { //recibe x si, que le dice de que boton es
        switch (x) {
            case 1:
                PanelAdministradorPacienteBorrar panelAdministradorPacienteBorrar = new PanelAdministradorPacienteBorrar(this);
                panelAdministradorPacienteBorrar.armarPanelAdministradorPacienteBorrar(tabla);
                break;
            case 2:
                PanelAdministradorOdontologoBorrar panelAdministradorOdontologoBorrar = new PanelAdministradorOdontologoBorrar(this);
                panelAdministradorOdontologoBorrar.armarPanelAdministradorOdontologoBorrar(tabla);
                break;
            case 3:
                PanelAdministradorTurnoBorrar panelAdministradorTurnoBorrar = new PanelAdministradorTurnoBorrar(this);
                panelAdministradorTurnoBorrar.armarPanelAdministradorTurnoBorrar(tabla);
                break;
            default: break;
        }
    }
    

    
    
    public void mostrarPaciente(Paciente paciente) {
        if (paciente == null) {
            this.mostrarPopUp("El usuario no tiene un paciente asociado.");
            this.mostrarLogin();
        } else {
            panelPaciente = new PanelPaciente(this);
            panelPaciente.armarPanelPaciente(paciente);
            jframe.setBounds(250, 250, 700, 220);
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