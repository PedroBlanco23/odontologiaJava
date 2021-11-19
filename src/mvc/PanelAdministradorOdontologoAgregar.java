package mvc;

import negocio.Odontologo;
import negocio.Paciente;
import negocio.Usuario;
import service.OdontologoService;
import service.PacienteService;
import service.UsuarioService;

import javax.swing.*;
import java.awt.*;

public class PanelAdministradorOdontologoAgregar extends JPanel {
    private PanelManager panelManager;

    public PanelAdministradorOdontologoAgregar(PanelManager panelManager){
        super(new GridLayout(2, 3));
        this.panelManager = panelManager;
        this.setBackground(Colores.COLOR_CUATRO);
    }

    public void armarPanelAdministradorOdontologoAgregar(){
        String[] labels = {
                "Nombre",
                "Apellido",
                "Matricula"};

        for (String f : labels) {
            JLabel c = new JLabel(f);
            c.setFont(Fuentes.FUENTE_SEIS);
            add(c);
        };

        JTextField nombreField = new JTextField();
        add(nombreField);
        JTextField apellidoField = new JTextField();
        add(apellidoField);
        JTextField matriculaField = new JTextField();
        add(matriculaField);




        int result = JOptionPane.showOptionDialog(null, this, "Agregar",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,  new String[]{"Añadir Odontólogo", "Cancelar"},
                "default");
        if (result == JOptionPane.OK_OPTION){
            //creacion de odontologo a raiz de datos proporcionados *falta fecha*
            Odontologo nuevoOdontologo = new Odontologo(nombreField.getText(),apellidoField.getText(),Integer.parseInt(matriculaField.getText()));

            //crecion del service para guardarlo
            OdontologoService odontologoService = new OdontologoService();
            odontologoService.guardarOdontologo(nuevoOdontologo);

            //actualizo tabla
            panelManager.mostrarAdministradorOdontologos();
        }
    }
}
