package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KasirGui  extends JFrame{
    private JPanel panel1;
    private JButton ButtonTransaksi;
    private JButton ButtonMenu;

    public KasirGui(){
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Menu Kasir");
        jFrame.add(panel1);
        jFrame.setSize(350,350);
        ButtonMenu.setBackground(Color.PINK);
        ButtonTransaksi.setBackground(Color.magenta);
        jFrame.setLocationRelativeTo(null);
        ButtonMenu.setPreferredSize(new Dimension(30,30));
        ButtonTransaksi.setPreferredSize(new Dimension(30,30));
        jFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jFrame.setVisible(true);


        ButtonMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageMenu manageMenu = new ManageMenu();
            }
        });
        ButtonTransaksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DaftarTransaksi daftarTransaksi = new DaftarTransaksi();
            }
        });
    }
}
