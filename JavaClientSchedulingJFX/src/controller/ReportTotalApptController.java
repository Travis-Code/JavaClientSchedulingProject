package controller;

import DAO.DBappointments;
import model.Appointments;
import model.ReportByMonth;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

/** This is the class for the Report Total Appointments screen controller. */
public class ReportTotalApptController implements Initializable {

    Parent scene;
    Stage stage;

    @FXML private TableColumn<Appointments, String> monthcol;
    @FXML private TableColumn<Appointments, String> typecol;
    @FXML private TableColumn<Appointments, Integer> countcol;
    @FXML private TableView<ReportByMonth> reportTable;
    @FXML private ComboBox<String> monthbox;
    @FXML private Button viewmonth;
    @FXML private Button backbutton;
    @FXML private ComboBox<String> typebox;
    @FXML private Button viewtype;

    /**
     *Report Main Screen button handler.
     * @param event
     * @throws IOException
     * */
    @FXML public void returnToReportScreen(ActionEvent event) throws IOException {
        //Are you sure you want to return?
        utilities.AlertsUtility.errorAlerts(22);
        ///view/ReportMainScreen.fxml
        utilities.Navigation.goToReportMainScreen(backbutton);
    }


    /** Generates a report based on month selected. */
    @FXML
    public void viewmonthbutton(ActionEvent event) throws SQLException {
        //System.out.println("View month button clicked");
        String selectedMonth = monthbox.getSelectionModel().getSelectedItem().toUpperCase();
        //String selectedType = typebox.getSelectionModel().getSelectedItem();
        reportTable.setItems(DBappointments.getApptByMonthAndType(selectedMonth));
    }
    /**
     * list of months.
     * @param event
     *
     * */
    @FXML public void monthComboBox(ActionEvent event) {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        monthcol.setCellValueFactory((new PropertyValueFactory<>("month")));
        typecol.setCellValueFactory((new PropertyValueFactory<>("type")));
        countcol.setCellValueFactory((new PropertyValueFactory<>("count")));
        ObservableList<String> months = FXCollections.observableArrayList("January", "February", "March",
                "April", "May", "June", "July", "August", "September", "October", "November", "December");
        monthbox.setItems(months);
        monthbox.getSelectionModel().selectFirst();
    }
}