package controller;

import DAO.DBappointments;
import DAO.UserDB;
import model.Appointments;
import model.Customer;
import model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

/**
 * Report User Appointment screen controller Class.
 * */
public class ReportUserApptController implements Initializable {
    @FXML private TableView<Appointments> userReportTable;
    @FXML private TableColumn<Appointments, Integer> apptidcol;
    @FXML private TableColumn<Appointments, String> titlecol;
    @FXML private TableColumn<Appointments, String> typecol;
    @FXML private TableColumn<Appointments, String> descriptioncol;
    @FXML private TableColumn<Appointments, Date> startcol;
    @FXML private TableColumn<Appointments, Date> endcol;
    @FXML private TableColumn<Customer, Integer> customeridcol;
    @FXML private Button backbutton;
    @FXML private ComboBox<User> userbox;
    @FXML private Button showresults;

    /**
     * Return to Report Main Screen.
     * @param event
     * @throws IOException
     * */
    @FXML public void returnToReportscreen(ActionEvent event) throws IOException {
        //Are you sure you want to return?
        utilities.AlertsUtility.errorAlerts(22);
        ///view/ReportMainScreen.fxml
        utilities.Navigation.goToReportMainScreen(backbutton);
    }

    /**
     * Shows results of the selected User.
     * @param event
     * */
    @FXML public void showResultsButton(ActionEvent event) {
        int selectedUser = userbox.getSelectionModel().getSelectedItem().getUserId();
        userReportTable.setItems(UserDB.getAppointmentsByUser(selectedUser));
    }

    @FXML void userCombobox(ActionEvent event) { }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userReportTable.setItems(DBappointments.getAllAppointments());
        userbox.setItems(UserDB.getAllUsers());
        userbox.getSelectionModel().selectFirst();

        apptidcol.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        titlecol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptioncol.setCellValueFactory(new PropertyValueFactory<>("description"));
        typecol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startcol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endcol.setCellValueFactory(new PropertyValueFactory<>("end"));
        customeridcol.setCellValueFactory(new PropertyValueFactory<>("customer"));
    }
}

