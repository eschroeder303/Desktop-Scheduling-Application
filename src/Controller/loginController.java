package Controller;

import DAO.userDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;

/**
 * Login controller.
 */
public class loginController {
    @FXML public TextField usernameField;
    @FXML public TextField passwordField;
    @FXML public Label usernameLabel;
    @FXML public Label passwordLabel;
    @FXML public Button loginButton;
    @FXML public Label locationTitleLabel;
    @FXML public Label locationLabel;
    @FXML public Label loginError;

    private final ResourceBundle rb = ResourceBundle.getBundle("Languages/translator", Locale.getDefault());

    /**
     * Initialize login and set language based on ZoneId.
     */
    public void initialize() {

        locationLabel.setText(String.valueOf(ZoneId.of(TimeZone.getDefault().getID())));
        loginButton.setText(rb.getString("login"));
        usernameLabel.setText(rb.getString("username"));
        passwordLabel.setText(rb.getString("password"));

    }

    /**
     * Validate username and password.
     *
     * @param actionEvent button click
     * @throws SQLException
     * @throws IOException
     */
    public void loginButtonClick(ActionEvent actionEvent) throws SQLException, IOException {

        if (!inputCheck()) return;
        boolean validLogin = userDAO.checkLogin(usernameField.getText(), passwordField.getText());
        if (validLogin) {
            userDAO.checkLogin(usernameField.getText(), passwordField.getText());
            logLogin(usernameField.getText() + " successfully logged in at ");

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Home.fxml")));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1000, 670);
            stage.setTitle("Schedule Manager");
            stage.setScene(scene);
            stage.show();
        } else {
            loginError.setText(rb.getString("invalid"));
            logLogin(usernameField.getText() + " unsuccessfully attempted to log in at ");
        }
    }

    /**
     * Check for empty username and password fields.
     *
     * @return
     */
    public Boolean inputCheck() {
        if (usernameField.getText().isEmpty()) {
            loginError.setText(rb.getString("username_required"));
            return false;
        } else if (passwordField.getText().isEmpty()) {
            loginError.setText(rb.getString("password_required"));
            return false;
        }
        return true;
    }

    /**
     * Log of successful and unsuccessful logins.
     *
     * @param loginText
     */
    private void logLogin(String loginText) {
        try (FileWriter fileWriter = new FileWriter("login_activity.txt", true)) {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat timeFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm");
            fileWriter.write(loginText + timeFormat.format(date) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}