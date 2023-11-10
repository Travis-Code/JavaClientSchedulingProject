package controller;

import DAO.DBappointments;
import DAO.DBcontacts;
import DAO.DBcustomers;
import DAO.UserDB;
import model.Appointments;
import model.Contacts;
import model.Customer;
import model.User;
import utilities.TimeConversions;
import DAO.JDBC;
import utilities.dbQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.*;
import java.util.Objects;
import java.util.ResourceBundle;

/**Appointment screen controller.* */
public class AppointmentScreenController implements Initializable {
    Stage stage;
    private Appointments selectedAppt;
    @FXML private TextField appointmentIDField;
    @FXML private TextField titleField;
    @FXML private TextField descriptionField;
    @FXML private ComboBox<Contacts> contactComboBox;
    @FXML private TextField typeField;
    @FXML private ComboBox<Customer> customerIDComboBoxField;
    @FXML private ComboBox<User> userIDField;
    @FXML private TextField locationField;
    @FXML private DatePicker startDate;
    @FXML private DatePicker endDate;
    @FXML private ComboBox<String> startTime;
    @FXML private ComboBox<String> endTime;
    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;
    @FXML private Button cancelButton;

    @FXML private TableView<Appointments> calendartable;
    @FXML private TableColumn<Appointments, Integer> apptidcol;
    @FXML private TableColumn<Appointments, String> titlecol;
    @FXML private TableColumn<Appointments, String> descriptcol;
    @FXML private TableColumn<Appointments, String> locationcol;
    @FXML private TableColumn<Contacts, String> contactcol;
    @FXML private TableColumn<Appointments, String> typecol;
    @FXML private TableColumn<Appointments, Date> startcol;
    @FXML private TableColumn<Appointments, Date> endcol;
    @FXML private TableColumn<Customer, Integer> custidcol;
    @FXML private TableColumn<User, Integer> useridcol;
    @FXML private RadioButton viewall;
    @FXML private RadioButton viewmonth;
    @FXML private RadioButton viewweek;

    /**
     * Select Appointment from the database.
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML public void selectedAppointment(ActionEvent event) throws IOException, SQLException {
        Appointments selectedAppt = calendartable.getSelectionModel().getSelectedItem();
        if (selectedAppt == null) {
            //Please select a Customer
            utilities.AlertsUtility.errorAlerts(7);
            return;
        }
        int apptID = selectedAppt.getApptID();

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Appointment.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        AppointmentScreenController controller = loader.getController();
        controller.getModAppt(calendartable.getSelectionModel().getSelectedItem());
        stage.show();

        Connection connection = JDBC.getConnection();
        dbQuery.setStatement(connection);
        Statement statement = dbQuery.getStatement();
        String SelectApptStatement = "SELECT * FROM appointments where Appointment_ID = " + apptID + "";
        System.out.println("Select Appointment statement: " + SelectApptStatement);
        statement.execute(SelectApptStatement);
    }

    /**
     * check for blank fields.
     */
    private boolean checkForBlankFields() {
        return titleField.getText().isEmpty() || descriptionField.getText().isEmpty() || contactComboBox.getValue() == null ||
                locationField.getText().isEmpty() || typeField.getText().isEmpty() ||
                startDate.getValue() == null || startTime.getValue() == null || endDate.getValue() == null ||
                endTime.getValue() == null || customerIDComboBoxField.getValue() == null || userIDField.getValue() == null;
    }

