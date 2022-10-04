package db;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    Dotenv dotenv = Dotenv.load();

    private final String URL = dotenv.get("DB_URL");
    private final String USER = dotenv.get("DB_USER");
    private final String PASS = dotenv.get("DB_PASS");

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
