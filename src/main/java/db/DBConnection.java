package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {
    private final static String URL = "jdbc:mysql://localhost:3306/ma-cnss";
    private final static String USER = "root";
    private final static String PASS = "";

    private static Connection con;
    private static Statement stmt;

    private static DBConnection instance;

    private DBConnection() {
        setConnection();
        setStatement();
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    private static void setConnection() {
        try {
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void setStatement() {
        try {
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
