package utilities;

import javafx.scene.control.Alert;

public final class AlertsUtility {
    /**
     * prevents instantiation of this class. This class is strictly for providing static methods that can be used across all classes.
     */
    private AlertsUtility(){throw new UnsupportedOperationException("Cannot instantiate the AlertsUtility Class");}

    public static void errorAlerts(int errorSelection){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
        Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        Alert alertWarn = new Alert(Alert.AlertType.WARNING);

        switch(errorSelection){
            //loginControllerAlerts===================================================================================
            case 1: alert.setTitle("Alert");
                alert.setContentText("Incorrect username and/or password. Please try again.");
                alert.showAndWait();
                break;
            case 2: alert.setTitle("Username field blank");
                alert.setContentText("Please enter a Username.");
                alert.showAndWait();
                break;
            case 3: alert.setTitle("Password field blank");
                alert.setContentText("Please enter a Password.");
                alert.showAndWait();
                break;
            case 4: alertInfo.setTitle("WELCOME");
                alertInfo.setContentText("There are no Upcoming Appointments");
                alertInfo.showAndWait();
                break;
            case 5: alertInfo.setTitle("BIENVENU");
                alertInfo.setContentText("Aucun rendez-vous à venir");
                alertInfo.showAndWait();
                break;
            case 6: alert.setTitle("INCORRECT");
                alert.setContentText("\n" + "Mauvais nom d'utilisateur et / ou mot de passe. Veuillez réessayer mon ami.");
                alert.showAndWait();
                break;
            case 7:
                alert.setTitle("Alert");
                alert.setContentText("Please select a Customer");
                alert.showAndWait();
                break;
            case 8:
                alert.setTitle("Check Selection");
                alert.setContentText("Click a Customer in the table then click on the Select Customer button.");
                alert.showAndWait();
                break;
            case 9:
                alert.setTitle("Check Selection");
                alert.setContentText("Select a Customer");
                alert.showAndWait();
                break;
            case 10:
                alert.setTitle("Check Input");
                alert.setContentText("Please Fill in Fields.");
                alert.showAndWait();
                break;
            case 11:
                alertConfirmation.setTitle("Alert");
                alertConfirmation.setContentText("Are you sure you want to cancel?");
                alertConfirmation.showAndWait();
                break;
            case 12:
                alert.setTitle("Alert");
                alert.setContentText("Please Select a Customer first");
                alert.showAndWait();
                break;
            case 13:
                alertWarn.setTitle("Alert");
                alertWarn.setContentText("Are you sure you want to delete the selected customer?");
                alertWarn.showAndWait();
                break;
            case 14:
                alertConfirmation.setTitle("Alert");
                alertConfirmation.setContentText("Customer Removed.");
                alertConfirmation.showAndWait();
                break;
            case 15:
                alert.setTitle("CANNOT DELETE");
                alert.setContentText("Customer has an Associated Appointment. All associated appointments will be removed. ");
                alert.showAndWait();
                break;
            case 16:
                alertConfirmation.setTitle("Alert");
                alertConfirmation.setContentText("Are you sure you want to return to the Main Menu?");
                alertConfirmation.showAndWait();
                break;
            case 17:
                alert.setTitle("OUTSIDE BUSINESS HOURS");
                alert.setContentText("Business hours are 8AM to 10PM EST, including Weekends");
                alert.showAndWait();
                break;
            case 18:
                alert.setTitle("Overlap in Appointments");
                alert.setContentText("Appointment overlaps with another. Please try again");
                alert.showAndWait();
                break;
            case 19:
                alert.setTitle("Alert");
                alert.setContentText("Please Select an Appointment");
                alert.showAndWait();
                break;
            case 20:
                alertConfirmation.setTitle("Alert");
                alertConfirmation.setContentText("Delete the Selected appointment?");
                alertConfirmation.showAndWait();
                break;
            case 21:
                alert.setTitle("Alert");
                alert.setContentText("Click on a appointment in the table, then click Select Appointment button.  Click the Update button again to update.");
                alert.showAndWait();
                break;
            case 22:
                alertConfirmation.setTitle("Alert");
                alertConfirmation.setContentText("Are you sure you want to return?");
                alertConfirmation.showAndWait();
                break;
            case 23:
                alertConfirmation.setTitle("Alert");
                alertConfirmation.setContentText("end date is before start date.");
                alertConfirmation.showAndWait();
                break;
            default:
                alert.setTitle("Alert");
                alert.setContentText("Error!");
                alert.showAndWait();
                break;


        }
    }




}
