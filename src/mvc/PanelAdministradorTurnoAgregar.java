package mvc;

import negocio.Odontologo;
import negocio.Paciente;
import negocio.Turno;
import negocio.Usuario;
import service.OdontologoService;
import service.PacienteService;
import service.TurnoService;
import service.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class PanelAdministradorTurnoAgregar extends JPanel{
    private PanelManager panelManager;
    public boolean cargando = false;
    
    public PanelAdministradorTurnoAgregar (PanelManager panelManager) {
        super(new GridLayout(2, 7));
        this.panelManager = panelManager;
        this.setBackground(Colores.COLOR_CUATRO);
    }

    public void armarPanelAdministradorTurnoAgregar() {

        String[] labels = {
                "Odontólogo",
                "Paciente",
                "Mes",
                "Día",
                "Hora",
                };

        for (String f : labels) {
            JLabel c = new JLabel(f);
            c.setFont(Fuentes.FUENTE_SEIS);
            add(c);
        };


        //Cracion de combos
        JComboBox<Odontologo> odontologoCombo = new JComboBox<Odontologo>();
        JComboBox<Paciente> pacienteCombo = new JComboBox<Paciente>();
        JComboBox<String> mesCombo = new JComboBox<String>();
        JComboBox<String> diaCombo = new JComboBox<String>();
        JComboBox<String> horaCombo = new JComboBox<String>();


        //Llenado con odontologos
        OdontologoService odontologoService = new OdontologoService();
        ArrayList<Odontologo> odontologos = odontologoService.listarOdontologo();
        for(Odontologo odo : odontologos) {
            odontologoCombo.addItem(odo);
        }

        //Llenado con Pacientes
        PacienteService pacienteService = new PacienteService();
        ArrayList<Paciente> pacientes = pacienteService.listarPaciente();
        for (Paciente p : pacientes){
            pacienteCombo.addItem(p);
        }

        //Que aparezcan Vacios
        odontologoCombo.setSelectedItem(null);
        pacienteCombo.setSelectedItem(null);
        diaCombo.setSelectedItem(null);
        mesCombo.setSelectedItem(null);
        horaCombo.setSelectedItem(null);


        //Que se muestren los demas combos de dia, mes y hora cuando se seleccione el doctor
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
        //que muestre los dias cuando selecciones el mes
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
        //que muestre la hora si se selccina el dia
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
                        if (turno.getidOdontologo() == ((Odontologo) odontologoCombo.getSelectedItem()).getId()) {
                            if (turno.getMes() == panelManager.meses.indexOf((String) mesCombo.getSelectedItem()) + 1) {
                                if (turno.getDia() == Integer.parseInt((String) diaCombo.getSelectedItem())) {
                                    System.out.println(turno.getHora());
                                    System.out.println(turno.getidPaciente());
                                    horarios.remove(turno.getHora());

                                    System.out.println(Arrays.toString(horarios.toArray()));
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
        add(pacienteCombo);
        add(mesCombo);
        add(diaCombo);
        add(horaCombo);


        int result = JOptionPane.showOptionDialog(null, this, "Agregar",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,  new String[]{"Añadir turno", "Cancelar"},
                "default");
        if (result == JOptionPane.OK_OPTION){
            //creacion de turno a raiz de datos proporcionados

            Turno nuevoTurno = new Turno(((Odontologo) odontologoCombo.getSelectedItem()).getId(),
                    ((Paciente) pacienteCombo.getSelectedItem()).getId(), Long.parseLong((String) horaCombo.getSelectedItem()), Long.parseLong(( String) diaCombo.getSelectedItem()),
                    (long) panelManager.meses.indexOf((String) mesCombo.getSelectedItem()) +1);
            //creacion del service
            TurnoService turnoService = new TurnoService();
            turnoService.guardar(nuevoTurno);

            //actualizo tabla
            panelManager.mostrarAdministradorTurnos();
        }

    }
}
