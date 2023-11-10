package model;

import DAO.UserDB;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class User {
    private int userId;
    private String userName;
    private String password;
    private LocalDateTime createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;

    public User(int userId,String userName,String password){
        this.userId=userId;
        this.userName=userName;
        this.password=password;
    }

    public User(){}

    //getters========================================================================
    /** This will return all of the Users from the database. */
    public static ObservableList<User> getAllUsers(){ return UserDB.getAllUsers();}
    public int getUserId(){return this.userId;}
    public String getUserName(){return this.userName;}
    public String getPassword(){return this.password;}
    public LocalDateTime getCreateDate(){return this.createDate;}
    public String getCreatedBy(){return this.createdBy;}
    public Timestamp getLastUpdate(){return this.lastUpdate;}
    public String getLastUpdatedBy(){return this.lastUpdatedBy;}
    //setters========================================================================
    public void setUserId(int userId){this.userId=userId;}
    public void setUserName(String userName){this.userName=userName;}
    public void setUserPassword(String userPassword){this.password=password;}
    public void setCreateDate(LocalDateTime createDate){this.createDate=createDate;}
    public void setCreatedBy(String createdBy){this.createdBy=createdBy;}
    public void setLastUpdate(Timestamp lastUpdate){this.lastUpdate=lastUpdate;}
    public void setLastUpdatedBy(String lastUpdatedBy){this.lastUpdatedBy=lastUpdatedBy;}

    /** Override toString method for the combo box. */
    @Override
    public String toString() {
        return ("#" + Integer.toString(userId) + " " + userName);
    }
}
