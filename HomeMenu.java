/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
/**
 *
 * @author HP
 */
public class HomeMenu {
    public Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String jdbcUrl = "jdbc:mysql://localhost:3306/cinemaproject";
        String jdbcUser = "root";
        String jdbcPassword = "";
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
    }
    public HomeMenu(String user_id) { //Connection conn, 
        try {
            Connection conn = createConnection();
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE user_id =?");
            ps.setString(1, user_id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String hello = "WELCOME TO THE CINEMA, " + rs.getString("full_name");
            JFrame fr = new JFrame("Home: Cinema");
            JLabel welcome = new JLabel(hello, JLabel.CENTER); //
            welcome.setBounds(0, 10, 400, 20);
            
            JButton movieLists = new JButton("MOVIE LISTS");
            movieLists.setBounds(70, 40, 240, 30);

            JButton bookTickets = new JButton("BOOK TICKETS");
            bookTickets.setBounds(70, 80, 240, 30);

            JButton myTickets = new JButton("MY TICKETS");
            myTickets.setBounds(70, 120, 240, 30);

            JButton userInfo = new JButton ("USER INFO");
            userInfo.setBounds(70, 160, 240, 30);

            JButton logOut = new JButton("LOG OUT");
            logOut.setBounds(70, 200, 240, 30);

            fr.add(welcome);
            fr.add(movieLists);
            fr.add(bookTickets);
            fr.add(myTickets);
            fr.add(userInfo);
            fr.add(logOut);
            
            logOut.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fr.dispose();
                    new LogInInterface();
                }
            });
            
            /*movieLists.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new showUserInfo();
                }
            });*/
            
            /*bookTickets.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new showUserInfo();
                }
            });*/
            
            /*myTickets.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new showUserInfo();
                }
            });*/
            
            userInfo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fr.dispose();
                    new ShowUserInfo(user_id);
                }
            });

            fr.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
            
            fr.setSize(400, 300);
            fr.setLayout(null);
            fr.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
