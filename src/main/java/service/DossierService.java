package service;

import db.DBService;
import org.macnss.Dossier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DossierService extends DBService {

    public static int insertDossier(Dossier dossier){
        int patient_id = dossier.getPatient().getId();
        ArrayList<Integer> medIds = dossier.getMedicationIds();
        int lastInsertedDossierId = 0;
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement("INSERT INTO `dossier` (`id`, `patient_id`, `status`, `repayment`) VALUES (NULL, ?, 'waiting', '00.00')", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,patient_id);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                lastInsertedDossierId = resultSet.getInt(1);
                attacheMedications(lastInsertedDossierId,medIds);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return 0;
        }
        return lastInsertedDossierId;
    }

    public static boolean attacheMedications(int dossier_id,ArrayList<Integer> medicationIds){
        String sql = "INSERT INTO `dossier_medication` (`id`, `dossier_id`, `midication_id`) VALUES";
        int parameterIndex1 = 1;
        int parameterIndex2 = 2;
        for (int i = 0; i < medicationIds.size(); i++) {
            sql+=" (NULL, ?, ?),";
        }
        sql = sql.substring(0,sql.length()-1);
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
            for (int i = 0; i < medicationIds.size(); i++) {
                statement.setInt(parameterIndex1,dossier_id);
                statement.setInt(parameterIndex2,medicationIds.get(i));
                parameterIndex1+=2;
                parameterIndex2+=2;
            }
            if (statement.executeUpdate() > 0) {
                System.out.println("[SUCCESS] : Medications inserted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return false;
    }
}