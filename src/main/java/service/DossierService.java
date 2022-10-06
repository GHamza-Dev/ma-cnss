package service;

import db.DBService;
import org.macnss.Dossier;
import org.macnss.Patient;
import org.macnss.Radio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DossierService extends DBService {

    public static boolean setDossierStatus(int dossierId,String status){
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement("UPDATE `dossier` SET `status` = ? WHERE `dossier`.`id` = ?");
            statement.setString(1,status);
            statement.setInt(2,dossierId);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    public static ArrayList<Dossier> selectDossiers(String status){
        ArrayList<Dossier> dossiers = new ArrayList<>();
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement("SELECT dossier.id as dossier_id, dossier.status, dossier.repayment, patient.* FROM dossier JOIN patient ON dossier.patient_id = patient.id where status = ?");
            statement.setString(1,status);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Dossier dossier = new Dossier();
                dossier.setRepayment(rs.getFloat("repayment"));
                dossier.setStatus(rs.getString("status"));
                dossier.setId(rs.getInt("dossier_id"));
                dossier.setPatient(new Patient(rs.getInt("id"),rs.getString("username"),rs.getString("email"),"####",rs.getInt("mat")));
                dossiers.add(dossier);
            }
            return dossiers;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
    public static int insertDossier(Dossier dossier){
        int patient_id = dossier.getPatient().getId();
        int speciality_id = dossier.getSpeciality().getId();
        float repayment = dossier.getRepayment();

        ArrayList<Integer> medIds = dossier.getMedicationIds();
        ArrayList<Integer> radIds = dossier.getRadioIds();
        ArrayList<Integer> anaIds = dossier.getAnalysisIds();

        int lastInsertedDossierId = 0;

        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement("INSERT INTO `dossier` (`id`, `patient_id`, `status`, `repayment`) VALUES (NULL, ?, 'waiting',?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,patient_id);
            statement.setFloat(2,repayment);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                lastInsertedDossierId = resultSet.getInt(1);
                if (medIds != null) attacheMedications(lastInsertedDossierId,medIds);
                if (radIds != null) attacheRadios(lastInsertedDossierId,radIds);
                if (anaIds != null) attacheAnalysis(lastInsertedDossierId,anaIds);
                attacheSpeciality(lastInsertedDossierId,speciality_id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return 0;
        }
        return lastInsertedDossierId;
    }

    public static boolean attacheSpeciality(int dossier_id,int specialityId){
        String sql = "INSERT INTO `dossier_specialty` (`id`, `dossier_id`, `specialty_id`) VALUES (NULL, ?, ?)";
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
            statement.setInt(1,dossier_id);
            statement.setInt(2,specialityId);
            if (statement.executeUpdate() > 0) {
                System.out.println("[SUCCESS] : Doctor inserted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    public static boolean attacheAnalysis(int dossier_id,ArrayList<Integer> analysisIds){
        String sql = "INSERT INTO `dossier_analysis` (`id`, `dossier_id`, `analysis_id`) VALUES";
        int parameterIndex1 = 1;
        int parameterIndex2 = 2;
        for (int i = 0; i < analysisIds.size(); i++) {
            sql+=" (NULL, ?, ?),";
        }
        sql = sql.substring(0,sql.length()-1);
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
            for (int i = 0; i < analysisIds.size(); i++) {
                statement.setInt(parameterIndex1,dossier_id);
                statement.setInt(parameterIndex2,analysisIds.get(i));
                parameterIndex1+=2;
                parameterIndex2+=2;
            }
            if (statement.executeUpdate() > 0) {
                System.out.println("[SUCCESS] : Analysis inserted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return false;
    }
    public static boolean attacheRadios(int dossier_id,ArrayList<Integer> radioIds){
        String sql = "INSERT INTO `dossier_radio` (`id`, `dossier_id`, `radio_id`) VALUES";
        int parameterIndex1 = 1;
        int parameterIndex2 = 2;
        for (int i = 0; i < radioIds.size(); i++) {
            sql+=" (NULL, ?, ?),";
        }
        sql = sql.substring(0,sql.length()-1);
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
            for (int i = 0; i < radioIds.size(); i++) {
                statement.setInt(parameterIndex1,dossier_id);
                statement.setInt(parameterIndex2,radioIds.get(i));
                parameterIndex1+=2;
                parameterIndex2+=2;
            }
            if (statement.executeUpdate() > 0) {
                System.out.println("[SUCCESS] : Radios inserted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return false;
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
