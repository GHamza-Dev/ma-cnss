package service;

import db.DBService;
import org.macnss.Analysis;
import org.macnss.Radio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RadioService extends DBService {
    public static Radio searchRadio(String name){
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement("SELECT * FROM radio where name = ?");
            statement.setString(1,name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Radio(rs.getInt("id"),rs.getString("name"),rs.getFloat("percentage"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

        return null;
    }
}
