
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public class LogInInterface {
    public Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String jdbcUrl = "jdbc:mysql://localhost:3306/cinemaproject";
        String jdbcUser = "root";
        String jdbcPassword = "";
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
    }
    public LogInInterface() { //Connection conn
        try{
            Connection conn = createConnection();
            JFrame fr = new JFrame("Cinema Log In");

            JLabel mainLabel = new JLabel("LOG IN", JLabel.CENTER); //, JLabel.CENTER
            mainLabel.setBounds(100, 20, 200, 20);

            JLabel l1 = new JLabel("Email/Phone");
            l1.setBounds(40, 60, 80, 20);

            TextField userName = new TextField();
            userName.setBounds(160, 60, 180, 20);

            JLabel l2 = new JLabel("Password");
            l2.setBounds(40, 100, 80, 20);

            TextField passWord = new TextField();
            passWord.setBounds(160, 100, 180, 20);

            Button logIn = new Button("Log In");
            logIn.setBounds(80, 160, 80, 35);

            Button signUp = new Button("Sign Up");
            signUp.setBounds(220, 160, 80, 35);

            JLabel signInError = new JLabel("");
            signInError.setBounds(60, 120, 220, 20);

            fr.add(mainLabel);
            fr.add(l1);
            fr.add(userName);
            fr.add(l2);
            fr.add(passWord);
            fr.add(logIn);
            fr.add(signUp);
            fr.add(signInError);

            logIn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    try {
                        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE (email=? OR phone=?) AND password=?");
                        ps.setString(1, userName.getText());
                        ps.setString(2, userName.getText());
                        ps.setString(3, passWord.getText());
                        ResultSet user_account = ps.executeQuery();
                        while (user_account.next()) {

                            String email = user_account.getString("email");
                            String phone = user_account.getString("phone");
                            String pass = user_account.getString("password");

                            if ((email.equals(userName.getText()) || phone.equals(userName.getText()))
                    && (pass.equals(passWord.getText()))) {
                                fr.dispose();
                                new HomeMenu(user_account.getString("user_id"));
                                //new HomeMenu();
                                System.out.println("Sign In Successfully");
                            }
                        }
                        signInError.setText("Sign In Unsuccessfully.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            signUp.addActionListener(new ActionListener() {
                public void actionPerformed (ActionEvent ae) {
                    fr.dispose();
                    new SignUpInterface(conn);
                }
            });

            fr.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });

            fr.setSize(400, 250);
            fr.setLayout(null);
            fr.setVisible(true);
        } catch(SQLException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
