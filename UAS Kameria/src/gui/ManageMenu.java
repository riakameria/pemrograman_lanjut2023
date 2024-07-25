package gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import ID.Makanan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class ManageMenu extends JFrame {
    public static final String URL = "jdbc:mysql://localhost:3306/khususmenu";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    private JPanel panel1;
    private JButton editMenu;
    private JButton hapusMenu;
    private JButton tambahMenu;
    private JTextField fieldNama;
    private JTextField fieldHarga;

    private JTable tabelMenu;
    private Makanan makanan;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private String selectedNama = "";
    public int getRow = 0;

    public ManageMenu(int hasil){
        this.getRow = hasil;
    }
    public void setRow(int row){
        this.getRow = row;
    }
    public int getRow(){
        return this.getRow;
    }

    public ManageMenu() {
        JFrame frame = new JFrame();
        frame.setTitle("MANAGE MENU");
        editMenu.setBackground(Color.PINK);
        editMenu.setForeground(Color.BLACK);
        hapusMenu.setBackground(Color.RED);
        hapusMenu.setForeground(Color.BLACK);
        tambahMenu.setBackground(Color.MAGENTA);
        tambahMenu.setForeground(Color.BLACK);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.add(panel1);
        frame.setLocationRelativeTo(null);
        fieldNama.setPreferredSize(new Dimension(20,15));
        frame.setSize(800, 800);
        TampilkanTable(
                getMakanan()
        );
        frame.pack();
        frame.setVisible(true);

        editMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = fieldNama.getText();
                String harga = fieldHarga.getText();
                int row = getRow();
                String query = "SELECT * FROM makanann";
                int i = 0;
                try{
                    ResultSet resultSet = executeQuery(query);
                    while(resultSet.next()){
                        String dataTabel = resultSet.getString("nama");

                        if (nama.contains(dataTabel)){
                            formatGagal formatGagal = new formatGagal();
                        }else{
                            Makanan makanan1 = new Makanan();
                            makanan1.setNamaMakanan(nama);
                            makanan1.setHargaMakanan(harga);
                            editDataMakanan(makanan1,row);
                            TampilkanTable(getMakanan());
                            clearFrom();
                        }
                    }
                }catch (Exception f){
                    System.out.println("gagal");
                    System.out.println(f);
                }

            }
        });
        hapusMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = getRow();
                hapusData(index);
                TampilkanTable(getMakanan());
                clearFrom();
            }
        });
        tambahMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = fieldNama.getText();
                String harga = fieldHarga.getText();
                String query = "SELECT * FROM makanann";
                int i = 0;
                try{
                    ResultSet resultSet = executeQuery(query);
                    while(resultSet.next()){
                        String dataNama = resultSet.getString("nama");
                        int index = resultSet.getInt("id");
                        System.out.println(index);
                        if (nama.contains(dataNama)){
                            formatGagal formatGagal = new formatGagal();
                            clearFrom();
                        }
                        i++;
                        if (i < index){
                            do{
                                i++;
                            }while(i < index);
                        }
                        if (i > index){
                            Makanan makanan1 = new Makanan();
                            makanan1.setNamaMakanan(nama);
                            makanan1.setHargaMakanan(harga);
                            makanan1.setId(i);
                            insertMenu(makanan1);
                            clearFrom();
                            TampilkanTable(getMakanan());
                        }else{
                            System.out.println("kurang dari index");
                        }
                    }
                }catch (Exception f){
                    System.out.println("gagal Menambah makanan");
                }
            }
        });

        this.tabelMenu.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        int row = tabelMenu.getSelectedRow();
                        if (row < 0) {
                            return;
                        }

                        String nama = tabelMenu.getValueAt(row, 0).toString();
                        if (selectedNama != null && selectedNama.equals(nama)) {
                            return;
                        }

                        selectedNama = nama;
                        String harga = tabelMenu.getValueAt(row, 1).toString();
                        setRow(row);
                        System.out.println(row);
                        fieldNama.setText(nama);
                        fieldHarga.setText(harga);

                        // Pastikan ada fieldDeskripsi
                    }
                }
        );
    }
    public void hapusData(int row){
        String sql = "delete from makanann " +
                "where id = '"+ row +"'";
        executeSql(sql);
    }

    public void TampilkanTable(@org.jetbrains.annotations.NotNull ArrayList<Makanan> mkn) {
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
        tabelMenu.setModel(defaultTableModel);
    }

    public static void insertMenu(Makanan makanan) {
/*        String sql = "INSERT INTO makanann (nama, harga) VALUES ( '" + makanan.getNamaMakanan() + "'," + "'" + makanan.getHargaMakanan() + "')";
        executeSql(sql);*/
        String sql = "insert into makanann values (" +
                "'" +makanan.getId() + "', " +
                "'" +makanan.getNamaMakanan() + "', " +
                "'" +makanan.getHargaMakanan() + "')";
        executeSql(sql);
    }
    public static void editDataMakanan(Makanan makanan, int row) {
        String sql = "update makanann set " +
                "nama = '"+ makanan.getNamaMakanan()+"', " +
                "harga = '"+ makanan.getHargaMakanan()+"' " +
                "where id = '"+ row +"'";
        executeSql(sql);
    }
    public static void executeSql(String sql) {
        String URL = "jdbc:mysql:://localhost:3306/khususmenu";
        String USER = "root";
        String PASSWORD = "";
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("gagal melakukan execute sql");
        }
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
    public static ArrayList<Makanan> getMakanan() {
        ArrayList<Makanan> makananArrayList = new ArrayList<>();
        ResultSet resultSet = executeQuery("SELECT * FROM makanan");
        try {
            while (resultSet.next()) {
                String nama = resultSet.getString("nama");
                String harga = resultSet.getString("harga");
                Makanan makanan1 = new Makanan();
                makanan1.setNamaMakanan(nama);
                makanan1.setHargaMakanan(harga);
                makananArrayList.add(makanan1);
            }
        } catch (Exception c) {
            return null;
        }
        return makananArrayList;
    }
    private void clearFrom() {
        fieldNama.setText("");
        fieldHarga.setText("");
    }
}