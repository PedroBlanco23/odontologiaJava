package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelLogin extends JPanel {
    private PanelManager panelManager;
    private JLabel userLbl;
    private JTextField userTxt;
    private JLabel passwordLbl;
    private JPasswordField passwordTxt;
    private JPanel panelUsuario;
    private JPanel panelContrasena;
    private JPanel panelCampos;
    private JPanel botonera;
    private JButton btnIniciarSesion;




    public PanelLogin(PanelManager panelManager) {
        this.panelManager = panelManager;
    }

    public void armarPanelLogin() {
        setLayout(new BorderLayout());
        setBackground(new Color(200, 221, 242));
        userLbl = new JLabel("Usuario: ");
        userTxt = new JTextField();
        passwordLbl = new JLabel("Contraseña: ");
        passwordTxt = new JPasswordField();

        panelUsuario = new JPanel(new GridLayout());
        panelUsuario.setBackground(new Color(200, 221, 242));
        panelContrasena = new JPanel(new GridLayout());
        panelContrasena.setBackground(new Color(200, 221, 242));
        panelCampos = new JPanel(new BorderLayout());
        panelCampos.setBackground(new Color(200, 221, 242));

        panelUsuario.add(userLbl);
        panelUsuario.add(userTxt);
        panelContrasena.add(passwordLbl);
        panelContrasena.add(passwordTxt);

        panelCampos.add(panelUsuario, BorderLayout.CENTER);
        panelCampos.add(panelContrasena, BorderLayout.AFTER_LAST_LINE);


        botonera = new JPanel();
        botonera.setBackground(new Color(200, 221, 242));
        btnIniciarSesion = new JButton("Iniciar sesión");
        botonera.add(btnIniciarSesion);

        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    panelManager.iniciarSesion(userTxt.getText(), passwordTxt.getText());
            }
        });


        add(panelCampos, BorderLayout.NORTH);
        add(botonera, BorderLayout.AFTER_LINE_ENDS);







    }

}
