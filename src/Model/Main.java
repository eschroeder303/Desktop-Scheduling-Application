package Model;

import Database.databaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Create class Main for a Schedule Manager application.
 */
public class Main extends Application {

    /**
     * Open and close connection to the database.
     *
     * @param args
     */
    public static void main(String[] args) {
        databaseConnection.openConnection();
        launch(args);
        databaseConnection.closeConnection();
    }

    /**
     * Initialize Home screen.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../View/Login.fxml")));
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Languages/translator", Locale.getDefault());
        primaryStage.setTitle(resourceBundle.getString("application"));
        primaryStage.setScene(new Scene(root, 540, 440));
        primaryStage.show();
    }
}