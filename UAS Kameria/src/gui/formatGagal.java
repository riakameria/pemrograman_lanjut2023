package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class formatGagal {
    private JPanel panel1;
    private JButton close;
    public formatGagal(){
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.add(panel1);
        jFrame.setSize(150,150);
        jFrame.setTitle("Format gagal");
        close.setBackground(Color.RED);
        close.setForeground(Color.BLACK);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
            }
        });
    }
}

