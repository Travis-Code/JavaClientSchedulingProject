package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This is the class for the Main screen controller. */
public class MainScreenController implements Initializable {

    @FXML private Button appointmentButton;
    @FXML private Button customerButton;
    @FXML private Button reportsButton;
    @FXML private Button logoutButton;

    /**Appointment screen button handler.
     * @param event
     * @throws IOException
     * */
    @FXML public void appointmentScreenButtonHandler(ActionEvent event) throws IOException {
        // /view/Appointment.fxml
        utilities.Navigation.returnToAppointmentScreen(appointmentButton);
    }
    /**
     * Customer screen button handler.
     * @param event
     * @throws IOException
     * */
    @FXML public void customerScreenButtonHandler(ActionEvent event) throws IOException {
        ///view/CustomerScreen.fxml
        utilities.Navigation.goToCustomerScreenButtonHandler(customerButton);
    }
    /**Report screen button handler.
     * @param event
     * @throws IOException
     * */
    @FXML public void reportScreenButtonHandler(ActionEvent event) throws IOException {
        ///view/ReportMainScreen.fxml
        utilities.Navigation.goToReportMainScreen(reportsButton);
    }
    /**
     * Login page button handler.
     * @param event
     * @throws IOException
     * */
    @FXML public void returnToLoginScreenHandler(ActionEvent event) throws IOException {
        ///view/LoginScreen.fxml
        utilities.Navigation.goToLoginScreen(logoutButton);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}
}
