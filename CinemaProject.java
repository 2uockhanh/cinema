/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
/**
 *
 * @author HP
 */
public class CinemaProject {
    
    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String jdbcUrl = "jdbc:mysql://localhost:3306/cinemaproject";
        String jdbcUser = "root";
        String jdbcPassword = "";
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
    }
    
    public static void main(String arg[]) {
        new LogInInterface();
        /*
        try {
            Connection conn = createConnection();
            //new SignUpInterface(conn);
            //new HomeMenu("00000001");
            new ShowUserInfo("00000001");
            //new LogInInterface();
        } catch (SQLException e) {
            System.out.println("Cannot Connect to Database.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Cannot found Class");
            e.printStackTrace();
        }
        */
        //new HomeMenu("02306669");
        /*
        try {
        } catch (SQLException e) {
            System.out.println("Cannot Connect to Database.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Cannot found Class");
            e.printStackTrace();
        } finally {
            if (user_account != null) {
                try {
                    user_account.close();
                } catch (SQLException sqle) {}
                user_account = null;
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException sqle) {}
                ps = null;
            }
        }
        */
    }
}
