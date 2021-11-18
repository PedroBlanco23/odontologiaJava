package mvc;

import service.PacienteService;

import javax.swing.*;

public class PanelAdministradorPacienteBorrar extends JPanel {
    PanelManager panelManager;

    public PanelAdministradorPacienteBorrar(PanelManager panelManager){
        this.panelManager= panelManager;
    }

    public void armarPanelAdministracionPacienteBorrar(JTable tabla ){
        int seleccion = tabla.getSelectedRow();

        long id = (long) tabla.getValueAt(seleccion, 0);


        int resultado = JOptionPane.showOptionDialog(null, "¿Está seguro de eliminar este paciente?" , "Confirmación",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,  new String[]{"Eliminar", "Cancelar"},
                "default");
        //int resultado = JOptionPane.showConfirmDialog(this,
           //     "Esta seguro de eliminar este paciente", "Confirmacion", JOptionPane.YES_NO_OPTION);
        if(resultado == JOptionPane.YES_OPTION) {
            PacienteService pacienteService = new PacienteService();
            pacienteService.eliminarPaciente(id);
            panelManager.mostrarAdministradorPacientes();
        }

    }

}
