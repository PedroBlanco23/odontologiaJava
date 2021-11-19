package Principal;

import dao.IOdontologoDAO;
import dao.OdontologoDAO;
import dao.TurnoDAO;
import mvc.PanelManager;
import negocio.Odontologo;
import negocio.Paciente;
import negocio.Turno;
import negocio.Usuario;
import service.OdontologoService;
import service.PacienteService;
import service.TurnoService;
import service.UsuarioService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        PanelManager panelManager = new PanelManager();
       panelManager.armarManager();

/*


       usuarioService.eliminarUsuario(6);

        System.out.println(usuarioService.eliminarUsuario(7));

        for (Usuario user: usuarios){
            System.out.println(user.getUsuario()+user.getContraseña());
        }

       Paciente paciente = new Paciente("Pedro", "Blanco", "Mi casa", 44092382);



      pacienteService.guardarPaciente(paciente);
*/
// UsuarioService usuarioService = new UsuarioService();
//        Usuario usuario = new Usuario("Admin", "123");
//      usuario.setRol("Admin");
//
//
//
//       usuarioService.guardarUsuario(usuario);
/*
        List<Usuario> usuarios = usuarioService.listarUsuario();
        for (Usuario user: usuarios){
            System.out.println(user.toString());
        }
    PacienteService pacienteService = new PacienteService();
        List<Paciente> pacientes = pacienteService.listarPaciente();


      for (Paciente p: pacientes){
          System.out.println(p.getId()+ p.getNombre());
      }*/
/*
      UsuarioService usuarioService = new UsuarioService();
      ArrayList<Usuario> usuarios = usuarioService.listarUsuario();
      for(Usuario u : usuarios) {
          System.out.println(u.getIdPaciente() + u.getUsuario() + u.getContraseña());
      }


        System.out.println(pacienteService.recuperarPaciente(1));

        OdontologoService odontologoService = new OdontologoService();
        odontologoService.eliminarOdontologo(0);




       TurnoDAO turnoDAO = new TurnoDAO();
        Turno turno = new Turno(2, 1, 13,1,3);
        turnoDAO.guardar(turno);

*/

        TurnoService turnoService= new TurnoService();
        List<Turno>turnos= turnoService.listarTurno();

        for(Turno t : turnos)
            System.out.println(t);


    }

}

