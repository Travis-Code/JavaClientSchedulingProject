package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Report Main screen controller.
 *
 * */
public class ReportMainScreenController implements Initializable {

    Parent scene;
    Stage stage;

    @FXML private Button totalappt;
    @FXML private Button contanctschedule;
    @FXML private Button customerlocation;
    @FXML private Button backbutton;
    @FXML private Button userschedules;

    /**
     * Navigate to Contact Report Screen.
     * @param event
     * @throws IOException
     * */
    @FXML public void contactScheduleReport(ActionEvent event) throws IOException {
        ///view/ReportContacts.fxml
        utilities.Navigation.goToReportContacts(contanctschedule);
    }
    /**
     * Navigate to User Schedule screen.
     * @param event
     * @throws IOException
     * */
    @FXML public void userScheduleScreenButtonHandler(ActionEvent event) throws IOException {
        //"/view/ReportUserAppt.fxml"
        utilities.Navigation.goToReportUserScreen(userschedules);
    }
    /**Main Screen.
     * @param event
     * @throws IOException
     * */
    @FXML public void returntoMainMenu(ActionEvent event) throws IOException {
        //Are you sure you want to return to the main menu?
        utilities.AlertsUtility.errorAlerts(16);
        //main menu
        utilities.Navigation.goBackToMainScreen(backbutton);
    }
    /**
     *Appointment Total Report screen.
     * @param event
     * @throws IOException
     * */
    @FXML public void totalAppointmentsScreen(ActionEvent event) throws IOException {
        // "/view/ReportTotalAppt.fxml"
        utilities.Navigation.ReportTotalAppt(totalappt);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
