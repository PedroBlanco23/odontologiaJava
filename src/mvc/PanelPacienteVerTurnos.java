package mvc;

import negocio.Odontologo;
import negocio.Paciente;
import negocio.Turno;
import negocio.Usuario;
import service.OdontologoService;
import service.PacienteService;
import service.TurnoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelPacienteVerTurnos extends JPanel {
    private PanelManager panelManager;
    private JPanel botoneraPrincipal;
    private JLabel turnoslbl;
    private JButton btnVolver;
    private JPanel botoneraVolver;

    private JTable jtable;
    private DefaultTableModel contenidoTable;
    private JScrollPane scrollPane;




    PanelPacienteVerTurnos(PanelManager panelManager) {
        this.panelManager = panelManager;
    }

    public void armarPanelPacienteVerTurnos (Usuario user) {
        setLayout(new BorderLayout(0, 5));
        setBackground(panelManager.COLOR_PRINCIPAL);


        botoneraPrincipal = new JPanel();
        botoneraPrincipal.setLayout(new FlowLayout());
        botoneraPrincipal.setBackground(panelManager.COLOR_SECUNDARIO);

        turnoslbl = new JLabel("Turnos", SwingConstants.CENTER);
        turnoslbl.setFont(new Font(null, 0, 30));
        botoneraPrincipal.add(turnoslbl);

        //Table
        contenidoTable = new DefaultTableModel();
        jtable = new JTable(contenidoTable);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(jtable);
        jtable.setDefaultEditor(Object.class, null);

        contenidoTable.addColumn("ODONTOLOGO");
        contenidoTable.addColumn("PACIENTE");
        contenidoTable.addColumn("DIA");

        TurnoService turnoService = new TurnoService();
        ArrayList<Turno> lista = turnoService.listarTurno();

        OdontologoService odontologoService = new OdontologoService();
        PacienteService pacienteService = new PacienteService();

        for (Turno turno: lista) {
            if(turno.getidPaciente() == user.getIdPaciente()) {
                Object[] row = new Object[3];
                Odontologo odontologo = odontologoService.recuperarOdontologo(turno.getidOdontologo());
                Paciente paciente = pacienteService.recuperarPaciente(turno.getidPaciente());


                row[0] = odontologo.getNombre() + " " + odontologo.getApellido();
                row[1] = paciente.getNombre() + " " + paciente.getApellido();
                row[2] = turno.getHora() + "hs " + turno.getDia()+"/" + turno.getMes();
                contenidoTable.addRow(row);
            }
        }



        btnVolver = new JButton("Volver");
        botoneraVolver = new JPanel();
        botoneraVolver.setBackground(panelManager.COLOR_SECUNDARIO);
        botoneraVolver.add(btnVolver);
        add(botoneraVolver, BorderLayout.SOUTH);
        add(botoneraPrincipal, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPaciente(user);
            }
        });





    }
}
