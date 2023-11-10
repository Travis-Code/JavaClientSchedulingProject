package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class UserDB {
    public static ObservableList<User> getAllUsers(){
        ObservableList<User> userList = FXCollections.observableArrayList();
        String dbUsers = "SELECT * FROM users;";

        try{
            PreparedStatement ps = JDBC.getConnection().prepareStatement(dbUsers);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int userID=rs.getInt("User_ID");
                String username=rs.getString("User_Name");
                String password=rs.getString("Password");
                User user = new User(userID,username,password);
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    /** This method finds appointments based on the User ID. */
    public static ObservableList<Appointments> getAppointmentsByUser(int userID) {
        ObservableList<Appointments> userAppointmentResult = FXCollections.observableArrayList();
        for (Appointments appointments : Objects.requireNonNull(DBappointments.getAllAppointments())){
            if (appointments.getUserID() == userID){
                userAppointmentResult.add(appointments);
            }
        }
        return userAppointmentResult;
    }
}
