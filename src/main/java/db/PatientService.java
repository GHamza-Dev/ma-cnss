package db;

import org.macnss.Patient;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PatientService extends DBService{

    public static boolean insertAgent(Patient patient){
        String sql = "INSERT INTO `patient` (`id`,`mat`, `username`, `email`, `password`) VALUES (NULL,?,?,?,?)";
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
            statement.setInt(1,(int)patient.getMat());
            statement.setString(2,patient.getUsername());
            statement.setString(3,patient.getEmail());
            statement.setString(4,patient.getPassword());
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
