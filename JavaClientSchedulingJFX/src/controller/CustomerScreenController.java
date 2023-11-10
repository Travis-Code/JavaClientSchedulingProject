package controller;

//import utilities.dbConnection;

import DAO.*;
import model.Appointments;
import model.Countries;
import model.Customer;
import model.FLDivisions;
import utilities.dbQuery;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Customer screen controller and Lambda expression in initialize.
 * */
public class CustomerScreenController implements Initializable {
    Parent scene;
    Stage stage;
    private Customer selectedCustomer;
    int index;
    @FXML private AnchorPane Customerscreen;
    @FXML private TextField postalfield;
    @FXML private TextField addressfield;
    @FXML private TextField namefield;
    @FXML private TextField phonefield;
    @FXML private TextField customerIDField;

    @FXML public ComboBox<FLDivisions> divisionbox;
    @FXML public ComboBox<Countries> countriesComboBox;
    @FXML private Label countrylabel;
    @FXML private Label divisionlabel;
    @FXML private Button cancelButton;
    @FXML private Button addbutton;
    @FXML private TableView<Customer> customerinfo;
    @FXML private TableColumn<Customer, Integer> custid;
    @FXML private TableColumn<Customer, String> custname;
    @FXML private TableColumn<Customer, String> custnumber;
    @FXML private TableColumn<Customer, String> custcountry;
    @FXML private TableColumn<Customer, Integer> custdivision;
    @FXML private TableColumn<Customer, String> custaddress;
    @FXML private TableColumn<Customer, String> custpostal;
    @FXML private Button updatebutton;
    @FXML private Button deletebutton;
//    @FXML private Button selectbutton;

    /**
     * Check for blank fields.
     * @return true or false.
     * */
    private boolean checkForBlankFields() {
        return namefield.getText().isEmpty() ||
                addressfield.getText().isEmpty() || countriesComboBox.getValue() == null ||
                phonefield.getText().isEmpty() || postalfield.getText().isEmpty() || divisionbox.getValue() == null;
    }

