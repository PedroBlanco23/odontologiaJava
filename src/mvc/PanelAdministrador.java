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
        setBackground(panelManager.COLOR_PRINCIPAL);

        botoneraPrincipal = new JPanel();
        botoneraPrincipal.setLayout(new FlowLayout());
        botoneraPrincipal.setBackground(panelManager.COLOR_SECUNDARIO);

        adminlbl = new JLabel("Admin User", SwingConstants.CENTER);
        adminlbl.setFont(new Font(null, 0, 30));

        btnTurnos = new JButton("Administrar Turnos");
        btnPacientes = new JButton("Administrar Pacientes");
        btnOdontologos = new JButton("Administrar Odntologos");
        btnVolver= new JButton("Volver");
        botoneraPrincipal.add(btnTurnos);
        botoneraPrincipal.add(btnPacientes);
        botoneraPrincipal.add(btnOdontologos);

        add(adminlbl, BorderLayout.NORTH);

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

        btnPacientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarAdministradorPacientes();
            }
        });


    }




}
