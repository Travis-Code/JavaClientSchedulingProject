package DAO;

import model.FLDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Returns First Level Divisions from DB. */
public class DBfldivisions {
    /** return all fields in the fl divisions database. */
    public static ObservableList<FLDivisions> getAllDivisions(){
        ObservableList<FLDivisions> flDivisionslist = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * from first_level_divisions";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int divisionID = resultSet.getInt("Division_ID");
                String divisionName = resultSet.getString("Division");
                int countryID = resultSet.getInt("COUNTRY_ID");
                FLDivisions division = new FLDivisions(divisionID, divisionName,countryID);
                flDivisionslist.add(division);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flDivisionslist;
    }
}
