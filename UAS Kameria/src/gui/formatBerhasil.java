package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class formatBerhasil {
    private JPanel panel1;
    private JButton tutup;

    public formatBerhasil(){
        JFrame jFrame = new JFrame();
        jFrame.setLocationRelativeTo(null);
        jFrame.setSize(150,150);
        jFrame.add(panel1);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setTitle("Format Berhasil");
        tutup.setBackground(Color.GREEN);
        tutup.setForeground(Color.BLACK);
        jFrame.setVisible(true);
        tutup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
            }
        });
    }
}
