package mvc;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        setBackground(Colores.COLOR_TRES);
        userLbl = new JLabel("Usuario: ");
        userLbl.setFont(Fuentes.FUENTE_UNO);
        userLbl.setForeground(Color.white);
        userTxt = new JTextField();
        userTxt.setFont(Fuentes.FUENTE_UNO);
        passwordLbl = new JLabel("Contraseña: ");
        passwordLbl.setForeground(Color.white);
        passwordLbl.setFont(Fuentes.FUENTE_UNO);
        passwordTxt = new JPasswordField();
        passwordTxt.setFont(Fuentes.FUENTE_UNO);

        panelUsuario = new JPanel(new GridLayout());
        panelUsuario.setBackground(Colores.COLOR_TRES);
        panelUsuario.setBorder(new EmptyBorder(0,0,10,0));
        panelContrasena = new JPanel(new GridLayout());
        panelContrasena.setBackground(Colores.COLOR_TRES);
        panelCampos = new JPanel(new BorderLayout());
        panelCampos.setBorder(new EmptyBorder(10,10,10,10));
        panelCampos.setBackground(Colores.COLOR_TRES);

        panelUsuario.add(userLbl);
        panelUsuario.add(userTxt);
        panelContrasena.add(passwordLbl);
        panelContrasena.add(passwordTxt);
        passwordTxt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.iniciarSesion(userTxt.getText(), passwordTxt.getText());
            }
        });

        panelCampos.add(panelUsuario, BorderLayout.CENTER);
        panelCampos.add(panelContrasena, BorderLayout.AFTER_LAST_LINE);


        botonera = new JPanel();
        botonera.setBackground(Colores.COLOR_TRES);
        btnIniciarSesion = new JButton("Iniciar sesión");
        btnIniciarSesion.setFont(Fuentes.FUENTE_UNO);
        botonera.add(btnIniciarSesion);

        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    panelManager.iniciarSesion(userTxt.getText(), passwordTxt.getText());
            }
        });


        add(panelCampos, BorderLayout.NORTH);
        add(botonera, BorderLayout.SOUTH);







    }

}
