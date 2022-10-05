package service;

import db.DBService;
import org.macnss.Analysis;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnalysisService extends DBService {

    public static Analysis searchAnalysis(String name){
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement("SELECT * FROM analysis where name = ?");
            statement.setString(1,name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Analysis(rs.getInt("id"),rs.getString("name"),rs.getFloat("percentage"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

        return null;
    }
}
