package db;

import org.macnss.Admin;
import org.macnss.Agent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AgentService extends DBService{

    public static Agent login(String email, String password){
        try {
            ResultSet resultSet = getStatement().executeQuery( "SELECT * FROM agent WHERE email = '"+email+"' AND password = '"+password+"'");
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String userEmail = resultSet.getString("email");
                return new Agent(id,username,userEmail,password);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
