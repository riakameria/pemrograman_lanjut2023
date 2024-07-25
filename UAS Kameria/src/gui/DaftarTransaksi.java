package gui;

import ID.Makanan;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DaftarTransaksi {
    public static final String URL = "jdbc:mysql://localhost:3306/khususcustomer";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    private JPanel panel1;
    private JTable tableTransaksi;
    private JButton tutup;
    private String selectedNama = "";
    private DefaultTableModel defaultTableModel = new DefaultTableModel();

    public DaftarTransaksi() {
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Daftar Transaksi");
        jFrame.setLocationRelativeTo(null);
        tutup.setBackground(Color.RED);
        tutup.setForeground(Color.BLACK);
        jFrame.setSize(400, 400);
        jFrame.add(panel1);
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jFrame.setVisible(true);
        TampilkanTable(
                getMakanan()
        );

        tutup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        this.tableTransaksi.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        int row = tableTransaksi.getSelectedRow();
                        if (row < 0) {
                            return;
                        }

                        String nama = tableTransaksi.getValueAt(row, 0).toString();
                        if (selectedNama != null && selectedNama.equals(nama)) {
                            return;
                        }

                        selectedNama = nama;
                        String namaMakanan = tableTransaksi.getValueAt(row, 1).toString();
                        String harga = tableTransaksi.getValueAt(row, 2).toString();
                        String total = tableTransaksi.getValueAt(row, 3).toString();
                        System.out.println(row);


                        // Pastikan ada fieldDeskripsi
                    }
                }
        );


    }
    public static ArrayList<Makanan> getMakanan() {
        ArrayList<Makanan> makananArrayList = new ArrayList<>();

        try {
            ResultSet resultSet = executeQuery("SELECT * FROM customer");
            while (resultSet.next()) {
                String nama = resultSet.getString("nama");
                System.out.println(nama);
                String namamakanan = resultSet.getString("namamakanan");
                System.out.println(namamakanan);
                String totalHarga = resultSet.getString("total");
                String date = resultSet.getString("tanggal");
                Makanan makanan1 = new Makanan();
                makanan1.setNama(nama);
                makanan1.setNamaMakanan(namamakanan);
                makanan1.setHargaMakanan(totalHarga);
                makanan1.setLocalDate(date);
                makananArrayList.add(makanan1);
            }
        } catch (Exception c) {
            return null;
        }
        return makananArrayList;
    }

    public void TampilkanTable(ArrayList<Makanan> mkn) {
        Object[][] data = new Object[mkn.size()][2];
        for (int i = 0; i < mkn.size(); i++) {
            data[i] = new Object[]{
                    mkn.get(i).getNama(),
                    mkn.get(i).getNamaMakanan(),
                    mkn.get(i).getHargaMakanan(),
                    mkn.get(i).getLocalDate()
            };
        }
        defaultTableModel = new DefaultTableModel(
                data,
                new String[]{"NAMA", "NAMA MAKANAN","TOTAL","TANGGAL"}
        );
        tableTransaksi.setModel(defaultTableModel);
    }
    public static ResultSet executeQuery(String query) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (Exception e){
            return null;
        }
    }
}