    /**
     * Add new Appointment to the database.
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML public void addNewAppointments(ActionEvent event) throws SQLException, IOException {
        System.out.println("add button clicked.");
        if (checkForBlankFields()) {
            //Please Fill in Fields.
            utilities.AlertsUtility.errorAlerts(10);
        } else {
            //get Timestamp for start date and time.
            System.out.println(startTime.getValue());
            System.out.println(startDate.getValue());
            LocalDate localDateStart = startDate.getValue();
            LocalTime localTimeStart = LocalTime.parse(startTime.getValue());
            Timestamp timestampStart = Timestamp.valueOf(LocalDateTime.of(localDateStart, localTimeStart));
            System.out.println("Start time: " + timestampStart);
            //get Timestamp for end date and time.
            System.out.println(endDate.getValue());
            System.out.println(endTime.getValue());
            LocalDate localDateEnd = endDate.getValue();
            LocalTime localTimeEnd = LocalTime.parse(endTime.getValue());
            Timestamp timestampEnd = Timestamp.valueOf(LocalDateTime.of(localDateEnd, localTimeEnd));
            System.out.println("End time: " + timestampEnd);
            int id = 0;
            appointmentIDField.setText(String.valueOf(++id));
            String title = titleField.getText();
            String description = descriptionField.getText();
            String location = locationField.getText();
            String type = typeField.getText();
            timestampStart = Timestamp.valueOf(LocalDateTime.of(localDateStart, localTimeStart));
            timestampEnd = Timestamp.valueOf(LocalDateTime.of(localDateEnd, localTimeEnd));
            int custID = Integer.parseInt(String.valueOf(customerIDComboBoxField.getValue().getCustID()));
            int userID = userIDField.getSelectionModel().getSelectedItem().getUserId();
            int contactID = Integer.parseInt(String.valueOf(contactComboBox.getValue().getContactID()));
            String custName = String.valueOf(customerIDComboBoxField.getValue());
            String contactName = String.valueOf(contactComboBox.getValue());
            String userName = String.valueOf(userIDField.getValue());
            //Converts business hours from est to local time
            ZonedDateTime starBusinessEst = ZonedDateTime.of(localDateStart.getYear(), localDateStart.getMonthValue(), localDateStart.getDayOfMonth(), 8, 0, 0, 0, ZoneId.of("America/New_York"));
            ZonedDateTime endBusinessEst = ZonedDateTime.of(localDateEnd.getYear(), localDateEnd.getMonthValue(), localDateEnd.getDayOfMonth(), 22, 0, 0, 0, ZoneId.of("America/New_York"));
            ZonedDateTime localStart = starBusinessEst.withZoneSameInstant(ZoneId.systemDefault());
            ZonedDateTime localEnd = endBusinessEst.withZoneSameInstant(ZoneId.systemDefault());
            //Outside business hours
            if (localTimeStart.isBefore(LocalTime.from(localStart)) || localTimeEnd.isAfter(LocalTime.from(localEnd))) {
                //"OUTSIDE BUSINESS HOURS"
                utilities.AlertsUtility.errorAlerts(17);
                return;
            }

            //check endate before start date
            if(localDateStart.isAfter(localDateEnd)){
                System.out.println("adsfawefawefawefawefawfefawefawefawefawefawefawefawe");
                System.out.println("localDateStart::: " + localDateStart);
                System.out.println("localDateEnd::: " + localDateEnd);
                utilities.AlertsUtility.errorAlerts(23);
                return;
            }

            boolean testOverlap = checkForOverlappingAppointment(timestampStart, timestampEnd, custID);
            System.out.println("TEST for overlap add is: "+ testOverlap);
            if(testOverlap){
                //This appointment is overlapping another one. Please try again
                utilities.AlertsUtility.errorAlerts(18);
                return;
            }

            //Checking for any overlapping appointments
            Appointments overlapAppt = DBappointments.checkOverlapAppt(TimeConversions.localUtc(timestampStart), TimeConversions.localUtc(timestampEnd), custID);
            if (overlapAppt != null) {
                utilities.AlertsUtility.errorAlerts(18);
                return;
            }
            Connection connection = JDBC.getConnection();
            dbQuery.setStatement(connection);
            Statement statement = dbQuery.getStatement();
            //TimeConversions.localUtc(timestampStart) + "', '" + TimeConversions.localUtc(timestampEnd)
            String insertStatement = "INSERT INTO  appointments(Title, Description, Location, Type, Start, End, Created_By, Last_Updated_By, Customer_ID, User_ID, Contact_ID) " +
                    "VALUES('" + title + "', '" + description + "', '" + location + "', '" + type + "', '" + TimeConversions.localUtc(timestampStart) + "', '" + TimeConversions.localUtc(timestampEnd) + "','" + userName + "', '" + userName + "' , '" + custID + "', '" + userID + "','" + contactID + "')";
            System.out.println("Insert statement: " + insertStatement);
            statement.execute(insertStatement);

            utilities.Navigation.returnToAppointmentScreen(addButton);
        }
    }
    /**
     * Back to the main screen.
     * @param event
     * @throws IOException
     * */
    @FXML public void cancelButtonHandler(ActionEvent event) throws IOException {
//      alert.setContentText("Are you sure you want to cancel?");
        utilities.AlertsUtility.errorAlerts(11);
        utilities.Navigation.goBackToMainScreen(cancelButton);
    }
    @FXML void contactbox (ActionEvent event){}
    @FXML void customerIDComboBox(ActionEvent event){}
    @FXML void appointmentIDFieldHandler(ActionEvent event){}

