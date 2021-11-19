package mvc;

import negocio.Odontologo;
import negocio.Paciente;
import negocio.Turno;
import negocio.Usuario;
import service.OdontologoService;
import service.PacienteService;
import service.TurnoService;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

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

    public void armarPanelPacienteVerTurnos (Paciente paciente) {
        setLayout(new BorderLayout(0, 5));
        setBackground(Colores.COLOR_CUATRO);
        botoneraPrincipal = new JPanel();
        botoneraPrincipal.setLayout(new FlowLayout());
        botoneraPrincipal.setBackground(Colores.COLOR_TRES);

        turnoslbl = new JLabel("Turnos", SwingConstants.CENTER);
        turnoslbl.setFont(Fuentes.FUENTE_DOS);
        botoneraPrincipal.add(turnoslbl);

        //Table
        contenidoTable = new DefaultTableModel();
        jtable = new JTable(contenidoTable);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(jtable);
        scrollPane.getViewport().setBackground(Colores.COLOR_CUATRO);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jtable.getTableHeader().setReorderingAllowed(false) ;
        jtable.setDefaultEditor(Object.class, null);
        jtable.setFont(Fuentes.FUENTE_CUATRO);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingConstants.CENTER);
        jtable.setDefaultRenderer(Object.class, centerRenderer);
        jtable.getTableHeader().setFont(Fuentes.FUENTE_CINCO);
        jtable.setRowHeight(25);

        contenidoTable.addColumn("ODONTOLOGO");
        contenidoTable.addColumn("PACIENTE");
        contenidoTable.addColumn("DIA");

        TurnoService turnoService = new TurnoService();
        ArrayList<Turno> lista = turnoService.listarTurno();

        OdontologoService odontologoService = new OdontologoService();

        for (Turno turno: lista) {
            if(turno.getidPaciente() == paciente.getId()) {
                Object[] row = new Object[3];
                Odontologo odontologo = odontologoService.recuperarOdontologo(turno.getidOdontologo());

                row[0] = odontologo.getNombre() + " " + odontologo.getApellido();
                row[1] = paciente.getNombre() + " " + paciente.getApellido();
                row[2] = turno.getHora() + "hs el " + turno.getDia()+" de " + panelManager.meses.get((int) turno.getMes()-1)  ;
                contenidoTable.addRow(row);
            }
        }



        btnVolver = new JButton("Volver");
        btnVolver.setFont(Fuentes.FUENTE_TRES);
        botoneraVolver = new JPanel();
        botoneraVolver.setBackground(Colores.COLOR_SEIS);
        botoneraVolver.add(btnVolver);
        add(botoneraVolver, BorderLayout.SOUTH);
        add(botoneraPrincipal, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPaciente(paciente);
            }
        });






    }
}
