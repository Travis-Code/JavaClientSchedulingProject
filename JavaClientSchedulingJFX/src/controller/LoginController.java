package controller;

import DAO.DBappointments;
import DAO.UserLoginDB;
import model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import utilities.AlertsLambdaInterface;
import utilities.MessagePrintLambdaInterface;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Timestamp;
import java.time.*;
import java.util.*;

/** This is the class for the Login screen controller and Lambda expression in print methods. */
public class LoginController implements Initializable {

//    @FXML private MenuBar loginscreen;
//    @FXML private TextField zoneidbox;

    @FXML private TextField userNameFieldID;
    @FXML private TextField passwordFieldID;
    @FXML private Text loginLabel;
    @FXML private Text schedulingLabel;
    @FXML private Text usernameLabel;
    @FXML private Text passwordLabel;
    @FXML private Text zoneIDLabel;
    @FXML private Label showZoneLabel;
    @FXML private Button submit;
    @FXML private Button exit;

    File file;
    Alert alert = new Alert(Alert.AlertType.WARNING);
    Alert alertErrorTypeLambda = new Alert(Alert.AlertType.ERROR);

    /**
     * Takes you to the Main screen using the utilities alert class, Lambdas are used for Alerts.
     * @param event
     * @return boolean
     * @throws IOException
     */
    @FXML public boolean submitButtonHandler(ActionEvent event) throws IOException {
        ResourceBundle rb = ResourceBundle.getBundle("model/lang", Locale.getDefault());
        if (userNameFieldID.getText().isEmpty()) {
            AlertsLambdaInterface alertTest = (a) ->{
                a.setTitle("Username field blank");
                a.setContentText("Please enter a Username.");
                a.showAndWait();
            };
            alertTest.alertMessage(alertErrorTypeLambda);
        }
        if (passwordFieldID.getText().isEmpty()) {
            AlertsLambdaInterface alertPassWordFieldIDEmpty = (b)->{
                b.setTitle("Password field blank");
                b.setContentText("Please enter a Password.");
                b.showAndWait();
            };
            alertPassWordFieldIDEmpty.alertMessage(alertErrorTypeLambda);
        }
        String username = userNameFieldID.getText();
        String password = passwordFieldID.getText();
        boolean validLogin = UserLoginDB.getActiveUser(username, password);
        if(validLogin) {
            if (upcomingAppointments15min().size()>=1){
                for (Appointments appt:
                        upcomingAppointments15min()) {
                    if(Locale.getDefault().toString().equals("en_US")){
                        alert.setTitle("WELCOME");
                        alert.setContentText("Upcoming Appointment in 15 minutes. Appointment ID: "+ appt.getApptID()+" | Starts: "+ appt.getStart());
                        alert.showAndWait();
                    }
                    if(Locale.getDefault().toString().equals("fr_FR")) {
                        alert.setTitle("BIENVENU");
                        alert.setContentText("Rendez-vous à venir dans 15 minutes. ID de rendez-vous: "+ appt.getApptID()+" | Départs: "+ appt.getStart());
                        alert.showAndWait();
                    }
                }
            } else {
                if(Locale.getDefault().toString().equals("en_US")) {
                    //no Upcoming Appointments
                    utilities.AlertsUtility.errorAlerts(4);
                }
                if(Locale.getDefault().toString().equals("fr_FR")) {
                    //Aucun rendez-vous à venir
                    utilities.AlertsUtility.errorAlerts(5);
                }
            }
            ///view/mainscreen.fxml
            utilities.Navigation.goBackToMainScreen(submit);
            fileLogSuccess(username);
        }
        else {
            if(Locale.getDefault().toString().equals("en_US")) {
                //Incorrect username and/or password. Please try again.
                utilities.AlertsUtility.errorAlerts(1);
                fileLogInvalid(username);
            }
            if(Locale.getDefault().toString().equals("fr_FR")){
                //Mauvais nom d'utilisateur et / ou mot de passe. Veuillez réessayer mon ami.
                utilities.AlertsUtility.errorAlerts(6);
                fileLogInvalid(username);
            }
        }
        return false;
    }
    /** Create the txt file and log the successful login attempts/ The justification for using Lambda code here is to reduce the amount of code.
     * @param username
     * */
    public static void fileLogSuccess(String username) {
        try {
            String loginLog = "login_activity.txt";
            File file = new File(loginLog);
            FileWriter fw = new FileWriter(loginLog, true);
            PrintWriter results = new PrintWriter(fw);
            LocalDateTime localDateTime = LocalDateTime.now();
            results.println("User: " + username +  " has successfully logged in: " + Timestamp.valueOf(localDateTime) );
            results.close();
            System.out.println(username + " Logged Successfully....YAY!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Create a txt file and log unsuccessful login attempts/The justification for using Lambda code here is to reduce the amount of code.
     * @param username
     * */
    public static void fileLogInvalid(String username) {
        try {
            String loginLog = "login_activity.txt";
            File file = new File(loginLog);
            FileWriter fw = new FileWriter(loginLog, true);
            PrintWriter results = new PrintWriter(fw);
            LocalDateTime localDateTime = LocalDateTime.now();
            results.println("User: " + username +  " Unsuccessful log in at: " + Timestamp.valueOf(localDateTime) );
            results.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static UserLoginDB user = new UserLoginDB();
    /** Look for appointments happening in the next 15 minutes.
     * @return Appointments.
     * */
    public ObservableList<Appointments> upcomingAppointments15min() {
        ObservableList<Appointments> allAppointments = DBappointments.getAllAppointments();
        ObservableList<Appointments> upcomingAppointments = FXCollections.observableArrayList();
        if (allAppointments!= null){
            for (Appointments appt : allAppointments){
                LocalDateTime start = appt.getStart().toLocalDateTime();
                LocalDateTime now = Timestamp.from(Instant.now()).toLocalDateTime();
                if (start.isBefore(now.plusMinutes(30))) {
                    if (start.isAfter(now)){
                        upcomingAppointments.add(appt);
                    }
                }
            }
        }
        return upcomingAppointments;
    }
    /** Initializes the controller.
     * @param url
     * @param resourceBundle
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("model/lang", Locale.getDefault());
            usernameLabel.setText(rb.getString("usernameLabel"));
            passwordLabel.setText(rb.getString("passwordLabel"));
            zoneIDLabel.setText(rb.getString("zoneIDLabel"));
            schedulingLabel.setText(rb.getString("schedulingLabel"));
            loginLabel.setText(rb.getString("loginLabel"));
            submit.setText(rb.getString("submitbutton"));
            exit.setText(rb.getString("exitbutton"));
        } catch (MissingResourceException missingResourceException) {
            missingResourceException.printStackTrace();
        }
        LocalDate parisDate = LocalDate.of(2022, 01, 31);
        LocalTime parisTime = LocalTime.of(3, 58);
        ZoneId parisZoneId = ZoneId.of("Europe/Paris");
        ZonedDateTime parisZoneDate = ZonedDateTime.of(parisDate, parisTime, parisZoneId);
        //System.out.println("..........."+parisZoneDate+"............");
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
        Instant parisToGMT = parisZoneDate.toInstant();
        ZonedDateTime GMTtoLocalZDT = parisToGMT.atZone(localZoneId);

        String date = String.valueOf(GMTtoLocalZDT.toLocalDate());
        String time = String.valueOf(GMTtoLocalZDT.toLocalTime());
        String dateTime = date + " " + time;
        //System.out.println(dateTime +"jfoeijfiej" );
        showZoneLabel.setText(String.valueOf(localZoneId));
    }
    //Unused Methods for Event handling buttons ==========================================================================
    /**
     * username field.
     * @param event
     */
    @FXML public void usernameboxfieldEventHandler(ActionEvent event) {
    }
    /**
     * Password Field.
     * @param event
     */
    @FXML public void passwordboxFieldEventHandler(ActionEvent event) {
    }
    /**
     * Exits the application, Lambda used to display exit message in console.
     * @param event
     */
    @FXML public void exitButtonHandler(ActionEvent event) {
        MessagePrintLambdaInterface exitButtonMessage = () -> System.out.println("you exited the program.");
        exitButtonMessage.message();
        System.exit(0);
    }
}