    /**
     * gets and selects the Customer by the Customer ID.
     * @param event */
    @FXML public void selectedCustomerButtonHandler(ActionEvent event) {
        try {
            Customer selectedCustomer = customerinfo.getSelectionModel().getSelectedItem();
            if(selectedCustomer == null){
                //Please select a Customer
                utilities.AlertsUtility.errorAlerts(7);
                return;
            }
            int customerId = selectedCustomer.getCustID();

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CustomerScreen.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            CustomerScreenController controller = loader.getController();
            controller.getModCustomer(customerinfo.getSelectionModel().getSelectedItem());
            stage.show();

            Connection connection = JDBC.getConnection();
            dbQuery.setStatement(connection);
            Statement st = dbQuery.getStatement();
            String SelectCustStatement = "SELECT Customer_Name, " +
                    "Address, " +
                    "Postal_Code, " +
                    "Phone, " +
                    "Division_ID FROM customers where Customer_ID = " +
                    customerId + "";
            st.execute(SelectCustStatement);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update Customer table.
     * @param event
     * @throws SQLException
     * @throws IOException
     * */
    @FXML
    public void updateCustomerButtonHandler(ActionEvent event) throws SQLException, IOException {
        if (checkForBlankFields()) {
            //Click a Customer in the table
            utilities.AlertsUtility.errorAlerts(8);
        } else {
            int customerId = selectedCustomer.getCustID();
            if (selectedCustomer == null) {
                //Select a Customer
                utilities.AlertsUtility.errorAlerts(9);
            }
            System.out.println("Update button Clicked");
            String custName = namefield.getText();
            String address = addressfield.getText();
            String postal = postalfield.getText();
            String phone = phonefield.getText();
            int divisionID = divisionbox.getValue().getDivisionID();


            Connection connection = JDBC.getConnection();
            dbQuery.setStatement(connection);
            Statement statement = dbQuery.getStatement();
            String updateStatement = "UPDATE customers SET Customer_Name = '" + custName +
                    "', Address = '" + address +
                    "', Postal_Code = '" + postal +
                    "', Phone = '" + phone +
                    "', Division_ID = '" + divisionID +
                    "'  WHERE Customer_ID = " + customerId + "";

            statement.execute(updateStatement);

            if (statement.getUpdateCount() > 0) {
                ///view/CustomerScreen.fxml
                utilities.Navigation.goToCustomerScreenButtonHandler(updatebutton);

            }else {
                System.out.println(statement.getUpdateCount() + " row(s) Check Update Handler.");
                ///view/CustomerScreen.fxml
                utilities.Navigation.goToCustomerScreenButtonHandler(updatebutton);
            }
        }
    }

    /**
     * Add a new Customer to the table.
     * @param event
     * @throws SQLException
     * @throws IOException
     * */
    @FXML public void addNewCustomerButtonHandler(ActionEvent event) throws SQLException, IOException {
        if (checkForBlankFields()) {
            //Check for empty Fields.
            utilities.AlertsUtility.errorAlerts(10);
        } else {
            int id = 0;
            customerIDField.setText(String.valueOf(++id));
            String name = namefield.getText();
            String address = addressfield.getText();
            String postal = postalfield.getText();
            String phone = phonefield.getText();
            int divisionID = divisionbox.getValue().getDivisionID();

            Connection connection = JDBC.getConnection();
            dbQuery.setStatement(connection);
            Statement statement = dbQuery.getStatement();
            String insertStatement = "INSERT INTO customers(Customer_Name, " +
                    "Address, " +
                    "Postal_Code , " +
                    "Phone,  " +
                    "Created_By,  " +
                    "Last_Updated_By, " +
                    "Division_ID) " +
                    "VALUES('" + name + "', " + "'" + address + "', '" + postal + "', '" + phone + "', " + "' admin', " + "  'admin' , '" + divisionID + "' )";

            System.out.println("Insert statement: " + insertStatement);
            statement.execute(insertStatement);
            if (statement.getUpdateCount() > 0) {
                System.out.println(statement.getUpdateCount() + "rows affected");
                utilities.Navigation.goToCustomerScreenButtonHandler(addbutton);
            }else {
                System.out.println("Check add handler!");
                ///view/CustomerScreen.fxml
                utilities.Navigation.goToCustomerScreenButtonHandler(addbutton);
            }
        }
    }
    /**
     * Returns to the Main screen.
     * @param event
     * @throws IOException
     * */
    @FXML public void cancelButtonHandler(ActionEvent event) throws IOException {
        //want to cancel?
        utilities.AlertsUtility.errorAlerts(11);
        //mainscreen
        utilities.Navigation.goBackToMainScreen(cancelButton);
    }

    /**
     * Combobox to select the country and filter divisions based on Country selection. Lambda used to reduce code
     * @param event
     * */
    @FXML public void countryComboBoxHandler(ActionEvent event) {
        System.out.println("country combobox: "+ countriesComboBox.getValue());
        Countries selectCountry = countriesComboBox.getSelectionModel().getSelectedItem();
        divisionbox.setItems(DBfldivisions.getAllDivisions().stream().filter(division -> division.getCountryID() == selectCountry.getId()).collect(Collectors.toCollection(FXCollections::observableArrayList)));
        //divisionbox.getSelectionModel().selectFirst();
    }
    /**
     * display customers on table.
     * @param event
     * */
    @FXML public void customerTable(ActionEvent event) { customerinfo.refresh();}

    /**
     * Delete a customer from the table and show an alert if there are associated appointments.
     * @param event
     * @throws SQLException
     * @throws IOException
     * */
    @FXML public void deleteCustomer(ActionEvent event) throws SQLException, IOException {
        Customer selectedCustomer = customerinfo.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
//          "Please Select a Customer first"
            utilities.AlertsUtility.errorAlerts(12);
            return;
        }
        int customerId = selectedCustomer.getCustID();
        //want to delete the selected customer?
        utilities.AlertsUtility.errorAlerts(13);
        try {
            Connection connection = JDBC.getConnection();
            dbQuery.setStatement(connection);
            Statement statement = dbQuery.getStatement();
            String deleteStatement = "DELETE FROM customers where Customer_ID= " + customerId + "";
            System.out.println("Delete statement: " + deleteStatement);
            statement.execute(deleteStatement);
            int baseCount = 0;
            if (statement.getUpdateCount() > baseCount)
                System.out.println(statement.getUpdateCount() + "rows affected");
            else
                //Customer Removed
                utilities.AlertsUtility.errorAlerts(14);
            ///view/CustomerScreen.fxml
            utilities.Navigation.goToCustomerScreenButtonHandler(deletebutton);

        } catch (SQLException throwables) {
            //Customer has an Associated Appointment. All associated appointments will be removed.
            utilities.AlertsUtility.errorAlerts(15);
            ObservableList<Appointments> listOfAppointments = DBappointments.getAllAppointments();
            //delete all appointments containing selected customer
            assert listOfAppointments != null;
            for (Appointments appointment : listOfAppointments) {
                if (appointment.getCustID() == selectedCustomer.getCustID()) {
                    DBappointments.deleteAppt(appointment.getApptID());
                }
            }
        }
    }
    @FXML void customerIDbox(ActionEvent event) {}
    @FXML void divisionComboBox(ActionEvent event) {}
    @FXML void namebox(ActionEvent event) {}
    @FXML void phonebox(ActionEvent event) {}
    @FXML void postalcodebox(ActionEvent event) {}
    @FXML void addressbox(ActionEvent event) {}

    /**
     * pass data between the selected customer table and fields.
     * @param customer
     * */
    public void getModCustomer(Customer customer) {
        selectedCustomer = customer;
        customerIDField.setText(Integer.toString(customer.getCustID()));
        namefield.setText(customer.getName());
        addressfield.setText(customer.getAddress());
        postalfield.setText(customer.getPostal());
        phonefield.setText(customer.getPhone());
        ObservableList<Countries> countryAll = Countries.getAllCountries();
        countriesComboBox.setItems(countryAll);
        FLDivisions divisions = null;
        for (FLDivisions f: DBfldivisions.getAllDivisions()){
            if (f.getDivisionName().equals(customer.getDivision()))
                divisions = f;
        }
        divisionbox.setValue(divisions);
        Countries countries = null;
        for (Countries c : DBcountries.getAllCountries()){
            assert divisions != null;
            if (c.getId() == (divisions.getCountryID()))
                countries = c;
        }
        countriesComboBox.setValue(countries);
    }
    /**
     * Initialize CustomerScreenController Lambda used for table data population.
     * @param resourceBundle
     * @param url
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerinfo.setItems(DBcustomers.getAllCustomers());
        selectedCustomer = new Customer(0, null, null, null, null, null, null);
        custid.setCellValueFactory((new PropertyValueFactory<>("custID")));
        custdivision.setCellValueFactory((new PropertyValueFactory<>("division")));
        //Lambda expressions populates customer table
        custname.setCellValueFactory(customer -> new SimpleStringProperty(customer.getValue().getName()));
        custnumber.setCellValueFactory(customer -> new SimpleStringProperty(customer.getValue().getPhone()));
        custcountry.setCellValueFactory(customer -> new SimpleStringProperty(customer.getValue().getCountry()));
        custaddress.setCellValueFactory(customer -> new SimpleStringProperty(customer.getValue().getAddress()));
        custpostal.setCellValueFactory(customer -> new SimpleStringProperty(customer.getValue().getPostal()));
        divisionbox.setPromptText("SELECT DIVISION");
        countriesComboBox.setPromptText("SELECT COUNTRY");
        ObservableList<Countries> countryAll = Countries.getAllCountries();
        countriesComboBox.setItems(countryAll);
    }
}
