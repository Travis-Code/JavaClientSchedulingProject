package utilities;

import javafx.scene.control.Alert;

/**
 * Lambda Interface used for alerts
 * */
@FunctionalInterface
public interface AlertsLambdaInterface {
    void alertMessage(Alert alertType);
}
