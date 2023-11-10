package utilities;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public final class Navigation {
    /**
     * Throws and unsupportedOperationException if there is an exception.
     * */
    private Navigation(){throw new UnsupportedOperationException("Cannot instantiate the Navigation Class.");
    }
    /**
     * utility method for navigation back to the MainScreen using the cancel button handler.
     * @param button
     * @throws IOException
     * */
    public static void goBackToMainScreen(Button button)throws IOException {
        Parent returnHome = FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("/view/mainscreen.fxml")));
        Scene returnHomeScene = new Scene(returnHome);
        Stage window = (Stage) button.getScene().getWindow();
        window.setScene(returnHomeScene);
        window.show();
//        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
//        scene = FXMLLoader.load(getClass().getResource("/view/Mainscreen.fxml"));
//        stage.setScene(new Scene(scene));
//        stage.show();
    }
    public static void ReportTotalAppt(Button button)throws IOException{
        Parent reportTotalAppointmentScreen = FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("/view/ReportTotalAppt.fxml")));
        Scene reportAppointmentScheduleScene = new Scene(reportTotalAppointmentScreen);
        Stage reportAppointmentScheduleWindow = (Stage) button.getScene().getWindow();
        reportAppointmentScheduleWindow.setScene(reportAppointmentScheduleScene);
        reportAppointmentScheduleWindow.show();
//        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
//        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/ReportTotalAppt.fxml")));
//        stage.setScene(new Scene(scene));
//        stage.show();
    }
    public static void returnToAppointmentScreen(Button button)throws IOException{
        Parent appointmentScreen = FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("/view/Appointment.fxml")));
        Scene appointmentScreenScene = new Scene(appointmentScreen);
        Stage appointmentScreenWindow = (Stage) button.getScene().getWindow();
        appointmentScreenWindow.setScene(appointmentScreenScene);
        appointmentScreenWindow.show();
//        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Appointment.fxml")));
//        stage.setScene(new Scene(scene));
//        stage.show();
    }
    public static void goToCustomerScreenButtonHandler(Button button)throws IOException{
        Parent customerScreen = FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("/view/CustomerScreen.fxml")));
        Scene customerScreenScene = new Scene(customerScreen);
        Stage customerScreenWindow = (Stage) button.getScene().getWindow();
        customerScreenWindow.setScene(customerScreenScene);
        customerScreenWindow.show();
//        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
//        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CustomerScreen.fxml")));
//        stage.setScene(new Scene(scene));
//        stage.show();
    }

    public static void goToReportMainScreen(Button button)throws IOException{
        Parent mainReportScreen = FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("/view/ReportMainScreen.fxml")));
        Scene mainReportScreenScene = new Scene(mainReportScreen);
        Stage mainReportScreenWindow = (Stage) button.getScene().getWindow();
        mainReportScreenWindow.setScene(mainReportScreenScene);
        mainReportScreenWindow.show();
//        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
//        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/ReportMainScreen.fxml")));
//        stage.setScene(new Scene(scene));
//        stage.show();
    }

    public static void goToLoginScreen(Button button)throws IOException{
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("/view/LoginScreen.fxml")));
        Scene loginScreenScene = new Scene(loginScreen);
        Stage loginScreenWindow = (Stage) button.getScene().getWindow();
        loginScreenWindow.setScene(loginScreenScene);
        loginScreenWindow.show();
//        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
//        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/LoginScreen.fxml")));
//        stage.setScene(new Scene(scene));
//        stage.show();
    }

    public static void goToReportUserScreen(Button button) throws IOException {
        Parent reportUserScreen =FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("/view/ReportUserAppt.fxml")));
        Scene reportUserScreenScene = new Scene(reportUserScreen);
        Stage reportUserScreenWIndow = (Stage) button.getScene().getWindow();
        reportUserScreenWIndow.setScene(reportUserScreenScene);
        reportUserScreenWIndow.show();
//        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
//        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/ReportUserAppt.fxml")));
//        stage.setScene(new Scene(scene));
//        stage.show();
    }
    public static void goToReportContacts(Button button)throws IOException{
        Parent reportContacts = FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("/view/ReportContacts.fxml")));
        Scene reportContactsScene = new Scene(reportContacts);
        Stage reportContactsWindow = (Stage) button.getScene().getWindow();
        reportContactsWindow.setScene(reportContactsScene);
        reportContactsWindow.show();


        //        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
//        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/ReportContacts.fxml")));
//        stage.setScene(new Scene(scene));
//        stage.show();
    }
}
