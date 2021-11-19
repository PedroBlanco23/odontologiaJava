package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAdministrador extends JPanel {
    private PanelManager panelManager;
    private JLabel adminlbl;
    private JButton btnTurnos;
    private JButton btnPacientes;
    private JButton btnOdontologos;
    private JButton btnUsuarios;
    private JButton btnVolver;
    private JPanel botoneraVolver;
    private JPanel botoneraPrincipal;


    public PanelAdministrador(PanelManager panel){
        this.panelManager = panel;
    }

    public void armarPanelAdmin(){
        setLayout(new BorderLayout(0, 5));
        setBackground(Colores.COLOR_CINCO);

        botoneraPrincipal = new JPanel();
        botoneraPrincipal.setLayout(new FlowLayout());
        botoneraPrincipal.setBackground(Colores.COLOR_SEIS);

        adminlbl = new JLabel("Admin User", SwingConstants.CENTER);
        adminlbl.setFont(Fuentes.FUENTE_DOS);

        btnTurnos = new JButton("Administrar Turnos");
        btnTurnos.setFont(Fuentes.FUENTE_UNO);
        btnPacientes = new JButton("Administrar Pacientes");
        btnPacientes.setFont(Fuentes.FUENTE_UNO);
        btnOdontologos = new JButton("Administrar Odontólogos");
        btnOdontologos.setFont(Fuentes.FUENTE_UNO);
        btnVolver= new JButton("Volver");
        btnVolver.setFont(Fuentes.FUENTE_UNO);
        botoneraPrincipal.add(btnTurnos);
        botoneraPrincipal.add(btnPacientes);
        botoneraPrincipal.add(btnOdontologos);


        add(adminlbl, BorderLayout.NORTH);

        add(botoneraPrincipal, BorderLayout.CENTER);

        botoneraVolver = new JPanel();
        botoneraVolver.setBackground(Colores.COLOR_SEIS);
        botoneraVolver.add(btnVolver);
        add(botoneraVolver, BorderLayout.SOUTH);


        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                panelManager.mostrarLogin();
            }
        });

        btnPacientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarAdministradorPacientes();
            }
        });


        btnTurnos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarAdministradorTurnos();
            }
        });

        btnOdontologos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarAdministradorOdontologos();
            }
        });


    }




}
