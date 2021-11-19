package mvc;

import negocio.Odontologo;
import negocio.Paciente;
import negocio.Turno;
import service.OdontologoService;
import service.PacienteService;
import service.TurnoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class PanelAdministradorTurnoEditar extends JPanel {
    private PanelManager panelManager;
    private boolean cargando = false;

    public PanelAdministradorTurnoEditar (PanelManager panelManager){
        super(new GridLayout(2, 6));
        this.panelManager= panelManager;
        this.setBackground(Colores.COLOR_CUATRO);
    }

    public void armarPanelAdministradorTurnoEditar(JTable tabla){

        int seleccion = tabla.getSelectedRow();

        String[] labels = {
                "ID",
                "Odontólogo",
                "Paciente",
                "Mes",
                "Día",
                "Hora"
                };

        for (String f : labels) {
            JLabel c = new JLabel(f);
            c.setFont(Fuentes.FUENTE_SEIS);
            add(c);
        };

        long idTurno = (long) tabla.getValueAt(seleccion, 0);

        JTextField idField = new JTextField();
        idField.setText(Long.toString(idTurno));
        idField.setEditable(false);


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



        //Meses


        //Llenando con meses
        for (int i = 1; i <= 12; i++) {
            mesCombo.addItem(panelManager.meses.get(i-1));
        }

        //Llenando con dias
        for (int i = 1; i <= panelManager.diasMes[panelManager.meses.indexOf(mesCombo.getSelectedItem())]; i++) {
            diaCombo.addItem(Integer.toString(i));
        }


        //Llenando con horas
        for (int i = 8; i <= 18; i++) {
            horaCombo.addItem(Integer.toString(i));;
        }


        //Que aparezcan los combos Con lo que ya estaba
        TurnoService turnoService = new TurnoService();
        Turno turnoActual = turnoService.recuperarTurno(idTurno);

        System.out.println(turnoActual);

        int indexOdo=-1;
        for(Odontologo odo : odontologos) {
            if(odo.getId()== turnoActual.getidOdontologo())
                indexOdo = odontologos.indexOf(odo);
        }

        int indexPac=-1;
        for(Paciente pac : pacientes){
            if(pac.getId()==turnoActual.getidPaciente())
                indexPac=pacientes.indexOf(pac);
        }


        odontologoCombo.setSelectedIndex(indexOdo);
        pacienteCombo.setSelectedIndex(indexPac);
        diaCombo.setSelectedIndex((int) turnoActual.getDia()-1);
        mesCombo.setSelectedIndex((int) turnoActual.getMes()-1);
        horaCombo.setSelectedIndex((int) turnoActual.getHora()-8);

        odontologoCombo.setSelectedItem(odontologoService.recuperarOdontologo(turnoActual.getidPaciente()));


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

        add(idField);
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

            Turno nuevoTurno = new Turno(idTurno, ((Odontologo) odontologoCombo.getSelectedItem()).getId(),
                    ((Paciente) pacienteCombo.getSelectedItem()).getId(), Long.parseLong((String) horaCombo.getSelectedItem()), Long.parseLong(( String) diaCombo.getSelectedItem()),
                    (long) panelManager.meses.indexOf((String) mesCombo.getSelectedItem()) +1);

            //guardado
            turnoService.guardar(nuevoTurno);

            //actualizo tabla
            panelManager.mostrarAdministradorTurnos();
        }
    }
}
