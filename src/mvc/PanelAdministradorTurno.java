package mvc;

import negocio.Odontologo;
import negocio.Paciente;
import negocio.Turno;
import service.OdontologoService;
import service.PacienteService;
import service.TurnoService;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class PanelAdministradorTurno extends JPanel {
    private PanelManager panelManager ;
    private JPanel panelTitulo;
    private JPanel panelBotonera;
    private JScrollPane pane;
    private static final int X = 3;

    public PanelAdministradorTurno (PanelManager panelManager){
        this.panelManager = panelManager;
    }


    public void armarPanelAdminTurno() {
        setLayout(new BorderLayout(0, 5));
        setBackground(Colores.COLOR_CUATRO);

        panelTitulo = new JPanel();
        panelTitulo.setBackground(Colores.COLOR_TRES);
        JLabel titulo = new JLabel("Turnos", SwingConstants.CENTER);
        titulo.setFont(Fuentes.FUENTE_DOS);

        panelTitulo.add(titulo);
        add(panelTitulo, BorderLayout.NORTH);

        JTable tabla = armarTabla();

        JScrollPane pane = new JScrollPane();
        pane.setViewportView(tabla);
        pane.getViewport().setBackground(Colores.COLOR_CUATRO);
        pane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        add(pane, BorderLayout.CENTER);

        panelBotonera = new JPanel(new BorderLayout());
        JButton btnEditar = new JButton("Editar");
        JButton btnVolver = new JButton("Volver");
        JButton btnAgregar = new JButton("Agregar");
        JButton btnBorrar = new JButton("Borrar");

        JPanel botones = new JPanel();


        botones.add(btnAgregar);
        botones.add(btnBorrar);
        botones.add(btnEditar);
        panelBotonera.add(botones, BorderLayout.CENTER);
        panelBotonera.add(btnVolver, BorderLayout.SOUTH);

        add(panelBotonera, BorderLayout.SOUTH);


        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarAdministrador();
            }
        });

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarAgregar(X);
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabla.getSelectedRow() != -1) {
                    panelManager.mostrarEditar(X, tabla);
                }
            }
        });

        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabla.getSelectedRow() != -1) {
                    panelManager.mostrarBorrar(X, tabla);
                }
            }
        });
    }

    public JTable armarTabla(){

        TurnoService turnoService = new TurnoService();     //service de turno para recuperarlos
        ArrayList<Turno> turnos = turnoService.listarTurno();

        PacienteService pacienteService = new PacienteService(); //service de paciente para poner el nombre
        OdontologoService odontologoService = new OdontologoService(); //service de odontologo para poner el nombre

        String[] nombreColumnas={
                "ID",
                "Paciente",
                "Odontólogo",
                "Día",
                "Mes",
                "Hora"};

        DefaultTableModel contenidoTabla = new DefaultTableModel();

        for (String c: nombreColumnas) { //creacion de columnas
            contenidoTabla.addColumn(c);
        }

        //Meses
        ArrayList<String> meses = new ArrayList<String>(Arrays.asList("Enero", "Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto"
                ,"Septiembre",  "Octubre","Noviembre","Diciembre"));

        for (Turno turno: turnos) {
            Object[] data = new Object[6];
            if (turno!= null){
                long idPaciente = turno.getidPaciente();
                System.out.println(idPaciente);
                long idOdontologo = turno.getidOdontologo();

                data[0]= turno.getId();
                data[1]= pacienteService.recuperarPaciente(idPaciente).getNombre() + " " + pacienteService.recuperarPaciente(idPaciente).getApellido();
                data[2]= odontologoService.recuperarOdontologo(idOdontologo).getNombre() + " " + odontologoService.recuperarOdontologo(idOdontologo).getApellido();
                data[3]= turno.getDia();
                data[4]= meses.get((int) (turno.getMes()-1));
                data[5]= turno.getHora() + " hs";


                contenidoTabla.addRow(data); //creacion de fila
            }
        }

        JTable tabla = new JTable(contenidoTabla);
        tabla.setDefaultEditor(Object.class, null);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setFont(Fuentes.FUENTE_CUATRO);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingConstants.CENTER);
        tabla.setDefaultRenderer(Object.class, centerRenderer);
        tabla.getTableHeader().setFont(Fuentes.FUENTE_CINCO);
        tabla.setRowHeight(25);

        return tabla;

    }

}
