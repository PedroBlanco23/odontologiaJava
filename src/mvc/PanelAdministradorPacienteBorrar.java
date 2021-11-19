package mvc;

import negocio.Turno;
import service.PacienteService;
import service.TurnoService;
import service.UsuarioService;

import javax.swing.*;
import java.util.ArrayList;

public class PanelAdministradorPacienteBorrar extends JPanel {
    PanelManager panelManager;

    public PanelAdministradorPacienteBorrar(PanelManager panelManager){
        this.panelManager= panelManager;
    }

    public void armarPanelAdministradorPacienteBorrar(JTable tabla ){
        int seleccion = tabla.getSelectedRow();

        long id = (long) tabla.getValueAt(seleccion, 0);

        //mensaje en espanol
        int resultado = JOptionPane.showOptionDialog(null, "¿Está seguro de eliminar este paciente?" , "Confirmación",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,  new String[]{"Eliminar", "Cancelar"},
                "default");
        //mensaje en ingles
        //int resultado = JOptionPane.showConfirmDialog(this,
           //     "Esta seguro de eliminar este paciente", "Confirmacion", JOptionPane.YES_NO_OPTION);
        if(resultado == JOptionPane.YES_OPTION) {
            PacienteService pacienteService = new PacienteService();
            UsuarioService usuarioService= new UsuarioService();
            TurnoService turnoService = new TurnoService();
            ArrayList<Turno> turnos = turnoService.listarTurno();
            for (Turno t :turnos) {
                if (t.getidPaciente()==id)
                    turnoService.eliminarTurno(t.getId());
            }

            Long idUser = (usuarioService.recuperarUsuarioPorPaciente(id)).getId();
            pacienteService.eliminarPaciente(id);
            usuarioService.eliminarUsuario(idUser);
            panelManager.mostrarAdministradorPacientes();
        }

    }

}
