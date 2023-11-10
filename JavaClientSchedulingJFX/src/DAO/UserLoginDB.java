package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserLoginDB {

    /** This method gets the user based on the User ID. */
    public static boolean getActiveUser(String username, String password) {
        try {
            String sql = "SELECT User_Name, Password from users";
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("User_Name").equals(username) && resultSet.getString("Password").equals(password))
                    return true;
            }
            return false;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
