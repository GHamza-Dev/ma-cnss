package service;

import db.DBService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicationService extends DBService {

    public static void searchMedication(String input){
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement("SELECT * FROM medication where name = ? OR bar_code = ?");
            statement.setString(1,input);
            statement.setString(2,input);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
