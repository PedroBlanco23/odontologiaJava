package mvc;

import negocio.Turno;
import service.OdontologoService;
import service.PacienteService;
import service.TurnoService;

import javax.swing.*;
import java.util.ArrayList;

public class PanelAdministradorOdontologoBorrar extends JPanel {
    PanelManager panelManager;

    public PanelAdministradorOdontologoBorrar(PanelManager panelManager){
        this.panelManager= panelManager;
    }

    public void armarPanelAdministradorOdontologoBorrar(JTable tabla ){
        int seleccion = tabla.getSelectedRow();

        long id = (long) tabla.getValueAt(seleccion, 0);

        //mensaje en espanol
        int resultado = JOptionPane.showOptionDialog(null, "¿Está seguro de eliminar este Odontólogo?" , "Confirmación",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,  new String[]{"Eliminar", "Cancelar"},
                "default");
        //mensaje en ingles
        //int resultado = JOptionPane.showConfirmDialog(this,
        //     "Esta seguro de eliminar este odontologo", "Confirmacion", JOptionPane.YES_NO_OPTION);
        if(resultado == JOptionPane.YES_OPTION) {
            OdontologoService odontologoService = new OdontologoService();
            TurnoService turnoService = new TurnoService();
            ArrayList<Turno> turnos = turnoService.listarTurno();
            for (Turno t :turnos) {
                if (t.getidOdontologo()==id)
                    turnoService.eliminarTurno(t.getId());
            }
            odontologoService.eliminarOdontologo(id);
            panelManager.mostrarAdministradorOdontologos();
        }

    }
}
