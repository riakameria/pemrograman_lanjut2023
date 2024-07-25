package gui;

import javax.print.attribute.standard.MediaName;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.*;
import ID.*;
import ID.Makanan;

public class CustomerGui {
    public static final String URL = "jdbc:mysql://localhost:3306/khususcustomer";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    private JPanel panel1;
    private JTable tableMenu;
    private JButton kurang;
    private JButton tambah;
    private JTextField jumblah;
    private JTextField namaCustomer;
    private JButton pesan;
    private String selectedNama = "";
    private int selectedHarga = 0;
    private int hargaDefault;

    private DefaultTableModel defaultTableModel = new DefaultTableModel();

    public CustomerGui() {
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Menu");
        jFrame.add(panel1);
        jFrame.setLocationRelativeTo(null);
        tambah.setBackground(Color.PINK);
        tambah.setForeground(Color.BLACK);
        kurang.setBackground(Color.red);
        kurang.setForeground(Color.BLACK);
        pesan.setBackground(Color.magenta);
        pesan.setForeground(Color.BLACK);
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jFrame.setSize(400, 400);
        jFrame.setVisible(true);
        TampilkanTable(
                getMakanan()
        );


        tambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int get = selectedHarga;
                int totalHarga = get + hargaDefault;
                System.out.println(totalHarga);
                selectedHarga = totalHarga;
                clearForm();
                String harga = String.valueOf(totalHarga);
                jumblah.setText(harga);
            }
        });


        kurang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("tekan kurang");
                int hasilKurang = selectedHarga - hargaDefault;
                System.out.println("kurang " + hasilKurang);
                selectedHarga = hasilKurang;
                if (hasilKurang < hargaDefault){
                    formatGagal formatGagal = new formatGagal();
                    clearForm();
                }
                clearForm();
                String hasiljasi = String.valueOf(hasilKurang);
                jumblah.setText(hasiljasi);

            }
        });

        this.tableMenu.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        int row = tableMenu.getSelectedRow();
                        if (row < 0) {
                            return;
                        }

                        String nama = tableMenu.getValueAt(row, 0).toString();
                        if (selectedNama != null && selectedNama.equals(nama)) {
                            return;
                        }

                        selectedNama = nama;
                        String harga = tableMenu.getValueAt(row, 1).toString();
                        jumblah.setText(harga);
                        int pasrsing = getNumeric(harga);
                        hargaDefault = pasrsing;
                        selectedHarga = pasrsing;
                    }
                }
        );

        pesan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String namacustomer = namaCustomer.getText();
                String namaMakanan = selectedNama;
                int panajangNama = namacustomer.length();
                String totalHarga = jumblah.getText();
                if (panajangNama == 0) {
                    formatGagal formatGagal = new formatGagal();
                    clearForm();
                } else {
                    formatBerhasil formatBerhasil = new formatBerhasil();
                }
/*                String query = "SELECT * FROM customer";
                try {
                    ResultSet resultSet = executeQuery(query);
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                    }
                } catch (Exception f) {
                    System.out.println(f);
                    System.out.println("gagal membaca database");
                }*/
                UserCustomer userCustomer = new UserCustomer();
                LocalDate date = LocalDate.now();
                userCustomer.setUserName(namacustomer);
                Register.insertIdCustomer(userCustomer,namaMakanan,date,totalHarga);
                clearForm();
                clearFrom2();
            }
        });
    }

    public static ArrayList<Makanan> getMakanan() {
        ArrayList<Makanan> makanancuy = new ArrayList<>();
        makanancuy = ManageMenu.getMakanan();
        return makanancuy;
    }

    public void TampilkanTable(ArrayList<Makanan> mkn) {
        Object[][] data = new Object[mkn.size()][2];
        for (int i = 0; i < mkn.size(); i++) {
            data[i] = new Object[]{
                    mkn.get(i).getNamaMakanan(),
                    mkn.get(i).getHargaMakanan(),
            };
        }
        defaultTableModel = new DefaultTableModel(
                data,
                new String[]{"NAMA", "HARGA"}
        );
        tableMenu.setModel(defaultTableModel);
    }

    public void clearForm() {
        jumblah.setText("");

    }
    public void clearFrom2(){
        namaCustomer.setText("");
    }

    public static ResultSet executeQuery(String query) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (Exception e) {
            return null;
        }

    }

    public static int getNumeric(String harga) {
            String originalString = harga;
            String newString = originalString.substring(0, originalString.length() - 1);
            int hasil = Integer.parseInt(newString);
            return hasil;

    }
}


