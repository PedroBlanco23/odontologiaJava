package mvc;
import negocio.Paciente;
import negocio.Usuario;
import service.PacienteService;
import service.UsuarioService;

import javax.swing.*;
import java.awt.*;

public class PanelAdministradorPacienteEditar extends JPanel{
    private PanelManager panelManager;

    public PanelAdministradorPacienteEditar (PanelManager panelManager) {
        super(new GridLayout(2, 7));
        this.panelManager = panelManager;
    }

    public void armarPanelAdministradorPacienteEditar(JTable tabla) {

        String[] labels = {
                "ID",
                "Nombre",
                "Apellido",
                "Domicilio",
                "DNI",
                "Fecha de alta",
                "Usuario",
                "Contraseña"};

        for (String f : labels) add(new JLabel(f));

        int seleccion = tabla.getSelectedRow();

        JTextField idField = new JTextField();
        idField.setText(tabla.getValueAt(seleccion,0).toString());
        idField.setEditable(false);
        add(idField);
        JTextField nombreField = new JTextField();
        nombreField.setText(tabla.getValueAt(seleccion,1).toString());
        add(nombreField);
        JTextField apellidoField = new JTextField();
        apellidoField.setText(tabla.getValueAt(seleccion,2).toString());
        add(apellidoField);
        JTextField domicilioField = new JTextField();
        domicilioField.setText(tabla.getValueAt(seleccion,3).toString());
        add(domicilioField);
        JTextField dniField = new JTextField();
        dniField.setText(tabla.getValueAt(seleccion,4).toString());
        add(dniField);
        JTextField fechaDeAltaField = new JTextField();
        //fechaDeAltaField.setText(tabla.getValueAt(seleccion,5).toString());
        add(fechaDeAltaField);
        JTextField usuarioField = new JTextField();
        usuarioField.setText(tabla.getValueAt(seleccion,6).toString());
        add(usuarioField);
        JTextField contrasenaField = new JTextField();
        contrasenaField.setText(tabla.getValueAt(seleccion,7).toString());
        add(contrasenaField);

        int result = JOptionPane.showConfirmDialog(null, this, "Editar",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION){
            //creacion de paciente a raiz de datos proporcionados *falta fecha*
            System.out.println(nombreField.getText()+apellidoField.getText()+domicilioField.getText()+Integer.parseInt(dniField.getText()));
            Paciente nuevoPaciente = new Paciente(Long.parseLong(idField.getText()) ,nombreField.getText(),apellidoField.getText(),domicilioField.getText(),Integer.parseInt(dniField.getText()));
            //creacion de usuario a raiz de datos y del nuevo paciente
            PacienteService pacienteService = new PacienteService();//nuevo service paciente
            pacienteService.guardarPaciente(nuevoPaciente);

            UsuarioService usuarioService = new UsuarioService(); //nuevo service usuario
            Usuario nuevoUsuario = usuarioService.recuperarUsuarioPorPaciente(Long.parseLong(idField.getText())); //buscamos el usuario existente
            nuevoUsuario.setUsuario(usuarioField.getText());
            nuevoUsuario.setContraseña(contrasenaField.getText());
            usuarioService.guardarUsuario(nuevoUsuario);
            //actualizo tabla
            panelManager.mostrarAdministradorPacientes();
        }

    }
}