    // TEST FOR OVERLAP
    public static boolean checkForOverlappingAppointment(Timestamp start, Timestamp end, int customerID) {
        try {
            String sql = "SELECT *"
                    + " FROM appointments"
                    + " WHERE Customer_ID = ? and Start < ? and end > ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, customerID);
            ps.setObject(2, end);
            ps.setObject(3, start);
            ResultSet rs = ps.executeQuery();
            // If there is a result row, we know it’s an overlapping appointment
            return rs.next();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delete selected appointment from the table
     * @param event
     * @throws SQLException
     * @throws IOException
     * */
    @FXML public void deleteAppointmentButtonHandler(ActionEvent event) throws SQLException, IOException {
        Appointments selectedAppt = calendartable.getSelectionModel().getSelectedItem();
        if (selectedAppt == null) {
            //Please Select an Appointment
            utilities.AlertsUtility.errorAlerts(19);
            return;
        }
        int apptId = selectedAppt.getApptID();
        int baseCount = 0;
        //Do you want to delete the selected appointment?
        utilities.AlertsUtility.errorAlerts(20);
        Connection connection = JDBC.getConnection();
        dbQuery.setStatement(connection);
        Statement statement = dbQuery.getStatement();
        String deleteStatement = "DELETE FROM appointments where Appointment_ID= " + apptId + "";
        System.out.println("Delete statement: " + deleteStatement);
        statement.execute(deleteStatement);
        if (statement.getUpdateCount() > baseCount)
            System.out.println(statement.getUpdateCount() + "rows affected");
        else
            System.out.println("No Changes");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setContentText("Appointment Deleted " + "Appointment ID: " + selectedAppt.getApptID() + " Type: " + selectedAppt.getType());
        alert.showAndWait();
        //"/view/Appointment.fxml"
        utilities.Navigation.returnToAppointmentScreen(deleteButton);
    }
    @FXML void descriptionbox (ActionEvent event){}
    @FXML void locationbox (ActionEvent event){}
    @FXML void enddatecombobox (ActionEvent event){}
    @FXML void endtimecombobox (ActionEvent event){}
    @FXML void startdatepicker (ActionEvent event){}
    @FXML void starttimecombobox (ActionEvent event){

        System.out.println("starttimecombobox:::: "+ startTime.getValue());
        System.out.println("From Combobox::: " + startTime.getSelectionModel().getSelectedItem());

    }
    @FXML void titlebox (ActionEvent event){}
    @FXML void typebox (ActionEvent event){}

    @FXML public void viewWeekMonthAllButtonHandler(ActionEvent event){
        if (viewall.isSelected()) {
            //System.out.println("View all button clicked");
            calendartable.setItems(DBappointments.getAllAppointments());
        } else {
            if (viewweek.isSelected()) {
                //System.out.println("View Week button clicked");
                calendartable.setItems(DBappointments.getThisWeekAppts());
                System.out.println("viewweek clicked:::: " + DBappointments.getThisWeekAppts());
            }
        }
        if (viewmonth.isSelected()) {
            //System.out.println("View Month button clicked");
            calendartable.setItems(DBappointments.getThisMonthAppts());
        }
    }
    /**
     * This method updates the Appointment.
     * @param event
     * @throws SQLException
     * @throws IOException
     * */
    @FXML public void updateAppointmentButtonHandler(ActionEvent event) throws SQLException, IOException {
        System.out.println("main part modify Clicked");
        if (checkForBlankFields()) {
            //Click on a appointment in the table, then click Select Appointment button.  Click the Update button again to update.
            utilities.AlertsUtility.errorAlerts(21);
        } else {
            int apptId = selectedAppt.getApptID();
            //Timestamp for start
            System.out.println(startDate.getValue());
            System.out.println(startTime.getValue());
            LocalDate localDateStart = startDate.getValue();
            LocalTime localTimeStart = LocalTime.parse(startTime.getValue());
            Timestamp timeStampStart = Timestamp.valueOf(LocalDateTime.of(localDateStart, localTimeStart));
            System.out.println("Start time: " + timeStampStart);
            //Timestamp for end
            System.out.println(endDate.getValue());
            System.out.println(endTime.getValue());
            LocalDate localDateEnd = endDate.getValue();
            LocalTime localTimeEnd = LocalTime.parse(endTime.getValue());
            Timestamp timeStampEnd = Timestamp.valueOf(LocalDateTime.of(localDateEnd, localTimeEnd));
            System.out.println("End time: " + timeStampEnd);
            //Converts business hours from est to local time
            ZonedDateTime starBusinessEst = ZonedDateTime.of(localDateStart.getYear(), localDateStart.getMonthValue(), localDateStart.getDayOfMonth(), 8, 0, 0, 0, ZoneId.of("America/New_York"));
            ZonedDateTime endBusinessEst = ZonedDateTime.of(localDateEnd.getYear(), localDateEnd.getMonthValue(), localDateEnd.getDayOfMonth(), 22, 0, 0, 0, ZoneId.of("America/New_York"));
            ZonedDateTime localStart = starBusinessEst.withZoneSameInstant(ZoneId.systemDefault());
            ZonedDateTime localEnd = endBusinessEst.withZoneSameInstant(ZoneId.systemDefault());
            //Outside business hours
            if (localTimeStart.isBefore(LocalTime.from(localStart)) || localTimeEnd.isAfter(LocalTime.from(localEnd))) {
                //Business hours are 8AM to 10PM EST, including Weekends
                utilities.AlertsUtility.errorAlerts(17);
                return;
            }
            int apptID = Integer.parseInt(appointmentIDField.getText());
            String title = titleField.getText();
            String description = descriptionField.getText();
            String location = locationField.getText();
            String type = typeField.getText();
            timeStampStart = Timestamp.valueOf(LocalDateTime.of(localDateStart, localTimeStart));
            timeStampEnd = Timestamp.valueOf(LocalDateTime.of(localDateEnd, localTimeEnd));
            int custID = Integer.parseInt(String.valueOf(customerIDComboBoxField.getValue().getCustID()));
            int userID = userIDField.getSelectionModel().getSelectedItem().getUserId();
            int contactID = Integer.parseInt(String.valueOf(contactComboBox.getValue().getContactID()));

            //Check for overlapping appointments
//            Appointments overlapAppt = DBappointments.checkOverlapAppt(timeStampStart, timeStampEnd, custID);
//            if (overlapAppt != null && apptId != apptID) {
//                //This appointment is overlapping another one. Please try again
//                utilities.AlertsUtility.errorAlerts(18);
//                return;
//            }

            boolean testOverlap = checkForOverlappingAppointment(timeStampStart, timeStampEnd, custID);
            System.out.println("TEST for overlap add is: "+ testOverlap);
            if(testOverlap && apptId != apptID){
                //This appointment is overlapping another one. Please try again
                utilities.AlertsUtility.errorAlerts(18);
                return;
            }

            //check endate before start date
            if(localDateStart.isAfter(localDateEnd)){
                System.out.println("localDateStart::: " + localDateStart);
                System.out.println("localDateEnd::: " + localDateEnd);
                utilities.AlertsUtility.errorAlerts(23);
                return;
            }


            //Appointments modAppt = new Appointments(apptID, title, description, location, type, timeStampStart, timeStampEnd, custID, userID, contactID, custName, userName, contactName);
            Connection connection = JDBC.getConnection();
            dbQuery.setStatement(connection);
            Statement statement = dbQuery.getStatement();
            String updateStatement = "UPDATE appointments SET Title = '" + title + "', Description = '" + description + "', Location = '" + location + "', Type = '" + type + "', Start = '" + TimeConversions.localUtc(timeStampStart) + "', End = '" + TimeConversions.localUtc(timeStampEnd) + "', Customer_ID = '" + custID + "', User_ID = '" + userID + "', Contact_ID = '" + contactID + "' WHERE Appointment_ID = '" + apptId + "'";
            //System.out.println("Update statement: " + updateStatement);
            statement.execute(updateStatement);
            int baseCount = 0;
            if (statement.getUpdateCount() > baseCount)
                System.out.println(statement.getUpdateCount() + "rows affected");
            else
                System.out.println("Nothing changed");
            ///view/Appointment.fxml
            utilities.Navigation.returnToAppointmentScreen(updateButton);
        }
    }
    @FXML void useridboxHandler (ActionEvent event){}
    @FXML public void viewallbutton (ActionEvent event){}
    @FXML void viewmonthradio (ActionEvent event){}
    @FXML void viewweekradio (ActionEvent event){}

    /**
     * Pass data between selected customer table and fills out the fields.
     * @param appointments
     * */
    public void getModAppt (Appointments appointments){
        selectedAppt = appointments;
        appointmentIDField.setText(Integer.toString(appointments.getApptID()));
        titleField.setText(appointments.getTitle());
        descriptionField.setText(appointments.getDescription());
        locationField.setText(appointments.getLocation());
        typeField.setText(appointments.getType());
        startTime.setValue(String.valueOf(appointments.getStart().toLocalDateTime().toLocalTime()));
        System.out.println("startTime::: " +appointments.getStart().toLocalDateTime().toLocalTime());
        startDate.setValue(appointments.getStart().toLocalDateTime().toLocalDate());
        endDate.setValue(appointments.getStart().toLocalDateTime().toLocalDate());
        endTime.setValue(String.valueOf(appointments.getEnd().toLocalDateTime().toLocalTime()));
        ObservableList<Contacts> contacts = Contacts.getAllContacts();
        contactComboBox.setItems(contacts);
        Contacts contacts1 = null;
        for (Contacts co : DBcontacts.getAllContacts()) {
            if (co.getContactName().equals(selectedAppt.getContact()))
                contacts1 = co;
        }
        contactComboBox.setValue(contacts1);
        ObservableList<Customer> customers = Customer.getAllCustomers();
        customerIDComboBoxField.setItems(customers);
        Customer customers1 = null;
        for (Customer cu : Objects.requireNonNull(DBcustomers.getAllCustomers())) {
            if (cu.getName().equals(selectedAppt.getCustomer()))
                customers1 = cu;
        }
        customerIDComboBoxField.setValue(customers1);
        ObservableList<User> users = User.getAllUsers();
        userIDField.setItems(users);
        User users1 = null;
        for (User us : UserDB.getAllUsers()) {
            if (us.getUserName().equals(selectedAppt.getUser()))
                users1 = us;
        }
        userIDField.setValue(users1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Display all Appointments
        calendartable.setItems(DBappointments.getAllAppointments());
        selectedAppt = new Appointments(0,null,null,null,null,null,null,0,0,0,null,null,null);
        apptidcol.setCellValueFactory((new PropertyValueFactory<>("apptID")));
        titlecol.setCellValueFactory((new PropertyValueFactory<>("title")));
        descriptcol.setCellValueFactory((new PropertyValueFactory<>("description")));
        locationcol.setCellValueFactory((new PropertyValueFactory<>("location")));
        contactcol.setCellValueFactory((new PropertyValueFactory<>("contact")));
        typecol.setCellValueFactory((new PropertyValueFactory<>("type")));
        startcol.setCellValueFactory((new PropertyValueFactory<>("start")));
        endcol.setCellValueFactory((new PropertyValueFactory<>("end")));
        custidcol.setCellValueFactory((new PropertyValueFactory<>("customer")));
        useridcol.setCellValueFactory((new PropertyValueFactory<>("user")));
        contactComboBox.setPromptText("Select Contact");
        userIDField.setPromptText("Select User");
        customerIDComboBoxField.setPromptText("Select Customer");

        //Display all contacts in comboBox
        ObservableList<Contacts> contacts = Contacts.getAllContacts();
        contactComboBox.setItems(contacts);

        //Display all customers in comboBox
        ObservableList<Customer> customers = Customer.getAllCustomers();
        customerIDComboBoxField.setItems(customers);

        //Displays all users in comboBox
        ObservableList<User> users = User.getAllUsers();
        userIDField.setItems(users);

        //Populate start and end time combo boxes
        LocalTime start = LocalTime.of(5, 0);
        LocalTime end = LocalTime.of(23, 0);

        while (start.isBefore(end.plusSeconds(1))) {
            startTime.getItems().add(String.valueOf(start));
            start = start.plusMinutes(30);
            endTime.getItems().add(String.valueOf(start));
        }
//        startTime.getSelectionModel().select(String.valueOf(LocalTime.of(0, 0)));
//        startTime.setPromptText("START TIME");
//        startTime.getSelectionModel().selectFirst();
//        endTime.getSelectionModel().select(String.valueOf(LocalTime.of(0,0)));
//        endTime.setPromptText("END TIME");
//        endTime.getSelectionModel().selectNext();
    }
}
