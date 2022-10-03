package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBService {
    private static final DBConnection dbConnection = new DBConnection();
    protected static Statement stmt;

    public static Statement getStatement() {
        try {
            stmt = dbConnection.getConnection().createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return stmt;
    }
}
