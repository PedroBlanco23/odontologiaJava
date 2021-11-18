package mvc;

import negocio.Usuario;

import javax.swing.*;
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


    public PanelPaciente(PanelManager panelManager) {
        this.panelManager = panelManager;
    }
    public void armarPanelPaciente(Usuario user){
        setLayout(new BorderLayout(0, 5));
        setBackground(panelManager.COLOR_PRINCIPAL);

        botoneraPrincipal = new JPanel();
        botoneraPrincipal.setLayout(new FlowLayout());
        botoneraPrincipal.setBackground(panelManager.COLOR_SECUNDARIO);

        userlbl = new JLabel("User", SwingConstants.CENTER);
        userlbl.setFont(new Font(null, 0, 30));

        btnVerTurnos = new JButton("Turnos");
        btnRegistrarTurnos = new JButton("Registrar turnos");
        btnVolver= new JButton("Volver");

        botoneraPrincipal.add(btnVerTurnos);
        botoneraPrincipal.add(btnRegistrarTurnos);

        add(userlbl, BorderLayout.NORTH);
        add(botoneraPrincipal, BorderLayout.CENTER);

        botoneraVolver = new JPanel();
        botoneraVolver.setBackground(panelManager.COLOR_SECUNDARIO);
        botoneraVolver.add(btnVolver);
        add(botoneraVolver, BorderLayout.SOUTH);

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarLogin();
            }
        });

        btnVerTurnos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPacienteVerTurnos(user);
            }
        });

        btnRegistrarTurnos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPacienteAgregarTurno(user);
            }
        });


    }

}
