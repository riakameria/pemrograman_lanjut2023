package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;

import ID.*;

public class Register extends JFrame {


    private JPanel panel1;
    private JTextField userName;
    private JTextField password;
    private JButton Kasir;
    private JButton Customer;
    private UserKasir kasir;
    private UserCustomer customer;


    public Register(){
        JFrame fremeRegister = new JFrame();
        fremeRegister.setTitle("FROM LOGIN");
        fremeRegister.add(panel1);
        fremeRegister.setSize(250,250);
        Kasir.setBackground(Color.PINK);
        Kasir.setForeground(Color.BLACK);
        Customer.setForeground(Color.BLACK);
        Customer.setBackground(Color.MAGENTA);
        fremeRegister.setLocationRelativeTo(null);
        fremeRegister.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        fremeRegister.pack();
        fremeRegister.setVisible(true);



        // button kasir
        Kasir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String Username = userName.getText();
                String Password = password.getText();
                UserKasir penggunaKasir = new UserKasir();
                penggunaKasir.setUserName(Username);
                penggunaKasir.setPassword(Password);
                String URL = "jdbc:mysql://localhost:3306/khususkasir";
                String USER = "root";
                String PASSWORD = "";
                String query = "SELECT * FROM kasir";
                try{
                    Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);

                    while(resultSet.next()){
                        String data = resultSet.getString("nama");
                        String data2 = resultSet.getString("password");
                        if (Username.contains(data) && Password.contains(data2)){
                             KasirGui kasirGui = new KasirGui();
                            clearForm();
                        }else{
                            formatGagal formatGagal = new formatGagal();
                        }
                    }
                }catch (Exception f){
                    System.err.println("error" + f);
                }
                clearForm();
            }
        });

        // button customer
        Customer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerGui customerGui = new CustomerGui();
                clearForm();
            }
        });
    }
        public static void insertIdCustomer(UserCustomer userCustomer, String namaP, LocalDate local, String totalHarga){
        String nama = userCustomer.getUserName();
        //String sql = "insert into customer values (" + "'" + nama + "'," +"'" +  password +"')";
        String Query_String = "INSERT INTO customer(nama,namamakanan,total,tanggal) VALUES ('" + nama + "'," + "'" + namaP + "'," + "'" + totalHarga + "'," + "'" + local + "')";
        executeSql2(Query_String);
        }
        public static void insertIdKasir(UserKasir kasir){
            String nama = kasir.getUserName();
            String password = kasir.getPassword();
            String sql = "insert into kasir values (" + "'" + nama + "'," +"'" +  password +"')";

            executeSql(sql);
        }
    public static void executeSql(String sql){
        String URL = "jdbc:mysql://localhost:3306/userkasir";
        String USER = "root";
        String PASSWORD = "";
        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void executeSql2(String sql){
        String URL = "jdbc:mysql://localhost:3306/usercustomer";
        String USER = "root";
        String PASSWORD = "";
        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void clearForm(){
        userName.setText("");
        password.setText("");

    }

}
