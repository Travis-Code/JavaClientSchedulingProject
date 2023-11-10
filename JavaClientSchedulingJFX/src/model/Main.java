package model;

import DAO.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/logInScreen.fxml")));
        primaryStage.setTitle("C195 SOFTWARE 2 OA");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
    }

    public static void main(String[] args) {

        //test code================================================================
        //create a locale
        //Locale locale1 = new Locale("en","us");
        //print locale
        //System.out.println("Locale: " + locale1);
        //set another default locale then print.
        //Locale.setDefault(new Locale("fr","FRANCE"));
        //Locale locate2 = Locale.getDefault();
        //System.out.println("Locale2: " + locate2);
        //test timezone
        //String locationTimeZoneTest = String.valueOf(ZoneId.systemDefault());
        //System.out.println("User Timezone: " + locationTimeZoneTest);


        JDBC.makeConnection();
        //JDBC.getConnection();
        launch(args);
        JDBC.closeConnection();


    }
}
