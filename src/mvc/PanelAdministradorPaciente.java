package mvc;

import service.PacienteService;
import service.UsuarioService;

import javax.swing.*;
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



    public PanelAdministradorPaciente (PanelManager panel){
        this.panelManager = panel;
    }

    public void armarPanelAdminPaciente(){
        setLayout(new BorderLayout(0, 5));
        setBackground(panelManager.COLOR_PRINCIPAL);

        panelTitulo= new JPanel();
        panelTitulo.setBackground(panelManager.COLOR_SECUNDARIO);
        JLabel titulo = new JLabel("Pacientes", SwingConstants.CENTER);
        titulo.setFont(new Font(null, 0, 30));

        panelTitulo.add(titulo);
        add(panelTitulo, BorderLayout.NORTH);

        JTable tabla = armarTabla();

        JScrollPane pane = new JScrollPane();
        pane.setViewportView(tabla);

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
                panelManager.mostrarAgregar();

            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
           public void actionPerformed(ActionEvent e) {
                panelManager.mostrarEditar(tabla);
           }
        });

        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabla.getSelectedRow() != -1) {
                    panelManager.mostrarBorrar(tabla);
                }
            }
        });
    }

    public JTable armarTabla(){

        UsuarioService usuarioService = new UsuarioService();
        List<Usuario> usuarios = usuarioService.listarUsuario();

        PacienteService pacienteService = new PacienteService();


        String[] nombresColumnas =
                {"ID",
                        "Nombre",
                        "Apellido",
                        "Domicilio",
                        "DNI",
                        "Fecha de alta",
                        "Usuario",
                        "Contraseña"};

        DefaultTableModel contenidoTabla = new DefaultTableModel();
        for (String columna: nombresColumnas) {
            contenidoTabla.addColumn(columna);
        }


        for (Usuario user: usuarios) {
            long idPaciente = user.getIdPaciente();
            Object[] data = new Object[8];
            if (idPaciente != 0) {
                Paciente paciente = pacienteService.recuperarPaciente(idPaciente);
                if(paciente != null) {
                    data[0] = idPaciente;
                    data[1] = paciente.getNombre();
                    data[2] = paciente.getApellido();
                    data[3] = paciente.getDomicilio();
                    data[4] = paciente.getDni();
                    data[5] = paciente.getFechaAlta();
                    data[6] = user.getUsuario();
                    data[7] = user.getContraseña();
                    contenidoTabla.addRow(data);
                }
            }
        }

        JTable tabla = new JTable(contenidoTabla);
        tabla.setDefaultEditor(Object.class, null);
        tabla.getTableHeader().setReorderingAllowed(false);
        return tabla;
    }

}
