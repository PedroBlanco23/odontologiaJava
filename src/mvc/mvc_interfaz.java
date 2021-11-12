package mvc;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mvc_interfaz {
    private static  JFrame frame;
    private JTextField usuario;
    private JPasswordField contraseña;
    private JButton enter;
    private JPanel panel;
    private JLabel usuarioLabel;
    private JLabel contraseñaLabel;
    private JTextArea AreaTextuser;

    public mvc_interfaz() {
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String contenido = usuario.getText();
                String contenido2 = contraseña.getText();
                if (contenido.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Debe llenar el campo usuario!");
                } else if (contenido2.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Debe llenar el campo contraseña!");
                }
                else {
                    //TODO
                    if (contenido.equals("P") && contenido2.equals("P")){
                        frame.setVisible(false);
                    } else{
                        JOptionPane.showMessageDialog(null,"Los datos ingresados son incorrectos.");
                    }
                }

            }
        });

    }

    public static void main(String[] args) {
        frame = new JFrame("Turnos Odontología");
        frame.setContentPane(new mvc_interfaz().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

    }


}
