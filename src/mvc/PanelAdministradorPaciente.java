package mvc;

import service.PacienteService;
import service.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public void armarPanelAdminPac(){
        setLayout(new BorderLayout(0, 5));
        setBackground(panelManager.COLOR_PRINCIPAL);

        panelTitulo= new JPanel();
        panelTitulo.setBackground(panelManager.COLOR_SECUNDARIO);
        JLabel titulo = new JLabel("Pacientes", SwingConstants.CENTER);
        titulo.setFont(new Font(null, 0, 30));

        panelTitulo.add(titulo);
        add(panelTitulo, BorderLayout.NORTH);

        JTable tabla = armarTabla();
        tabla.setDefaultEditor(Object.class, null);

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
                //actualizo tabla
                remove(pane);
                JScrollPane nuevo = new JScrollPane();
                nuevo.add(armarTabla());
                add(nuevo);
            }
        });

    }

    public JTable armarTabla (){

        UsuarioService usuarioService = new UsuarioService();
        List<Usuario> usuarios = usuarioService.listarUsuario();

        PacienteService pacienteService = new PacienteService();
        int size  = pacienteService.listarPaciente().size();

        String[] nombresColumnas =
                {"ID",
                        "Nombre",
                        "Apellido",
                        "Domicilio",
                        "DNI",
                        "Fecha de alta",
                        "Usuario",
                        "Contraseña"};

        Object[][] data = new Object[size][8];

        int index = 0;
        int cont = 0;
        while (index < usuarios.size()){
            long idPaciente = usuarios.get(index).getIdPaciente();
            if (idPaciente != 0){
                Paciente paciente = pacienteService.recuperarPaciente(idPaciente);
                data[cont][0]= idPaciente;
                data[cont][1]= paciente.getNombre();
                data[cont][2]= paciente.getApellido();
                data[cont][3]= paciente.getDomicilio();
                data[cont][4]= paciente.getDni();
                data[cont][5]= paciente.getFechaAlta();
                data[cont][6]= usuarios.get(index).getUsuario();
                data[cont][7]= usuarios.get(index).getContraseña();
                cont++;
            }index++;
        }

        JTable tabla = new JTable(data, nombresColumnas);
        return tabla;
    }

}
