package mvc;

import negocio.Odontologo;
import service.OdontologoService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.parser.ContentModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelAdministradorOdontologo extends JPanel {
    private PanelManager panelManager ;
    private JPanel panelTitulo;
    private JPanel panelBotonera;
    private JScrollPane pane;
    private static final int X = 2;

    public PanelAdministradorOdontologo (PanelManager panelManager){
        this.panelManager = panelManager;
    }


    public void armarPanelAdminOdontologo() {
        setLayout(new BorderLayout(0, 5));
        setBackground(Colores.COLOR_CUATRO);

        panelTitulo = new JPanel();
        panelTitulo.setBackground(Colores.COLOR_CUATRO);
        JLabel titulo = new JLabel("Odontologos", SwingConstants.CENTER);
        titulo.setFont(Fuentes.FUENTE_DOS);

        panelTitulo.add(titulo);
        add(panelTitulo, BorderLayout.NORTH);

        JTable tabla = armarTabla();

        JScrollPane pane = new JScrollPane();
        pane.setViewportView(tabla);
        pane.getViewport().setBackground(Colores.COLOR_CUATRO);
        pane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pane.setBackground(Colores.COLOR_CUATRO);

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
        OdontologoService odontologoService = new OdontologoService();
        ArrayList<Odontologo> odontologos = odontologoService.listarOdontologo();

        String[] nombresColumnas =
                {       "ID",
                        "Nombre",
                        "Apellido",
                        "Matricula"};

        DefaultTableModel contenidoTabla = new DefaultTableModel();  //Creacion de columnas
        for (String c : nombresColumnas) {
            contenidoTabla.addColumn(c);
        }

        for(Odontologo odon : odontologos) {             //Creacion de filas
            Object[] data = new Object[4];
            if(odon!= null){
                data[0] = odon.getId();
                data[1] = odon.getNombre();
                data[2] = odon.getApellido();
                data[3] = odon.getMatricula();
                contenidoTabla.addRow(data);
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
