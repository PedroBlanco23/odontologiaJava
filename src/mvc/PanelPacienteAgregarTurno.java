package mvc;

import negocio.Odontologo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

import negocio.Paciente;
import negocio.Turno;
import negocio.Usuario;
import service.OdontologoService;
import service.TurnoService;


public class PanelPacienteAgregarTurno extends JPanel {
    private PanelManager panelManager;
    private JComboBox<Odontologo> odontologoCombo;
    private JComboBox<String> diaCombo;
    private JComboBox<String> mesCombo;
    private JComboBox<String> horaCombo;

    private JLabel odontologolbl;
    private JLabel dialbl;
    private JLabel meslbl;
    private JLabel horalbl;
    public boolean cargando = false;

    public PanelPacienteAgregarTurno(PanelManager panelManager) {
        super(new GridLayout(2, 4));
        this.panelManager = panelManager;
        this.setBackground(Colores.COLOR_CUATRO);
    }

    public void armarPanelPacienteAgregarTurno (Paciente paciente) {
        odontologolbl = new JLabel("ODONTOLOGO", SwingConstants.CENTER);
        odontologolbl.setFont(Fuentes.FUENTE_SEIS);
        dialbl = new JLabel("DIA", SwingConstants.CENTER);
        dialbl.setFont(Fuentes.FUENTE_SEIS);

        meslbl = new JLabel("MES", SwingConstants.CENTER);
        meslbl.setFont(Fuentes.FUENTE_SEIS);

        horalbl = new JLabel("HORA", SwingConstants.CENTER);
        horalbl.setFont(Fuentes.FUENTE_SEIS);


        add(odontologolbl);
        add(meslbl);
        add(dialbl);
        add(horalbl);

        odontologoCombo = new JComboBox<Odontologo>();
        mesCombo = new JComboBox<String>();
        diaCombo = new JComboBox<String>();
        horaCombo = new JComboBox<String>();



        OdontologoService odontologoService = new OdontologoService();
        ArrayList<Odontologo> odontologos = odontologoService.listarOdontologo();
        for(Odontologo odo : odontologos) {
            odontologoCombo.addItem(odo);
        }



        odontologoCombo.setSelectedItem(null);
        diaCombo.setSelectedItem(null);
        mesCombo.setSelectedItem(null);
        horaCombo.setSelectedItem(null);




        odontologoCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diaCombo.removeAllItems();
                mesCombo.removeAllItems();
                horaCombo.removeAllItems();

                for (int i = 1; i <= 12; i++) {
                    mesCombo.addItem(panelManager.meses.get(i-1));
                }


                diaCombo.setSelectedItem(null);
                mesCombo.setSelectedItem(null);
                horaCombo.setSelectedItem(null);
            }
        });
        mesCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diaCombo.removeAllItems();
                horaCombo.removeAllItems();

                if(mesCombo.getSelectedItem() != null && !cargando) {
                    cargando = true;


                    for (int i = 1; i <= panelManager.diasMes[panelManager.meses.indexOf(mesCombo.getSelectedItem())]; i++) {
                        diaCombo.addItem(Integer.toString(i));
                    }


                horaCombo.removeAllItems();
                diaCombo.setSelectedItem(null);
                horaCombo.setSelectedItem(null);
                cargando = false;
            }
            }
        });

        diaCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                horaCombo.removeAllItems();
                if (diaCombo.getSelectedItem() != null && !cargando) {

                    TurnoService turnoService = new TurnoService();
                    ArrayList<Turno> turnos = turnoService.listarTurno();


                    Odontologo odontoActual = (Odontologo) odontologoCombo.getSelectedItem();
                    ArrayList<Long> horarios = new ArrayList<Long>();
                    for (long i = 8; i <= 18; i++) {
                        horarios.add(i);
                    }
                    for (Turno turno : turnos) {
                        if (turno.getidOdontologo() == ( (Odontologo) odontologoCombo.getSelectedItem()).getId()) {
                            if (turno.getMes() == panelManager.meses.indexOf((String) mesCombo.getSelectedItem()) + 1) {
                                if (turno.getDia() == Integer.parseInt((String) diaCombo.getSelectedItem())) {
                                    horarios.remove(turno.getHora());

                                }
                            }
                        }
                    }

                    for (Long hora : horarios) {
                        horaCombo.addItem(hora.toString());
                    }


                }
                horaCombo.setSelectedItem(null);
            }
        });



        add(odontologoCombo);
        add(mesCombo);

        add(diaCombo);


        add(horaCombo);




        int result = JOptionPane.showOptionDialog(null, this, "Registrar",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,  new String[]{"Registrar turno", "Cancelar"}, // this is the array
                "default");




        if (result == JOptionPane.OK_OPTION){
            if (horaCombo.getSelectedItem() != null) {
                TurnoService turnoService = new TurnoService();
                Turno turno = new Turno(((Odontologo) odontologoCombo.getSelectedItem()).getId(),
                        paciente.getId(), Long.parseLong((String) horaCombo.getSelectedItem()), Long.parseLong(( String) diaCombo.getSelectedItem()),
                        (long) panelManager.meses.indexOf((String) mesCombo.getSelectedItem()) +1);
                turnoService.guardar(turno);
            }

        }



/*
            //creacion de paciente a raiz de datos proporcionados falta fecha
            System.out.println(nombreField.getText()+apellidoField.getText()+domicilioField.getText()+Integer.parseInt(dniField.getText()));
            Paciente nuevoPaciente = new Paciente(nombreField.getText(),apellidoField.getText(),domicilioField.getText(),Integer.parseInt(dniField.getText()));
            //creacion de usuario a raiz de datos y del nuevo paciente
            Usuario nuevoUsuario = new Usuario(usuarioField.getText(),contrasenaField.getText(),nuevoPaciente.getId());
            PacienteService pacienteService = new PacienteService();//nuevo service paciente
            pacienteService.guardarPaciente(nuevoPaciente);
            UsuarioService usuarioService = new UsuarioService(); //nuevo service usuario
            usuarioService.guardarUsuario(nuevoUsuario);
            //actualizo tabla
            panelManager.mostrarAdministradorPacientes();

         */
        }

    }
