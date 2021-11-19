package mvc;

import negocio.Odontologo;
import negocio.Paciente;
import negocio.Usuario;
import service.OdontologoService;
import service.PacienteService;
import service.UsuarioService;

import javax.swing.*;
import java.awt.*;

public class PanelAdministradorOdontologoEditar extends JPanel {
    private PanelManager panelManager;

    public PanelAdministradorOdontologoEditar (PanelManager panelManager){
        super(new GridLayout(2,4));
        this.panelManager = panelManager;
        this.setBackground(Colores.COLOR_CUATRO);
    }

    public void armarPanelAdministradorOdontologoEditar(JTable tabla){

        String[] labels = {
                "ID",
                "Nombre",
                "Apellido",
                "Matricula"};

        for (String f : labels) {
            JLabel c = new JLabel(f);
            c.setFont(Fuentes.FUENTE_SEIS);
            add(c);
        };

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
        JTextField matriculaField = new JTextField();
        matriculaField.setText(tabla.getValueAt(seleccion,3).toString());
        add(matriculaField);


        int result = JOptionPane.showConfirmDialog(null, this, "Editar",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION){
            //creacion de Odontologo a raiz de datos

            Odontologo nuevoOdontologo = new Odontologo(Long.parseLong(idField.getText()) ,nombreField.getText(),apellidoField.getText(),Integer.parseInt(matriculaField.getText()));

            OdontologoService odontologoService = new OdontologoService();//nuevo service Odontologo
            odontologoService.guardarOdontologo(nuevoOdontologo);

            //actualizo tabla
            panelManager.mostrarAdministradorOdontologos();
        }
    }
}
