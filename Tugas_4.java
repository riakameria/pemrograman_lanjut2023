package com.ria.gui;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame {
    private JPanel panelMain;
    private JList<String> jListMahasiswa;
    private JButton buttonFilter;
    private JTextField textFieldFilter;
    private JTextField textFieldNama;
    private JTextField textFieldNim;
    private JTextField textFieldIpk;
    private JButton buttonSave;
    private JButton buttonDelete;
    private JButton buttonClear;
    private List<Mahasiswa> arrayListMahasiswa = new ArrayList<>();
    private DefaultListModel<String> defaultListModel = new DefaultListModel<>();

    class Mahasiswa {
        private String nim;
        private String nama;
        private float ipk;

        public String getNim() {
            return nim;
        }

        public void setNim(String nim) {
            this.nim = nim;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public float getIpk() {
            return ipk;
        }

        public void setIpk(float ipk) {
            this.ipk = ipk;
        }
    }

    public MainScreen() {
        super("Data Mahasiswa");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        jListMahasiswa.setModel(defaultListModel);

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nim = textFieldNim.getText();
                    String nama = textFieldNama.getText();
                    float ipk = Float.parseFloat(textFieldIpk.getText());

                    Mahasiswa mahasiswa = new Mahasiswa();
                    mahasiswa.setIpk(ipk);
                    mahasiswa.setNama(nama);
                    mahasiswa.setNim(nim);

                    arrayListMahasiswa.add(mahasiswa);
                    clearForm();

                    fromListMahasiswaToListModel();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainScreen.this, "IPK harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jListMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int index = jListMahasiswa.getSelectedIndex();

                if (index < 0)
                    return;
                String nama = jListMahasiswa.getSelectedValue();

                for (Mahasiswa mahasiswa : arrayListMahasiswa) {
                    if (mahasiswa.getNama().equals(nama)) {
                        textFieldIpk.setText(String.valueOf(mahasiswa.getIpk()));
                        textFieldNama.setText(mahasiswa.getNama());
                        textFieldNim.setText(mahasiswa.getNim());
                        break;
                    }
                }
            }
        });

        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jListMahasiswa.getSelectedIndex();

                if (index < 0)
                    return;

                String nama = jListMahasiswa.getSelectedValue();

                arrayListMahasiswa.removeIf(mahasiswa -> mahasiswa.getNama().equals(nama));
                clearForm();
                fromListMahasiswaToListModel();
            }
        });

        buttonFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyWord = textFieldFilter.getText();
                List<String> filtered = new ArrayList<>();

                for (Mahasiswa mahasiswa : arrayListMahasiswa) {
                    if (mahasiswa.getNama().contains(keyWord)) {
                        filtered.add(mahasiswa.getNama());
                    }
                }

                refreshListModel(filtered);
            }
        });
    }

    private void fromListMahasiswaToListModel() {
        List<String> listNamaMahasiswa = new ArrayList<>();

        for (Mahasiswa mahasiswa : arrayListMahasiswa) {
            listNamaMahasiswa.add(mahasiswa.getNama());
        }

        refreshListModel(listNamaMahasiswa);
    }

    private void clearForm() {
        textFieldIpk.setText("");
        textFieldNim.setText("");
        textFieldNama.setText("");
    }

    private void refreshListModel(List<String> list) {
        defaultListModel.clear();
        for (String item : list) {
            defaultListModel.addElement(item);
        }
    }

    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);
    }
}