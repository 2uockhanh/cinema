/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.LocalDate;
/**
 *
 * @author HP
 */
public class SignUpInterface {
    public boolean isValidDate(String day, String month, String year) {
        int day_number = Integer.parseInt(day);
        int month_number = Integer.parseInt(month);
        int year_number = Integer.parseInt(year);
        switch(month_number) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                if (day_number >= 1 && day_number <= 31) {
                    return true;
                }
                break;
            case 4: case 6: case 9: case 11:
                if (day_number >= 1 && day_number <= 30) {
                    return true;
                }
                break;
            case 2:
                if ((year_number % 400 == 0) || (year_number % 4 == 0 && year_number % 100 != 0)) {
                    if (day_number >= 1 && day_number <= 29) {
                        return true;
                    }
                } else {
                    if (day_number >= 1 && day_number <= 28) {
                        return true;
                    }
                }
                break;
        }
        if (year_number < LocalDate.now().getYear()) {
            return true;
        } else if (year_number == LocalDate.now().getYear()) {
            if (month_number < LocalDate.now().getMonthValue()) {
                return true;
            } else if (month_number == LocalDate.now().getMonthValue()) {
                if (day_number <= LocalDate.now().getDayOfMonth()) {
                    return true;
                }
            }
        }
        return false;
    }
    public SignUpInterface(Connection conn) { //throws SQLException
        JFrame fr = new JFrame ("Cinema Sign Up");
        
        JLabel mainLabel = new JLabel("SIGN UP", JLabel.CENTER);
        mainLabel.setBounds(100, 30, 200, 20);
        
        JLabel l1 = new JLabel("Full Name");
        l1.setBounds(40, 60, 80, 20);
        
        TextField fullname = new TextField();
        fullname.setBounds(140, 60, 180, 20);
        
        JLabel l2 = new JLabel("Email");
        l2.setBounds(40, 100, 80, 20);
        
        TextField email = new TextField();
        email.setBounds(140, 100, 180, 20);
        
        JLabel l3 = new JLabel("Phone");
        l3.setBounds(40, 140, 80, 20);
        
        TextField phone = new TextField();
        phone.setBounds(140, 140, 180, 20);
        
        JLabel l4 = new JLabel("D.o.B");
        l4.setBounds(40, 180, 80, 20);
        
        JComboBox day = new JComboBox();
        day.addItem("Day");
        day.setBounds(140, 180, 60, 20);
        for (int d = 1; d <= 31; d++) {
            day.addItem(Integer.toString(d));
        }
        
        JComboBox month = new JComboBox();
        month.setBounds(210, 180, 80, 20);
        month.addItem("Month");
        for (int m = 1; m <= 12; m++) {
            month.addItem(Integer.toString(m));
        }
        
        JComboBox year = new JComboBox();
        year.setBounds(300, 180, 60, 20);
        year.addItem("Year");
        for (int y = 2025; y >= 1925; y--) {
            year.addItem(Integer.toString(y));
        }
        
        JLabel l5 = new JLabel("Gender");
        l5.setBounds(40, 220, 80, 20);
        
        JComboBox gender = new JComboBox();
        gender.setBounds(140, 220, 80, 20);
        gender.addItem(null);
        gender.addItem("Male");
        gender.addItem("Female");
        
        JLabel l6 = new JLabel("Password");
        l6.setBounds(40, 260, 80, 20);
        
        TextField password = new TextField();
        password.setBounds(140, 260, 180, 20);
        
        JLabel l7 = new JLabel("City");
        l7.setBounds(40, 300, 80, 20);
        
        JComboBox city = new JComboBox();
        city.setBounds(140, 300, 120, 20);
        city.addItem(null);
        
        try{
            PreparedStatement st = conn.prepareStatement("SELECT * FROM City");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                city.addItem(rs.getString("city_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        Button signUp = new Button("Sign Up");
        signUp.setBounds(170, 350, 60, 30);
        
        signUp.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent ae) {
                String getFullname = (String) fullname.getText();
                String getEmail = (String) email.getText();
                String getPhone = (String) phone.getText();
                
                String getDay = (String) day.getSelectedItem();
                
                String getMonth = (String) month.getSelectedItem();
                String getYear = (String) year.getSelectedItem();
                String getGender = (String) gender.getSelectedItem();
                String getPassword = (String) password.getText();
                String getCity = (String) city.getSelectedItem();
                if (getFullname.equals(null) || getEmail.equals(null) ||
                    phone.equals(null) || getDay.equals("Day") || getMonth.equals("Month") ||
                    getYear.equals("Year") || getGender.equals(null) ||
                    getPassword.equals(null) || getCity.equals(null)) {
                    System.out.println("You must finished every item.");
                    return;
                } else {
                    if (getPhone.length() >= 11) {
                        System.out.println("Invalid Phone Number");
                        return;
                    } else {
                        for (int i = 0; i < getPhone.length(); i++) {
                            if (getPhone.charAt(i) < '0' || getPhone.charAt(i) > '9') {
                                System.out.println("Invalid Phone Number");
                                return;
                            }
                        }
                    }
                    if (!isValidDate(getDay, getMonth, getYear)) {
                        System.out.println("Invalid Date.");
                        return;
                    }
                }
                try {
                    PreparedStatement st = conn.prepareStatement("SELECT * FROM Users");
                    ResultSet rs = st.executeQuery();
                    while (rs.next()) {
                        if (rs.getString("email").equals(getEmail) 
                            || rs.getString("phone").equals(getPhone)) {
                            System.out.println("This email or phone number has been used.");
                            return;
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                int count = 0;
                try{
                    PreparedStatement st = conn.prepareStatement("SELECT * FROM Users");
                    ResultSet rs = st.executeQuery();
                    while (rs.next()) {
                        count++;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                String user_id = Integer.toString(count + 1);
                while (user_id.length() < 8) {
                    user_id = "0" + user_id;
                }
                if (Integer.parseInt(getDay) < 10) {
                    getDay = "0" + getDay;
                }
                if (Integer.parseInt(getMonth) < 10) {
                    getMonth = "0" + getMonth;
                }
                String dob = getYear + "-" + getMonth + "-" + getDay;
                try {
                    CallableStatement cs = 
                            conn.prepareCall("{call add_new_users(?,?,?,?,?,?,?,?)}");
                    cs.setString(1, user_id);
                    cs.setString(2, getFullname);
                    cs.setString(3, getEmail);
                    cs.setString(4, getPhone);
                    cs.setString(5, dob);
                    cs.setString(6, getGender);
                    cs.setString(7, getPassword);
                    cs.setString(8, getCity);
                    cs.executeQuery();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                fr.dispose();
                new HomeMenu(user_id);
            }
        });
        
        fr.add(mainLabel);
        fr.add(l1);
        fr.add(fullname);
        fr.add(l2);
        fr.add(email);
        fr.add(l3);
        fr.add(phone);
        fr.add(l4);
        fr.add(day);
        fr.add(month);
        fr.add(year);
        fr.add(l5);
        fr.add(gender);
        fr.add(l6);
        fr.add(password);
        fr.add(l7);
        fr.add(city);
        fr.add(signUp);
        
        fr.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                fr.dispose();
                
            }
        });
        
        fr.setSize(400, 440);
        fr.setLayout(null);
        fr.setVisible(true);
        
    }
}
