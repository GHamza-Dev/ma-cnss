package service;

import db.DBService;
import org.macnss.Medication;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicationService extends DBService {

    public static Medication searchMedication(String input){
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement("SELECT * FROM medication where name = ? OR bar_code = ?");
            statement.setString(1,input);
            statement.setString(2,input);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Medication(rs.getInt("id"),rs.getString("bar_code"),rs.getString("name"),rs.getFloat("repayment"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

        return null;
    }
}
