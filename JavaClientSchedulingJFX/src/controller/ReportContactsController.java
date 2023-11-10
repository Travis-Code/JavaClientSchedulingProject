package controller;

import DAO.DBappointments;
import DAO.DBcontacts;
import model.Appointments;
import model.Contacts;
import model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

/** Class for the Report Contacts screen controller. */
public class ReportContactsController implements Initializable {
    @FXML private TableView<Appointments> contactReportTable;
    @FXML private TableColumn<Appointments, Integer> apptidcol;
    @FXML private TableColumn<Appointments, String> titlecol;
    @FXML private TableColumn<Appointments, String> typecol;
    @FXML private TableColumn<Appointments, String> descriptioncol;@FXML
    private TableColumn<Appointments, Date> startcol;
    @FXML private TableColumn<Appointments, Date> endcol;
    @FXML private TableColumn<Customer, Integer> customeridcol;
    @FXML private Button backbutton;
    @FXML private ComboBox<Contacts> contactbox;

    /**
     * Returns to Report Main Screen.
     * @param event
     * @throws IOException
     * */
    @FXML public void returnToReportScreenButtonHandler(ActionEvent event) throws IOException {
        //Are you sure you want to return?
        utilities.AlertsUtility.errorAlerts(22);
        ///view/ReportMainScreen.fxml
        utilities.Navigation.goToReportMainScreen(backbutton);
    }
    /** Show results of the selected Contact.
     * @param event
     * @throws SQLException
     * */
    @FXML public void showresultsbutton(ActionEvent event) throws SQLException {
        int selectedContact = contactbox.getSelectionModel().getSelectedItem().getContactID();
        contactReportTable.setItems(DBcontacts.getApptsByContact(selectedContact));
    }
    @FXML public void contactComboBox(ActionEvent event) {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactReportTable.setItems(DBappointments.getAllAppointments());
        contactbox.setItems(DBcontacts.getAllContacts());
        contactbox.getSelectionModel().selectFirst();
        apptidcol.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        titlecol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptioncol.setCellValueFactory(new PropertyValueFactory<>("description"));
        typecol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startcol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endcol.setCellValueFactory(new PropertyValueFactory<>("end"));
        customeridcol.setCellValueFactory(new PropertyValueFactory<>("customer"));
    }
}