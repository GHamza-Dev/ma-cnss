package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AgentService extends DBService {

    public static boolean insertAgent(String username,String email,String password){
        String sql = "INSERT INTO `agent` (`id`, `username`, `email`, `password`) VALUES (NULL, ?, ?, ?)";
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,email);
            statement.setString(3,password);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
