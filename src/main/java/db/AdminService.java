package db;

import org.macnss.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminService extends DBService{

    public static Admin login(String email,String password){
        try {
            ResultSet resultSet = getStatement().executeQuery( "SELECT * FROM admin WHERE email = '"+email+"' AND password = '"+password+"'");
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String userEmail = resultSet.getString("email");
                return new Admin(id,username,userEmail,password);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
