package model;

import DAO.DBcountries;
import javafx.collections.ObservableList;

/** Class for the Countries model. */
public class Countries {

    private int id;
    private String name;

    /**
     * Constructor for Countries.
     * @param id
     * @param name
     * */
    public Countries(int id, String name){
        this.id = id;
        this.name = name;
    }

    /**
     * Return all of the Countries from the database.
     * */
    public static ObservableList<Countries> getAllCountries() {
        return DBcountries.getAllCountries();
    }


    /** The getter for Countries ID.
     * @return*/
    public int getId() {
        return id;
    }


    /** The getter for Countries Name. */
    public String getName(){
        return name;
    }

    /** This overrides the toString method for the combo box. */
    @Override
    public String toString() {
        return (name);
    }

}
