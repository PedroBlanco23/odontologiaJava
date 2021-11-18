package mvc;

import negocio.Paciente;
import negocio.Usuario;
import service.PacienteService;
import service.UsuarioService;

import javax.swing.*;
import java.awt.*;

public class PanelAdministradorPacienteAgregar extends JPanel {
    private PanelManager panelManager;
    public PanelAdministradorPacienteAgregar (PanelManager panelManager) {
        super(new GridLayout(2, 7));
        this.panelManager = panelManager;
    }

    public void armarPanelAdministradorPacienteAgregar() {

        String[] labels = {
                "Nombre",
                "Apellido",
                "Domicilio",
                "DNI",
                "Fecha de alta",
                "Usuario",
                "Contraseña"};
        for (String f : labels) add(new JLabel(f));

        JTextField nombreField = new JTextField();
        add(nombreField);
        JTextField apellidoField = new JTextField();
        add(apellidoField);
        JTextField domicilioField = new JTextField();
        add(domicilioField);
        JTextField dniField = new JTextField();
        add(dniField);
        JTextField fechaDeAltaField = new JTextField();
        add(fechaDeAltaField);
        JTextField usuarioField = new JTextField();
        add(usuarioField);
        JTextField contrasenaField = new JTextField();
        add(contrasenaField);

        int result = JOptionPane.showConfirmDialog(null, this, "Agregar",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION){
            //creacion de paciente a raiz de datos proporcionados *falta fecha*
            System.out.println(nombreField.getText()+apellidoField.getText()+domicilioField.getText()+Integer.parseInt(dniField.getText()));
            Paciente nuevoPaciente = new Paciente(nombreField.getText(),apellidoField.getText(),domicilioField.getText(),Integer.parseInt(dniField.getText()));
            //creacion de usuario a raiz de datos y del nuevo paciente
            Usuario nuevoUsuario = new Usuario(usuarioField.getText(),contrasenaField.getText(),nuevoPaciente.getId());
            PacienteService pacienteService = new PacienteService();//nuevo service paciente
            pacienteService.guardarPaciente(nuevoPaciente);
            UsuarioService usuarioService = new UsuarioService(); //nuevo service usuario
            usuarioService.guardarUsuario(nuevoUsuario);
            //actualizo tabla
            panelManager.mostrarAdministradorPacientes();
        }

    }
}
