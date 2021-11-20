package mvc;
import negocio.Paciente;
import negocio.Usuario;
import service.PacienteService;
import service.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAdministradorPacienteEditar extends JPanel{
    private PanelManager panelManager;

    public PanelAdministradorPacienteEditar (PanelManager panelManager) {
        super(new GridLayout(2, 8));
        this.panelManager = panelManager;
        this.setBackground(Colores.COLOR_CUATRO);
    }

    public void armarPanelAdministradorPacienteEditar(JTable tabla) {

        String[] labels = {
                "ID",
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
        JComboBox<String> mesDeAltaCombo = new JComboBox<String>();
        JComboBox<String> diaDeAltaCombo = new JComboBox<String>();


        //Llenando con meses
        for (int i = 1; i <= 12; i++) {
            mesDeAltaCombo.addItem(panelManager.meses.get(i-1));
        }

        //Llenando con dias
        for (int i = 1; i <= panelManager.diasMes[panelManager.meses.indexOf(mesDeAltaCombo.getSelectedItem())]; i++) {
            diaDeAltaCombo.addItem(Integer.toString(i));
        }




        diaDeAltaCombo.setSelectedIndex((int)(tabla.getValueAt(seleccion, 6))-1);
        mesDeAltaCombo.setSelectedIndex(panelManager.meses.indexOf((String)tabla.getValueAt(seleccion,5)));




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




        add(mesDeAltaCombo);
        add(diaDeAltaCombo);


        JTextField usuarioField = new JTextField();
        usuarioField.setText(tabla.getValueAt(seleccion,7).toString());
        add(usuarioField);
        JTextField contrasenaField = new JTextField();
        contrasenaField.setText(tabla.getValueAt(seleccion,8).toString());
        add(contrasenaField);

        //pop up en ingles
//        int result = JOptionPane.showConfirmDialog(null, this, "Editar",
//                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        //pop up en espanol
        int result = JOptionPane.showOptionDialog(null, this, "Editar",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,  new String[]{"Editar", "Cancelar"},
                "default");
        if (result == JOptionPane.OK_OPTION){
            //creacion de paciente a raiz de datos proporcionados *falta fecha*
            Paciente nuevoPaciente = new Paciente(Long.parseLong(idField.getText()) ,nombreField.getText(),apellidoField.getText(),domicilioField.getText(),Integer.parseInt(dniField.getText()), (int)
             panelManager.meses.indexOf(mesDeAltaCombo.getSelectedItem())+1, Integer.parseInt((String) diaDeAltaCombo.getSelectedItem()));
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
