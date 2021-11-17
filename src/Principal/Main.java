package Principal;

import dao.IOdontologoDAO;
import dao.OdontologoDAO;
import dao.TurnoDAO;
import mvc.PanelManager;
import negocio.Odontologo;
import negocio.Paciente;
import negocio.Turno;
import negocio.Usuario;
import service.PacienteService;
import service.TurnoService;
import service.UsuarioService;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        PanelManager panelManager= new PanelManager();
        panelManager.armarManager();
/*
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = new Usuario("Pedro", "123");
        usuarioService.guardarUsuario(usuario);

        Paciente paciente = new Paciente("Pedro", "Blanco", "Mi casa", 44092382);
        PacienteService pacienteService = new PacienteService();
        pacienteService.guardarPaciente(paciente);
        System.out.println(pacienteService.recuperarPaciente(usuario.getIdPaciente()).getNombre());
*/





/*
        TurnoDAO turnoDAO = new TurnoDAO();
        Turno turno = new Turno(2, 1, 13,1,3);
        turnoDAO.guardar(turno);
*/
    }
}
