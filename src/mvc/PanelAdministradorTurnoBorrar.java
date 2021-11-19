package mvc;

import service.OdontologoService;
import service.TurnoService;

import javax.swing.*;

public class PanelAdministradorTurnoBorrar extends JPanel{
    private PanelManager panelManager;

    public PanelAdministradorTurnoBorrar(PanelManager panelManager) {
        this.panelManager = panelManager;
    }

    public void armarPanelAdministradorTurnoBorrar(JTable tabla){
        int seleccion = tabla.getSelectedRow();

        long id = (long) tabla.getValueAt(seleccion, 0);

        //mensaje en espanol
        int resultado = JOptionPane.showOptionDialog(null, "¿Está seguro de eliminar este Turno?" , "Confirmación",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,  new String[]{"Eliminar", "Cancelar"},
                "default");
        //mensaje en ingles
        //int resultado = JOptionPane.showConfirmDialog(this,
        //     "Esta seguro de eliminar este odontologo", "Confirmacion", JOptionPane.YES_NO_OPTION);
        if(resultado == JOptionPane.YES_OPTION) {
            TurnoService turnoService = new TurnoService();
            turnoService.eliminarTurno(id);
            panelManager.mostrarAdministradorTurnos();
        }

    }
}
