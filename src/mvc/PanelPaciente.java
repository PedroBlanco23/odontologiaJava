package mvc;

import negocio.Paciente;
import negocio.Usuario;
import service.PacienteService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelPaciente extends JPanel {
    private PanelManager panelManager;
    private JPanel botoneraPrincipal;
    private JLabel userlbl;
    private JButton btnVerTurnos;
    private JButton btnRegistrarTurnos;
    private JButton btnVolver;
    private JPanel botoneraVolver;
    private PacienteService pacienteService;


    public PanelPaciente(PanelManager panelManager) {
        this.panelManager = panelManager;
    }
    public void armarPanelPaciente(Paciente paciente){
        setLayout(new BorderLayout(0, 5));
        setBackground(Colores.COLOR_SEIS);

        botoneraPrincipal = new JPanel();
        botoneraPrincipal.setLayout(new FlowLayout());
        botoneraPrincipal.setBackground(Colores.COLOR_OCHO);
        botoneraPrincipal.setBorder(new EmptyBorder(10,10,10,10));
        userlbl = new JLabel("User", SwingConstants.CENTER);
        userlbl.setFont(Fuentes.FUENTE_DOS);

        btnVerTurnos = new JButton("Turnos");
        btnVerTurnos.setFont(Fuentes.FUENTE_UNO);
        btnRegistrarTurnos = new JButton("Registrar turnos");
        btnRegistrarTurnos.setFont(Fuentes.FUENTE_UNO);
        btnVolver= new JButton("Volver");
        btnVolver.setFont(Fuentes.FUENTE_UNO);

        botoneraPrincipal.add(btnVerTurnos);
        botoneraPrincipal.add(btnRegistrarTurnos);

        add(userlbl, BorderLayout.NORTH);
        add(botoneraPrincipal, BorderLayout.CENTER);

        botoneraVolver = new JPanel();
        botoneraVolver.setBackground(Colores.COLOR_OCHO);
        botoneraVolver.setBorder(new EmptyBorder(5,5,5, 5));
        botoneraVolver.add(btnVolver);
        add(botoneraVolver, BorderLayout.SOUTH);

        pacienteService = new PacienteService();

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarLogin();
            }
        });

        btnVerTurnos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPacienteVerTurnos(paciente);
            }
        });

        btnRegistrarTurnos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPacienteAgregarTurno(paciente);
            }
        });


    }

}
