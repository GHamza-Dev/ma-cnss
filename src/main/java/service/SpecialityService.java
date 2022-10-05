package service;

import db.DBService;
import org.macnss.Medication;
import org.macnss.Speciality;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecialityService extends DBService {

    public static Speciality searchSpeciality(String speciality){
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement("SELECT * FROM speciality where name ='" + speciality + "' ");
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Speciality(rs.getString("name"),rs.getFloat("repayment"),rs.getInt("medication_refundable"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

        return null;
    }
}
