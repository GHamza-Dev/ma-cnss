package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private final String URL = "jdbc:mysql://localhost:3306/ma-cnss";
    private final String USER = "root";
    private final String PASS = "";

    private Connection con;

    public DBConnection() {
        try {
            this.con = DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public Connection getConnection() {
        return this.con;
    }
}
