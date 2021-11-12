package mvc;

import javax.swing.*;
import java.awt.*;

public class PanelAdministrador extends JPanel {
    private PanelManager panelManager;
    private JFrame jframe;
    private JLabel adminlbl;
    private JButton btnTurnos;
    private JButton btnPacientes;
    private JButton btnOdontologos;
    private JButton btnUsuarios;
    public void PanelAdministrador(PanelManager panel){
        this.panelManager = panel;
    }

    public void armarPanelAdmin(){
        setLayout(new BorderLayout());
        setBackground(new Color(200,152,92));
        adminlbl = new JLabel("Admin User: ");


    }




}
