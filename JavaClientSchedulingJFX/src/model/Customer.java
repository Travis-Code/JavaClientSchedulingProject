package model;

import DAO.DBcustomers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Timestamp;

/** This is the class for the Customers model. */
public class Customer {
    public ObservableList<Appointments> assocAppointments = FXCollections.observableArrayList();
    private int custID;
    private String name;
    private String address;
    private String postal;
    private String phone;
    private String division;
    private String country;
    private int divisionID;
    /** The constructor for Customers. */
    public Customer(int custID, String name, String address, String postal, String phone, String division, String country) {
        this.custID = custID;
        this.name = name;
        this.address = address;
        this.postal = postal;
        this.phone = phone;
        this.division = division;
        this.country = country;
    }

//    public Customer(int custID, String custName, String address, String postal, String phone, FLDivisions division) {
//        this.custID = custID;
//        this.name = custName;
//        this.address = address;
//        this.postal = postal;
//        this.phone = phone;
//        this.division = String.valueOf(division);
//    }
//
//    public Customer(int id, String name, String address, String postal, String phone, int divisionID, String country) {
//
//    }
//
//    public Customer(int custID, String custName, String address, String postal, String phone, int divisionID) {
//    }





    /** Return all Customers from the database.
     * @return DBcustomers.getAllCustomers();
     * */
    public static ObservableList<Customer> getAllCustomers() {
        return DBcustomers.getAllCustomers();
    }


//    public static void addCust(Customer newCust) {
//    }

    /** Get Customer ID.
     * @return custID
     * */
    public int getCustID() {
        return custID;
    }
    /**
     * Set id
     * @param id
     */
    public void setId(int id) {
        this.custID = id;
    }
    /** Get Customer Name.
     * @return name
     * */
    public String getName(){ return name;}
    /**
     * @param name the name to set
     */
    public void setName(String name) {this.name = name;}
    /** Get Customer Address.
     * @return address
     * */
    public String getAddress(){
        return address;
    }
    /**
     * Set customer Address.
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /** The getter for Customer Address.
     * @return postal
     * */
    public String getPostal(){
        return postal;
    }
    /**
     * Set the postal address
     * @param postal the postal to set
     */
    public void setPostal(String postal) {
        this.postal = postal;
    }

    /** Get Customer Phone#.
     * @return phone
     * */
    public String getPhone(){
        return phone;
    }
    /**
     * Set the customer phone#.
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
//    public ObservableList<Appointments> getAssocAppointments() {
//        return assocAppointments;
//    }
    /**
     * Get Division.
     * @return division
     * */
    public String getDivision(){
        return division;
    }
    /**
     * @param division the division to set
     */
    public void setDivision(String division) {
        this.division = division;
    }
    /**
     * Get Country.
     * @return country
     * */
    public String getCountry(){
        return country;
    }
    /**
     * Set Country.
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }
    /**
     *override the toString method to display actual text on the combo box. */
    @Override
    public String toString() {
        return ("#" +Integer.toString(custID) + " " + name);
    }
    public int getDivisionID() {
        return divisionID;
    }
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }
}
