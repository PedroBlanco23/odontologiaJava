package mvc;

import service.PacienteService;
import service.UsuarioService;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import negocio.*;

public class PanelAdministradorPaciente extends JPanel {
    private PanelManager panelManager;
    private JPanel panelTitulo;
    private JPanel panelBotonera;
    private JScrollPane pane;
    private static final int X = 1; // para indicar de donde son los botones



    public PanelAdministradorPaciente (PanelManager panel){
        this.panelManager = panel;
    }

    public void armarPanelAdminPaciente(){
        setLayout(new BorderLayout(0, 5));
        setBackground(Colores.COLOR_CINCO);

        panelTitulo= new JPanel();
        panelTitulo.setBackground(Colores.COLOR_TRES);
        JLabel titulo = new JLabel("Pacientes", SwingConstants.CENTER);
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

        UsuarioService usuarioService = new UsuarioService();
        List<Usuario> usuarios = usuarioService.listarUsuario();

        PacienteService pacienteService = new PacienteService();


        String[] nombresColumnas =
                {       "ID",
                        "Nombre",
                        "Apellido",
                        "Domicilio",
                        "DNI",
                        "Mes de Alta",
                        "Dia de Alta",
                        "Usuario",
                        "Contraseña"};

        DefaultTableModel contenidoTabla = new DefaultTableModel();
        for (String columna: nombresColumnas) {
            contenidoTabla.addColumn(columna);
        }


        for (Usuario user: usuarios) {
            long idPaciente = user.getIdPaciente();
            Object[] data = new Object[9];
            if (idPaciente != 0) {
                Paciente paciente = pacienteService.recuperarPaciente(idPaciente);
                if(paciente != null) {
                    data[0] = idPaciente;
                    data[1] = paciente.getNombre();
                    data[2] = paciente.getApellido();
                    data[3] = paciente.getDomicilio();
                    data[4] = paciente.getDni();
                    data[5] = panelManager.meses.get(paciente.getMesAlta()-1);
                    data[6] = paciente.getDiaAlta();
                    data[7] = user.getUsuario();
                    data[8] = user.getContraseña();
                    contenidoTabla.addRow(data);
                }
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

        tabla.getColumnModel().getColumn(0).setPreferredWidth(3);
        return tabla;
    }

}
