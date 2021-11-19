package mvc;

import negocio.Paciente;
import negocio.Usuario;
import service.PacienteService;
import service.UsuarioService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAdministradorPacienteAgregar extends JPanel {
    private PanelManager panelManager;
    public PanelAdministradorPacienteAgregar (PanelManager panelManager) {
        super(new GridLayout(2, 8));
        this.panelManager = panelManager;
        this.setBackground(Colores.COLOR_CUATRO);

    }


    public void armarPanelAdministradorPacienteAgregar() {

        String[] labels = {
                "Nombre",
                "Apellido",
                "Domicilio",
                "DNI",
                "Mes de alta",
                "Dia de alta",
                "Usuario",
                "Contraseña"};

        for (String f : labels) {
            JLabel c = new JLabel(f);
            c.setFont(Fuentes.FUENTE_SEIS);
            add(c);
        };

        JTextField nombreField = new JTextField();
        add(nombreField);
        JTextField apellidoField = new JTextField();
        add(apellidoField);
        JTextField domicilioField = new JTextField();
        add(domicilioField);
        JTextField dniField = new JTextField();
        add(dniField);
        JComboBox<String> mesDeAltaCombo = new JComboBox<String>();
        add(mesDeAltaCombo);
        JComboBox<String> diaDeAltaCombo = new JComboBox<String>();
        add(diaDeAltaCombo);
        JTextField usuarioField = new JTextField();
        add(usuarioField);
        JTextField contrasenaField = new JTextField();
        add(contrasenaField);



        //añade meses

        for(String mes: panelManager.meses) {
            mesDeAltaCombo.addItem(mes);
        }
        mesDeAltaCombo.setSelectedItem(null);

        mesDeAltaCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diaDeAltaCombo.removeAllItems();

                if(mesDeAltaCombo.getSelectedItem() != null ) {
                    for (int i = 1; i <= panelManager.diasMes[panelManager.meses.indexOf(mesDeAltaCombo.getSelectedItem())]; i++) {
                        diaDeAltaCombo.addItem(Integer.toString(i));
                    }

                    diaDeAltaCombo.setSelectedItem(null);
                }
            }
        });



        int result = JOptionPane.showOptionDialog(null, this, "Agregar",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,  new String[]{"Añadir paciente", "Cancelar"},
                "default");

        if (result == JOptionPane.OK_OPTION){
            //creacion de paciente a raiz de datos proporcionados *falta fecha*
            Paciente nuevoPaciente = new Paciente(nombreField.getText(),apellidoField.getText(),domicilioField.getText(),Integer.parseInt(dniField.getText()),
                    (int) panelManager.meses.indexOf((String) mesDeAltaCombo.getSelectedItem()) +1, Integer.parseInt((String)diaDeAltaCombo.getSelectedItem()));
            //creacion de usuario a raiz de datos y del nuevo paciente
            PacienteService pacienteService = new PacienteService();//nuevo service paciente
            pacienteService.guardarPaciente(nuevoPaciente);

            UsuarioService usuarioService = new UsuarioService(); //nuevo service usuario
            Usuario nuevoUsuario = new Usuario(usuarioField.getText(),contrasenaField.getText(),nuevoPaciente.getId());
            usuarioService.guardarUsuario(nuevoUsuario);
            //actualizo tabla
            panelManager.mostrarAdministradorPacientes();
        }

    }
}
