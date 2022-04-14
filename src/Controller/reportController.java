package Controller;

import Database.databaseConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Objects;

/**
 * Report Controller.
 *
 * Contains Lambda #2.
 */
public class reportController {

    public ObservableList data;
    public String variableQuery;
    public Label reportLabel;
    public Button backButton;
    @FXML private TableView reportsTable;
    @FXML private ToggleGroup reportsRadioToggle;
    @FXML private RadioButton totalRadio;
    @FXML private RadioButton allRadio;
    @FXML private RadioButton schedulesRadio;

    /**
     * Determine which sort to be used based on radio button selection.
     */
    public void initialize() {
        reportsRadioToggle();
    }

    /**
     * Lambda #2: a lambda expression is used to dynamically generate the report tableview.
     */
    public void buildReport() {
        Connection c;
        data = FXCollections.observableArrayList();
        reportsTable.getColumns().clear();
        try {
            c = databaseConnection.getConnection();
            String SQL = variableQuery;
            ResultSet rs = c.createStatement().executeQuery(SQL);

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                // Lambda #2
                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                        new SimpleStringProperty(param.getValue().get(j).toString()));

                reportsTable.getColumns().addAll(col);
            }
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }

            reportsTable.setItems(data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set SQL query based on radio button selection.
     */
    public void reportsRadioToggle() {
        if (totalRadio.isSelected()) {
            variableQuery = ("SELECT Type, MONTHNAME(Start) as Month, count(*) AS Count FROM appointments GROUP BY Type, MONTHNAME(Start) ORDER BY Type;");
        } else if (allRadio.isSelected()) {
            variableQuery = ("SELECT contacts.Contact_Name, appointments.Appointment_ID, appointments.Title, appointments.Type, appointments.Description, CONVERT_TZ(start, '+00:00', 'system') AS start, CONVERT_TZ(end, '+00:00', 'system') AS end, appointments.Customer_ID FROM appointments, contacts WHERE appointments.Contact_ID = contacts.Contact_ID ORDER BY Contact_Name;");
        } else if (schedulesRadio.isSelected()) {
            variableQuery = ("SELECT first_level_divisions.division, appointments.Appointment_ID, appointments.Title, appointments.Type, appointments.Description, CONVERT_TZ(start, '+00:00', 'system') AS start, CONVERT_TZ(end, '+00:00', 'system') AS end, appointments.Customer_ID FROM first_level_divisions, appointments WHERE first_level_divisions.Division_ID = appointments.Contact_ID ORDER BY division;");
        }
        buildReport();
    }

    /**
     * Return to Home screen.
     *
     * @param actionEvent button click
     * @throws Exception
     */
    public void backButtonClick(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Home.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 670);
        stage.setTitle("Schedule Manager");
        stage.setScene(scene);
        stage.show();
    }
}